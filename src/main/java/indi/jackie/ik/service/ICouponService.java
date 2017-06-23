package indi.jackie.ik.service;

import indi.jackie.ik.domain.Coupon;

/**
 * @author jackie chen
 * @create 2017/6/16
 * @description
 */
public interface ICouponService {
    /**
     * 根据id获取优惠券
     *
     * @param id 优惠券id
     * @return 优惠券信息
     */
    Coupon getCouponById(Integer id);
}
