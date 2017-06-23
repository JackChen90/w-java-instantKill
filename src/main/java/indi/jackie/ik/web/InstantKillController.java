package indi.jackie.ik.web;

import com.google.gson.Gson;
import indi.jackie.ik.domain.Coupon;
import indi.jackie.ik.domain.IKActivity;
import indi.jackie.ik.dto.RefIKAAndCouponDto;
import indi.jackie.ik.service.ICouponService;
import indi.jackie.ik.service.IIKActivityService;
import indi.jackie.ik.service.IRedisService;
import indi.jackie.ik.service.IRefIKAAndCouponService;
import indi.jackie.ik.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jackie chen
 * @create 2017/6/15
 * @description 抢购Controller
 */
@Controller
public class InstantKillController {

    @Autowired
    IRedisService redisService;

    @Autowired
    IIKActivityService iikActivityService;

    @Autowired
    IRefIKAAndCouponService refIKAAndCouponService;

    @Autowired
    ICouponService couponService;

    @RequestMapping(value = "/ikActivity")
    public ModelAndView getIKbyId(@RequestParam Integer ikaId) {
        ModelAndView mv = new ModelAndView("instantKill");
        if (ikaId == null) {
            return mv;
        }

        IKActivity activity = redisService.get("ikaId" + ikaId, IKActivity.class);
        if (null == activity) {
            activity = iikActivityService.getActivityById(ikaId);
            redisService.set("ikaId" + ikaId, new Gson().toJson(activity));
        }
        mv.addObject("activity", activity);
        return mv;
    }

    @RequestMapping(value = "/activies")
    public ModelAndView getAllIKActivities(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        ModelAndView mv = new ModelAndView("showActivities");

        List<IKActivity> ikActivityList = redisService.getList("ika-all-" + pageNum, IKActivity.class);
        if (null == ikActivityList || ikActivityList.size() == 0) {
            ikActivityList = iikActivityService.getAllActivitiesByPage(pageNum, pageSize);
            redisService.setList("ika-all", ikActivityList);
        }
        mv.addObject("activities", ikActivityList);
        return mv;
    }

    @RequestMapping(value = "/doIK")
    public ModelAndView instantKill(@RequestParam Integer ikaId) {
        //标记 0：活动结束 1：正常获取到活动优惠券
        Integer flag = 0;

        ModelAndView mv = new ModelAndView("showResult");
        if (ikaId == null) {
            flag = 0;
        } else {
            //获取该活动对应的优惠券id列表
            List<String> couponList = redisService.getList("ika-coupon-" + ikaId, String.class);

            if (couponList != null && couponList.size() != 0) {

                Random random = new Random();
                random.nextInt();
                //随机数，得到用户获取的券的类型
                Integer temp = random.nextInt(couponList.size());

                //redis中对该优惠券数量做减一操作
                Long amount = redisService.decr("ika-coupon-cid-" + couponList.get(temp));

                //amount>=0，则存在券；否则券已被抢光
                if (null != amount && amount >= 0) {
                    //最后一张券
                    if (amount == 0) {
                        //更新redis券列表
                        couponList.remove(temp);
                        redisService.setList("ika-coupon-" + ikaId, couponList);
                        //删除redis中已兑完的券
                        redisService.del("ika-coupon-cid-" + couponList.get(temp));
                    }

                    //返回优惠券
                    Coupon coupon = redisService.get("ikc-coupon-id-" + couponList.get(temp), Coupon.class);
                    if (coupon == null) {
                        coupon = couponService.getCouponById(Integer.parseInt(couponList.get(temp)));
                        redisService.set("ikc-coupon-id-" + couponList.get(temp), new Gson().toJson(coupon));
                    }

                    if (coupon != null) {
                        flag = 1;
                        mv.addObject("coupon", coupon);
                    }
                }
            }
        }
        mv.addObject("flag", flag);
        return mv;
    }


    @RequestMapping(value = "/doInit")
    public Boolean doInit() {
        return initRedis();
    }

    private boolean initRedis() {
        try {
            List<RefIKAAndCouponDto> refList = refIKAAndCouponService.getActivatedSale();
            insertRedis(refList);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private void insertRedis(List<RefIKAAndCouponDto> refList) {
        Integer ikaId = refList.get(0).getIdIka();
        List<Integer> tempCoupons = new ArrayList<>();
        tempCoupons.add(refList.get(0).getIdCoupon());

        redisService.set("ika-coupon-cid-" + refList.get(0).getIdCoupon(), refList.get(0).getAmount().toString());

        if (refList.size() == 1) {
            redisService.setList("ika-coupon-" + ikaId, tempCoupons);
        }

        for (int i = 1; i < refList.size(); i++) {
            if (!refList.get(i).getIdIka().equals(ikaId)) {
                redisService.setList("ika-coupon-" + ikaId, tempCoupons);
                ikaId = refList.get(i).getIdIka();
                tempCoupons = new ArrayList<>();
            }
            tempCoupons.add(refList.get(i).getIdCoupon());
            redisService.set("ika-coupon-cid-" + refList.get(i).getIdCoupon(), refList.get(i).getAmount().toString());
        }

        String a = redisService.get("ika-coupon-cid-1");
        List<Integer> blist = redisService.getList("ika-coupon-1", Integer.class);
    }
}
