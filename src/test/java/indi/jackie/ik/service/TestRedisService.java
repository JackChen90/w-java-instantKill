package indi.jackie.ik.service;

import indi.jackie.ik.Application;
import indi.jackie.ik.domain.User;
import indi.jackie.ik.dto.RefIKAAndCouponDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jackie chen
 * @create 2017/6/5
 * @description Redis 单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestRedisService {

    @Autowired
    IRedisService redisService;

    @Autowired
    IRefIKAAndCouponService refIKAAndCouponService;

    @Test
    public void testRedisAPI() {
        String a = "a";
        String b = "b";
        String c = "c";

        List<String> lst = new ArrayList<>();
        lst.add("d");
        lst.add("e");
        lst.add("f");
        String key = "letter-key";
        System.out.println("key: " + key + "\t" + "value: " + redisService.get(key));

        redisService.set(key, a);
        redisService.set(key, b);
        redisService.set(key, c);

        System.out.println("key: " + key + "\t" + "value: " + redisService.get(key));

        redisService.setList(key, lst);
        System.out.println("key: " + key + "\t" + "value: " + redisService.getList(key, String.class));

        User user1 = new User();
        user1.setRealname("jackie");
        User user2 = new User();
        user2.setRealname("tony");
        List<User> lstUser = new ArrayList<>();
        lstUser.add(user1);
        lstUser.add(user2);

        redisService.setList(key, lstUser);
        System.out.println("key: " + key + "\t" + "value: " + redisService.getList(key, User.class));


        key = new String("push-key");

        redisService.lpush(key, a);
        redisService.rpush(key, b);

        System.out.println("key: " + key + "\t" + "value: " + redisService.lpop(key));

        redisService.lpop(key);

        System.out.println("key: " + key + "\t" + "value: " + redisService.lpop(key));
        System.out.println("key: " + key + "\t" + "value: " + redisService.lpop(key));

        redisService.rpush(key, b);
        System.out.println("key: " + key + "\t" + "value: " + redisService.lpop(key));

        redisService.rpush(key, b);

        redisService.expire(key, 10L);

        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("key: " + key + "\t" + "value: " + redisService.lpop(key));

    }

    @Test
    public void testA() {
        List<RefIKAAndCouponDto> list = refIKAAndCouponService.getActivatedSale();
    }
}
