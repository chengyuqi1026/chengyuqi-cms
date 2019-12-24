package com.chengyuqi.cms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import com.chengyuqi.cms.common.Gender;

/**
 * 	用户类
 * @author ChengYQ
 *
 * 2019年12月11日
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8763169151913630016L;
	
	private Integer	id              ;
	
	@NotBlank(message="用户名必须填写")
	@Size(max=16,message="用户名最长十六位")
	private String	username        ;
	
	@NotBlank(message="密码不可为空")
	@Size(max=16,min=8,message="密码最少八位，最长十六位")
	private String	password        ;
	private String	nickname        ;
	private Date	birthday        ;
	private Gender	gender          ;
	private int		locked          ;
	private Date	create_time     ;
	private Date	update_time     ;
	private String	url             ;
	private int		score           ;
	private int	role            ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nickname=" + nickname
				+ ", birthday=" + birthday + ", gender=" + gender + ", locked=" + locked + ", create_time="
				+ create_time + ", update_time=" + update_time + ", url=" + url + ", score=" + score + ", role=" + role
				+ "]";
	}
	public User(Integer id, @NotBlank(message = "用户名必须填写") @Size(max = 16, message = "用户名最长十六位") String username,
			@NotBlank(message = "密码不可为空") @Size(max = 16, min = 8, message = "密码最少八位，最长十六位") String password,
			String nickname, Date birthday, Gender gender, int locked, Date create_time, Date update_time, String url,
			int score, int role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.birthday = birthday;
		this.gender = gender;
		this.locked = locked;
		this.create_time = create_time;
		this.update_time = update_time;
		this.url = url;
		this.score = score;
		this.role = role;
	}
	public User() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}