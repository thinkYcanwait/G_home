package it.gong.yunge.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * jedis工具类
 */
public class JedisUtil {
    private static JedisPool jedisPool;

    static {
        InputStream is = JedisUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties p = new Properties();
        try {
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String maxTotal = p.getProperty("maxTotal");
        String maxIdle = p.getProperty("maxIdle");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(Integer.parseInt(maxIdle));
        jedisPoolConfig.setMaxTotal(Integer.parseInt(maxTotal));
        jedisPool=new JedisPool(jedisPoolConfig,p.getProperty("host"),Integer.parseInt(p.getProperty("port")));
    }
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
