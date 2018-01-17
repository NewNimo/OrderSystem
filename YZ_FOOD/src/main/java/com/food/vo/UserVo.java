package com.food.vo;

/**  
* @author nimo
* @date 2018年1月10日 
* @version V1.0  
* @Description: 描述
*/
public class UserVo {
	private int uid;
	private String nickname;
	private String password;
	private String user;
	private int email;
	
	
	public int getEmail() {
		return email;
	}
	public void setEmail(int email) {
		this.email = email;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}
