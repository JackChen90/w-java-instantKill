package indi.jackie.ik.service;

import indi.jackie.ik.dto.RefIKAAndCouponDto;

import java.util.List;

/**
 * @author jackie chen
 * @create 2017/6/16
 * @description
 */
public interface IRefIKAAndCouponService {
    List<RefIKAAndCouponDto> getActivatedSale();
}
