package com.jinzhun.user.config;

import org.springframework.context.annotation.Configuration;

import com.jinzhun.common.config.DefaultAsycTaskConfig;

/**
 * 线程沲异步配置,{@link Async}quartz 需要使用
 */
@Configuration
public class AsycTaskExecutorConfig extends DefaultAsycTaskConfig {

}
