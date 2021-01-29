package com.cinfy.mlearning.model.response;

import java.util.ArrayList;
import java.util.List;

public class LoginUserRes {
	
	private String userId;
	
	private String firstName;
	
	private String lastName;
	
	private String role;
	
	private String mobile;
	
	private String email;
	
	private List<String> menuList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getMenuList() {
		if(this.menuList == null) {
			this.menuList = new ArrayList<>();
		}
		return menuList;
	}

	public void setMenuList(List<String> menuList) {
		this.menuList = menuList;
	}

	
	
	

}
