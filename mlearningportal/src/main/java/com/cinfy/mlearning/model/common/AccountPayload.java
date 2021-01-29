package com.cinfy.mlearning.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class AccountPayload {

	private String id;

	private String email;

	private String mobile;

	private String name;

	private String role = "admin";	

	private Integer languageId;

	public AccountPayload() {
	}

	public AccountPayload(String id, String email, String mobile, String name) {
		super();
		this.id = id;
		this.email = email;
		this.mobile = mobile;
		this.name = name;

	}

	public AccountPayload(String id, String email, String mobile, String name, String role) {
		super();
		this.id = id;
		this.email = email;
		this.mobile = mobile;
		this.name = name;
		this.role = role;

	}

	public AccountPayload(String id, String email, String mobile, String name, Integer languageId) {
		super();
		this.id = id;
		this.email = email;
		this.mobile = mobile;
		this.name = name;
		this.languageId = languageId;
	}
	
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
