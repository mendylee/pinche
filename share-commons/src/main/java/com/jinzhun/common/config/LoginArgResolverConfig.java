package com.jinzhun.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jinzhun.common.feign.UserService;
import com.jinzhun.common.resolver.TokenArgumentResolver;

/**
 * 公共配置类, 一些公共工具配置
 */
public class LoginArgResolverConfig implements WebMvcConfigurer {

	@Lazy
	@Autowired
	private UserService userService;
	
    /**
     * Token参数解析
     *
     * @param argumentResolvers 解析类
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new TokenArgumentResolver(userService));
    }
}
