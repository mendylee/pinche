package com.jinzhun.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.jinzhun.db.config.DefaultMybatisPlusConfig;

@Configuration
@MapperScan({"com.jinzhun.user.mapper*"})
public class MybatisPlusConfig extends DefaultMybatisPlusConfig {

    /**
     * 逻辑删除注入器
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }
}
