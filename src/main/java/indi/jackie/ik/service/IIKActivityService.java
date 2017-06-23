package indi.jackie.ik.service;

import indi.jackie.ik.domain.IKActivity;

import java.util.List;

/**
 * @author jackie chen
 * @create 2017/6/15
 * @description 抢购活动Service
 */
public interface IIKActivityService {
    /**
     * 根据id获取活动信息
     * @param id 活动id
     * @return 活动信息
     */
    IKActivity getActivityById(Integer id);

    /**
     * 分页获取活动信息
     * @param pageNum 当前页码
     * @param pageSize 每页大小
     * @return 活动List
     */
    List<IKActivity> getAllActivitiesByPage(Integer pageNum, Integer pageSize);
}
