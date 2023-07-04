package com.amano.redisdemo.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.List;

/**
 * @className: RedisUtil
 * @package com.amano.redisdemo.utils
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/30
 **/
public class RedisUtil {
    public static RedisTemplate<String, Object> redisTemplate;

    private static String LOCK_LUA = """
            if redis.call('exists', KEYS[1]) == 0 then 
                redis.call('hset', KEYS[1], ARGV[1], 1) 
                redis.call('expire', KEYS[1], ARGV[2]) 
                return 1 
            else if redis.call('hexists', KEYS[1], ARGV[1]) == 1 then 
                redis.call('hincrby', KEYS[1], ARGV[1], 1) 
                redis.call('expire', KEYS[1], ARGV[2]) 
                return 1 
            else 
                return 0 
            end
            """;

    public boolean lock() {
        String currentThreadName = Thread.currentThread().getName();
        Integer result = redisTemplate.execute(new DefaultRedisScript<>(LOCK_LUA, Integer.class), List.of("lock"), currentThreadName, 30);
        if (result > 0) {
            // 加锁成功，启动续期线程

        }
        return false;
    }
}
