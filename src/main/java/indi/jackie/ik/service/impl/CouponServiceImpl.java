package indi.jackie.ik.service.impl;

import indi.jackie.ik.domain.Coupon;
import indi.jackie.ik.mapper.CouponMapper;
import indi.jackie.ik.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jackie chen
 * @create 2017/6/16
 * @description
 */
@Service
public class CouponServiceImpl implements ICouponService {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public Coupon getCouponById(Integer id) {
        return couponMapper.getCouponById(id);
    }
}
