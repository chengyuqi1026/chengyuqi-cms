package com.chengyuqi.cms.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chengyuqi.cms.common.CmsUtils;
import com.chengyuqi.cms.dao.UserMapper;
import com.chengyuqi.cms.entity.User;
import com.chengyuqi.cms.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByUserName(String username) {
		return userMapper.getUserByUserName(username);
	}
	
	@Override
	public int rejister(@Valid User user) {
		String password = user.getPassword();
		String string = CmsUtils.encryption(password, "uiwhefuissjbbjabqi89562");
		user.setPassword(string);
		return userMapper.rejister(user);
	}
	
	@Override
	public User login(User user) {
		
		String string = CmsUtils.encryption(user.getPassword(), "uiwhefuissjbbjabqi89562");
		user.setPassword(string);
		
		return userMapper.login(user);
	}
}
