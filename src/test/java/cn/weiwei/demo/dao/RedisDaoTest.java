package cn.weiwei.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDaoTest {

    @Autowired
    RedisDao redisDao;

    @Test
    public void contextLoads() {
    }

    @Test
    public void setKey() {
        redisDao.setKey("weiwei", "jianglongchan");
    }

    @Test
    public void getValue() {
        System.out.println("########## " + redisDao.getValue("weiwei"));
    }
}
