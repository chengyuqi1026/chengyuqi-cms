package com.chengyuqi.cms.service;

import javax.validation.Valid;

import com.chengyuqi.cms.entity.User;

public interface UserService {

	User getUserByUserName(String username);

	int rejister(@Valid User user);

	User login(User user);

}
