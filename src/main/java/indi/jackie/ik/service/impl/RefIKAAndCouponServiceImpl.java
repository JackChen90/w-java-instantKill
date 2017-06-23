package indi.jackie.ik.service.impl;

import indi.jackie.ik.dto.RefIKAAndCouponDto;
import indi.jackie.ik.mapper.RefIKAAndCouponMapper;
import indi.jackie.ik.service.IRefIKAAndCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jackie chen
 * @create 2017/6/16
 * @description
 */
@Service
public class RefIKAAndCouponServiceImpl implements IRefIKAAndCouponService {

    @Autowired
    RefIKAAndCouponMapper refIKAAndCouponMapper;

    @Override
    public List<RefIKAAndCouponDto> getActivatedSale() {
        return refIKAAndCouponMapper.selectAllActivatedSale();
    }
}
