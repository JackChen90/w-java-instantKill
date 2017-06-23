package indi.jackie.ik.service.impl;

import com.github.pagehelper.PageHelper;
import indi.jackie.ik.domain.IKActivity;
import indi.jackie.ik.mapper.IKActivityMapper;
import indi.jackie.ik.service.IIKActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jackie chen
 * @create 2017/6/15
 * @description 抢购活动Service
 */
@Service
public class IKActivityServiceImpl implements IIKActivityService {
    @Autowired
    IKActivityMapper ikActivityMapper;

    @Override
    public IKActivity getActivityById(Integer id) {
        return ikActivityMapper.selectByIdInActiviated(id);
    }

    @Override
    public List<IKActivity> getAllActivitiesByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<IKActivity> ikActivities = ikActivityMapper.getAllActivities();
        return ikActivities;
    }
}
