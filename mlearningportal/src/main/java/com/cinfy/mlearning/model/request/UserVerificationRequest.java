package com.cinfy.mlearning.model.request;

public class UserVerificationRequest {

	private String type;
	
	
	private String username;
	
	private String email;
	
	private String password;
	
	private String mobile;
	
	private String otpCode;
	
	
	private boolean isOtpSent;

	
	private String otpVerified;
	


	public String getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public boolean isOtpSent() {
		return isOtpSent;
	}

	public void setOtpSent(boolean isOtpSent) {
		this.isOtpSent = isOtpSent;
	}

	public String getOtpVerified() {
		return otpVerified;
	}

	public void setOtpVerified(String otpVerified) {
		this.otpVerified = otpVerified;
	}


	
	
	
}
