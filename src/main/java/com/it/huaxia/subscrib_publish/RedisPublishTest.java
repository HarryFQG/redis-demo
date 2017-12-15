package com.it.huaxia.subscrib_publish;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**测试redis的发布订阅
 * 发布
 * @author fengqigui
 * @Date 2017/12/15 19:01
 */
public class RedisPublishTest extends JedisPubSub{

    @Test
    public void testRedis2(){

        Jedis jedis = new Jedis("127.0.0.1",6379);

        jedis.publish("myRedisChannel","我在学习Redis");

    }

}
