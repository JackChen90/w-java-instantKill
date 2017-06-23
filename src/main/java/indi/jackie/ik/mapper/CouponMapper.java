package indi.jackie.ik.mapper;

import indi.jackie.ik.domain.Coupon;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {
    Coupon getCouponById(Integer id);
}