package indi.jackie.ik.mapper;

import indi.jackie.ik.domain.IKActivity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IKActivityMapper {
    /**
     * 根据id获取已激活的活动信息
     *
     * @param id 活动id
     * @return 活动信息
     */
    IKActivity selectByIdInActiviated(Integer id);

    /**
     * 分页获取活动信息
     *
     * @return 活动List
     */
    List<IKActivity> getAllActivities();
}