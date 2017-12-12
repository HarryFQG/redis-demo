package com.it.huaxia.test;

import com.it.huaxia.entry.User;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemoRedis01 {

    /**
     * 测试java的String类型
     */
    @Test
    public void testString(){

        Jedis jedis =new Jedis("127.0.0.1",6379);

        jedis.set("java","coder");
        jedis.set("C++", "ccoder");
        // 设置过期时间
        jedis.expire("java",5);
        // 添加分布式锁的String
        jedis.setnx("Python","编程的艺术");

        jedis.del("java");

        System.out.println(jedis.mget("java","C++","Python"));


    }

    /**
     * 测试存储对象：hash
     */
    @Test
    public void testMap(){

        Jedis jedis = new Jedis("127.0.0.1",6379);

        Map<String,String> map = new HashMap<String, String>();
        map.put("java","java疯狂讲义");
        map.put("C++","图灵笔记");
        map.put("Python","编码艺术");


        User user = new User();
        user.setId("12");
        user.setAge(22);
        user.setUserName("张三丰");

        jedis.hmset("book",map);

        // 修改就是覆盖
        map.put("C++","中国的编年史");
        jedis.hmset("book",map);


        System.out.println(jedis.hmget("book","C++"));
        System.out.println(jedis.hgetAll("book"));


    }

    /**
     * 测试List
     */
    @Test
    public void testList(){
        Jedis jedis = new Jedis("127.0.0.1",6379);

        jedis.lpush("isList","aa","bb","cc");

        List<String> list = new ArrayList<String>();
        list.add("dd");
        list.add("ee");
        list.add("ff");
        list.add("gg");
        list.add("hh");
        jedis.lpush("myList",list.toArray(new String[list.size()]));

        System.out.println(jedis.lrange("myList",0,20));
        System.out.println(jedis.lindex("myList",2));
        // 将栈顶的元素弹出并且删除
        System.out.println(jedis.lpop("myList"));
        // 将栈底的元素弹出并且删除
        System.out.println(jedis.rpop("rmyList"));
        // 翻转进行输出
        System.out.println(jedis.rpop("myList"));
        // 设置下标为x的值
        jedis.lset("myList",0,"詹景婷");
        // 获取list的长度
        System.out.println("长度为："+jedis.llen("myList"));

    }

    /**
     * 测试Set
     */

    @Test
    public void testSet(){
        Jedis jedis = new Jedis("127.0.0.1",6379);

        // 添加set集合
        jedis.sadd("mySet","李四","王五","张三","赵六","钱七");
        jedis.sadd("mySet1","李思思","王五","张百礽","赵小六","钱七");
        // 拿下面的所有元素
        System.out.println("所有的元素："+jedis.smembers("mySet"));

        // 删除栈顶的元素,成功为1
        System.out.println("删除栈顶的元素："+jedis.spop("mySet"));

        // 删除之地元素
        System.out.println("删除指定的元素："+jedis.srem("mySet","钱七"));

        // 找出两个集合的相同的元素
        System.out.println("相同元素："+jedis.sinter("mySet","mySet1"));
        // 找出两个集合的不同的元素
        System.out.println("相同元素："+jedis.sdiff("mySet","mySet1"));



    }

    /**
     * 测试SortedSet
     */
    @Test
    public void testSortedSet(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        Map<String,Double> map = new HashMap<String, Double>();
        map.put("李四",10.00);
        map.put("王五",1.00);
        map.put("张三",2.00);
        map.put("赵六",6.00);
        map.put("钱七",4.00);

        jedis.zadd("mySortedSet",map);

        System.out.println("所有的元素："+jedis.zrange("mySortedSet",0,11));

        // 更新Score,这个可以做商品的热度，和商品的热度。
        jedis.zadd("mySortedSet",5,"李四");
        System.out.println("所有的元素："+jedis.zrange("mySortedSet",0,11));

        // 按照score进行倒序
        System.out.println("倒序："+jedis.zrevrange("mySortedSet",0,10));


    }





}
