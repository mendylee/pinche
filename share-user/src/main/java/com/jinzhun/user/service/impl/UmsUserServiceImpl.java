package com.jinzhun.user.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinzhun.common.constant.CommonConstant;
import com.jinzhun.common.model.LoginUser;
import com.jinzhun.common.model.Result;
import com.jinzhun.common.model.UmsUser;
import com.jinzhun.user.mapper.UmsUserMapper;
import com.jinzhun.user.service.IUmsUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@SuppressWarnings({"rawtypes","unused"})
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements IUmsUserService {
	
	private final static String LOCK_KEY_USERNAME = CommonConstant.LOCK_KEY_PREFIX + "username:";
	
	@Resource private UmsUserMapper userMapper;
	@Autowired private PasswordEncoder passwordEncoder;
	
	@Override
	public LoginUser findByOpenId(String openId) {
		log.info("UmsUserService:findByOpenId({})",openId);
		return getLoginUser(this.selectByOpenId(openId));
	}

	@Override
	public LoginUser findByMobile(String mobile) {
		log.info("UmsUserService:findByMobile({})",mobile);
		return getLoginUser(this.selectByMobile(mobile));
	}

	@Override
	public LoginUser findByUsername(String username) {
		log.info("UmsUserService:findByUsername({})",username);
		return getLoginUser(this.selectByUsername(username));
	}

	@Override
	public UmsUser selectByOpenId(String openId) {
		log.info("UmsUserService:selectByOpenId({})",openId);
		return getLoginUser(this.selectByOpenId(openId));
	}
	
	@Override
	public LoginUser getLoginUser(UmsUser umsUser) {
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(umsUser, loginUser);
        return loginUser;
	}

	@Override
	public UmsUser selectByMobile(String mobile) {
		return getUser(baseMapper.selectList(new QueryWrapper<UmsUser>().eq("mobile",mobile)));
	}

	@Override
	public UmsUser selectByUsername(String username) {
		return getUser(baseMapper.selectList(new QueryWrapper<UmsUser>().eq("username",username)));
	}
	
    private UmsUser getUser(List<UmsUser> users) {
        UmsUser user = null;
        if (users != null && !users.isEmpty()) {
            user = users.get(0);
        }
        return user;
    }


	@Override
	public Result updateEnabled(Map<String, Object> params) {
		return null;
	}

	@Override
	public boolean saves(UmsUser user) {
		user.setCreateTime(new Date());
		user.setEnabled(true);
		
		//查询是否有相同用户名的用户
		List<UmsUser> userList = userMapper.selectList(new QueryWrapper<UmsUser>().eq("username",user.getUsername()));
        if (userList.size() > 0) {
            return false;
        }
        
        //将密码进行加密操作
        if (StringUtils.isEmpty(user.getPassword())){
        	user.setPassword("123456");
        }
        String md5Password = passwordEncoder.encode(user.getPassword());
        user.setPassword(md5Password);
        userMapper.insert(user);
        return true;
	}

	@Override
	@Transactional
	public boolean updates(Long id, UmsUser user) {
		user.setUsername(null);
		user.setId(id);
        String md5Password = passwordEncoder.encode(user.getPassword());
        user.setPassword(md5Password);
        userMapper.updateById(user);
        return true;
	}

}
