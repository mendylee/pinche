package com.jinzhun.redis.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * 缓存管理配置
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "cache-manager")
public class CacheManagerProperties {

	/**
	 * 缓存配置列表
	 */
	private List<CacheConfig> configs;

	@Setter
	@Getter
	public static class CacheConfig {
		private String key;
		private long second = 60;
	}
}
