package com.cinfy.mlearning.model;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
/**
*
* @author j.singh
*/
public class Login {
	@NotEmpty
	@Size(min = 1, max = 50)
	private String emailId;

	@NotEmpty
	@Size(min = 1, max = 40)
	private String password;

	@NotEmpty
	@Size(min = 1, max = 10)
	private String phone;

	@NotEmpty
	@Size(min = 1, max = 10)
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Login [emailId=" + emailId + ", password=" + password + ", phone=" + phone + ", role=" + role + "]";
	}

}
