package com.it.huaxia.subscrib_publish;

import redis.clients.jedis.JedisPubSub;

/**
 * @author fengqigui
 * @Date 2017/12/15 19:01
 */
public class RedisSubscrib extends JedisPubSub{

    @Override
    public void onMessage(String channel, String message) {
        System.out.printf(channel+"--------"+message);
    }


}
