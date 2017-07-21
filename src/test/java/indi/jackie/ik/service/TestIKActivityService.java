package indi.jackie.ik.service;

import com.google.gson.Gson;
import indi.jackie.ik.Application;
import indi.jackie.ik.domain.IKActivity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author jackie chen
 * @create 2017/6/26
 * @description 抢购活动单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestIKActivityService {
    @Autowired
    IIKActivityService iikActivityService;

    @Test
    public void testAjaxGetAllIKActivities() {
        List<IKActivity> ikActivityList = iikActivityService.getAllActivitiesByPage(1, 10);
        new Gson().toJson(ikActivityList);
    }
}
