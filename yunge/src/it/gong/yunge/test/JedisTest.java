package it.gong.yunge.test;

import it.gong.yunge.utils.JedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    @Test
    public void test1(){
        Jedis jedis = JedisUtil.getJedis();
        jedis.set("username","zhangsan");
        jedis.close();
    }
}
