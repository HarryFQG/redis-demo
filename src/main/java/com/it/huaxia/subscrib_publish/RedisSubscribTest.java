package com.it.huaxia.subscrib_publish;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * 测试redis的发布订阅
 * 订阅
 * @author fengqigui
 * @Date 2017/12/15 18:54
 */
public class RedisSubscribTest {

    @Test
    public void testRedis1(){

        Jedis jedis = new Jedis("127.0.0.1",6379);

        jedis.subscribe(new RedisSubscrib(),"myRedisChannel");

    }

}
