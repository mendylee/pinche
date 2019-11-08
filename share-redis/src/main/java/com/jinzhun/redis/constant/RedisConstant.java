package com.jinzhun.redis.constant;

/**
 * Redis常量定义工具类
 */
public class RedisConstant {
	
    private RedisConstant() {
        throw new IllegalStateException("Redis Utility class");
    }
	
	/**
	 * 单模式
	 */
    public final static int SINGLE = 1 ;
    
	/**
	 * 集群模式
	 */
    public final static int CLUSTER = 2 ;
}
