package com.cyh.project.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author chenyinghui
 * @create 2019-03-29 22:27
 */
public class JedisPoolTest {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);   //设置最大连接数
        config.setMaxIdle(10);     //设置空闲连接数

        JedisPool jedisPool = new JedisPool(config, "192.168.43.184", 6379);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.auth("root");
            jedis.set("username", "tom");
            String username = jedis.get("username");
            System.out.println(username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
            if (jedisPool != null) {
                jedisPool.close();
            }
        }

    }
}
