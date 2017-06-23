package indi.jackie.ik.mapper;

import indi.jackie.ik.dto.RefIKAAndCouponDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RefIKAAndCouponMapper {
    List<RefIKAAndCouponDto> selectAllActivatedSale();
}