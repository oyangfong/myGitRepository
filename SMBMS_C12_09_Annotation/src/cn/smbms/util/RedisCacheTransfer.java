package cn.smbms.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
/**
 * 创建中间类RedisCacheTransfer，完成RedisCache.jedisConnectionFactory的静态注入
 * @author oyangfong
 *
 */
public class RedisCacheTransfer 
{

    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }

}