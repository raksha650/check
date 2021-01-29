package com.cinfy.mlearning.model.response;

public class UserRegistrationRes {
	
	private Integer id;
	
	private String email;
	
	private String mobile;
	
	private String name;
	
	
	
	private String message;

	

	public UserRegistrationRes(Integer id, String email, String mobile, String name,String message) {
		super();
		this.id = id;
		this.email = email;
		this.mobile = mobile;
		this.name = name;
	
		this.message = message;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;

	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	
	
	

}