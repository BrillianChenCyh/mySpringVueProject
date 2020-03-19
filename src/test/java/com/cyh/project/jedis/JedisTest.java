package com.cyh.project.jedis;

import redis.clients.jedis.Jedis;

/**
 * @author chenyinghui
 * @create 2019-03-29 22:02
 */
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("47.101.50.57",6303);
        jedis.auth("whlt@2018#!");
        jedis.set("name","123");
        System.out.println(jedis.get("name"));
        jedis.close();
    }
}
