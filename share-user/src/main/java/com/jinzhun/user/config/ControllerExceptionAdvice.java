package com.jinzhun.user.config;

import org.springframework.web.bind.annotation.ControllerAdvice;

import com.jinzhun.common.exception.DefaultExceptionAdvice;

/**
 * Controller通用异常处理类
 */
@ControllerAdvice
public class ControllerExceptionAdvice extends DefaultExceptionAdvice {

}
