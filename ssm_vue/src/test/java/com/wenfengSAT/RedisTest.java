package com.wenfengSAT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wenfengSAT.common.utils.RedisUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml","classpath:spring-jdbc.xml",
        "classpath:spring-redis.xml","classpath:spring-scheduler.xml",})
public class RedisTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void test(){
        //测试redis，需要设置renren.redis.open=true
        redisUtils.set("domain", "com.wenfengSAT");
        String domain = redisUtils.get("domain");

        System.out.println(domain);
    }
}
