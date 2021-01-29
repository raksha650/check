package com.cinfy.mlearning.model.request;

public class LoginUser {

	private String userId;

	private String password;

	private String mobile;

	private Integer languageId = 1;

	private String role;
	
	
	

	public LoginUser(String userId, String password, String mobile, String role) {
		super();
		this.userId = userId;
		this.password = password;
		this.mobile = mobile;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LoginUser() {
	}



	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
