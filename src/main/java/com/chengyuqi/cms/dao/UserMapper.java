package com.chengyuqi.cms.dao;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.chengyuqi.cms.entity.User;

public interface UserMapper {
	/**
	 * 检查名字的唯一性
	 * @param username
	 * @return
	 */
	@Select("select id,username,password from cms_user where username=#{username} limit 1")
	User getUserByUserName(@Param("username")String username);

	@Insert("insert into cms_user(username,password,locked,create_time,score,role)"
			+ "values(#{username},#{password},0,now(),0,0)")
	int rejister(@Valid User user);
	
	@Select("SELECT id,username,password,nickname,birthday,"
			+ "gender,locked,create_time createTime,update_time updateTime,url,"
			+ "role FROM cms_user WHERE username=#{username}  AND password = #{password} "
			+ " LIMIT 1")
	User login(User user);

}
