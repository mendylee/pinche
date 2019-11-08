package com.jinzhun.oauth.controller;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jinzhun.common.constant.SecurityConstants;
import com.jinzhun.common.model.Result;
import com.jinzhun.common.model.SysUser;
import com.jinzhun.common.utils.SpringUtil;
import com.jinzhun.oauth.config.WechatConfiguration;
import com.jinzhun.oauth.mobile.MobileAuthenticationToken;
import com.jinzhun.oauth.openid.OpenIdAuthenticationToken;
import com.jinzhun.oauth.service.impl.RedisClientDetailsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthWeChatRequest;

@Slf4j
@RestController
@Api(tags = "OAuth2")
public class OAuth2Controller {
    
    @Autowired private WechatConfiguration wechatConfig;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    
    @Resource private ObjectMapper objectMapper;
    @Resource private AuthorizationServerTokenServices authorizationServerTokenServices;
    
	@ApiOperation(value = "用户登录")
	@PostMapping(SecurityConstants.OAUTH_LOGIN_PRO_URL)
	public void loginByWeixin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//创建授权request
		AuthWeChatRequest wechatRequest = new AuthWeChatRequest(AuthConfig.builder()
											 .clientId(wechatConfig.getClientId())
											 .clientSecret(wechatConfig.getClientSecret())
											 .redirectUri(wechatConfig.getRedirectUri())
											 .build());
		//生成授权页面
		wechatRequest.authorize(wechatConfig.getState());
		AuthCallback authCallback = new AuthCallback();
		authCallback.setState(wechatConfig.getState());
		wechatRequest.login(authCallback);
	}
	
	@ApiOperation(value = "用户名密码获取token")
	@PostMapping(SecurityConstants.PASSWORD_LOGIN_PRO_URL)
	public void getUserTokenInfo(@RequestBody SysUser loginParam,HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(loginParam.getUsername() == null || "".equals(loginParam.getUsername())) {
			throw new UnapprovedClientAuthenticationException("用户名为空");
		}
		if(loginParam.getPassword() == null || "".equals(loginParam.getPassword())) {
			throw new UnapprovedClientAuthenticationException("密码为空");
		}
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword());
        writerToken(request, response, token, "用户名或密码错误");
	}
	
    @ApiOperation(value = "openId获取token")
    @PostMapping(SecurityConstants.OPENID_TOKEN_URL)
	public void getTokenByOpenId(@ApiParam(required = true, name = "openId", value = "openId") String openId,
							     HttpServletRequest request, HttpServletResponse response) throws IOException {
		OpenIdAuthenticationToken token = new OpenIdAuthenticationToken(openId);
		writerToken(request, response, token, "openId错误");
	}

    @ApiOperation(value = "mobile获取token")
    @PostMapping(SecurityConstants.MOBILE_TOKEN_URL)
    public void getTokenByMobile(@ApiParam(required = true, name = "mobile", value = "mobile") String mobile,
            				     @ApiParam(required = true, name = "password", value = "密码") String password,
            				     HttpServletRequest request, HttpServletResponse response) throws IOException {
        MobileAuthenticationToken token = new MobileAuthenticationToken(mobile, password);
        writerToken(request, response, token, "手机号或密码错误");
    }
    
    @SuppressWarnings("unchecked")
    private void writerToken(HttpServletRequest request, HttpServletResponse response, AbstractAuthenticationToken token, String badCredenbtialsMsg) throws IOException {
        try {
            String clientId = request.getHeader("client_id");
            String clientSecret = request.getHeader("client_secret");
            if (clientId == null || "".equals(clientId)) {
                throw new UnapprovedClientAuthenticationException("请求头中无client_id信息");
            }
            if (clientSecret == null || "".equals(clientSecret)) {
                throw new UnapprovedClientAuthenticationException("请求头中无client_secret信息");
            }
            ClientDetails clientDetails = getClient(clientId, clientSecret, null);
			TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "customer");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            oAuth2Authentication.setAuthenticated(true);
            writerObj(response, oAuth2AccessToken);
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            exceptionHandler(response, badCredenbtialsMsg);
            e.printStackTrace();
        } catch (Exception e) {
            exceptionHandler(response, e);
        }
    }
    
    private void exceptionHandler(HttpServletResponse response, Exception e) throws IOException {
        log.error("exceptionHandler-error:", e);
        exceptionHandler(response, e.getMessage());
    }

    private void exceptionHandler(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        writerObj(response, Result.failed(msg));
    }
    
	private void writerObj(HttpServletResponse response, Object obj) throws IOException {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		try (Writer writer = response.getWriter()) {
			writer.write(objectMapper.writeValueAsString(obj));
			writer.flush();
		}
	}
	
	private ClientDetails getClient(String clientId, String clientSecret, RedisClientDetailsService clientDetailsService) {
		if (clientDetailsService == null) {
			clientDetailsService = SpringUtil.getBean(RedisClientDetailsService.class);
		}
		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

		if (clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clientId对应的信息不存在");
		} else if (!passwordEncoder.matches(clientSecret, clientDetails.getClientSecret())) {
			throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
		}
		return clientDetails;
	}
    	
}
