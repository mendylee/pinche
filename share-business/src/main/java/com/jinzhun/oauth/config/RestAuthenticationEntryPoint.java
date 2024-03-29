package com.jinzhun.oauth.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.jinzhun.common.utils.CommonResult;

import cn.hutool.json.JSONUtil;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.toJsonStr(new CommonResult().unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}
