package com.chengyuqi.cms.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.chengyuqi.cms.common.Commcont;
import com.chengyuqi.cms.entity.User;
import com.chengyuqi.cms.service.UserService;

public class CmsInterceptor implements HandlerInterceptor{
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		User loginUser = (User) request.getSession().getAttribute(Commcont.USER_KEY);

		if(loginUser!=null) {
			return true;
		}
		
		//从cookie中获取信息
		User user = new User();
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if("username".equals(cookies[i].getName())) {
				user.setUsername(cookies[i].getValue());
			}
			if("password".equals(cookies[i].getName())) {
				user.setPassword(cookies[i].getValue());
			}
		}
		
		//说明cookie中存放的用户信息不完整
		if(null==user.getUsername() || null==user.getPassword()) {
			response.sendRedirect("/user/login");
			return false;
		}
		
		
		//利用cookie中用户信息进行登录操作
		loginUser = userService.login(user);
		if(loginUser!=null) {
			System.err.println("-------------------------------------登录");
			request.getSession().setAttribute(Commcont.USER_KEY, loginUser);
			return true;
		}
		
		response.sendRedirect("/user/login");
		return false;
	}
	
}
