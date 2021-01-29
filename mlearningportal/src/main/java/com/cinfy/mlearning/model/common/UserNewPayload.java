package com.cinfy.mlearning.model.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserNewPayload {

	private Integer userId;
	private String email;
	private String phone;
	private String firstName;
	private String middleName;
	private String lastName;
	private Integer languageId;
	private String password;
	private String profileImagePath;
	private String profileImageName;
	private String role;
	private Date addedDate;
	private Boolean isResetPwd;

	private CompanyMasterPayload companyId;
	private OfficeMasterPayload officeId;
	private DeptMasterPayload deptId;
	private DivisionMasterPayload divisionId;
	
	private DesignationMasterPayload designationId;
	private DistrictMasterPayload districtId;
	

	public Boolean getIsResetPwd() {
		return isResetPwd;
	}

	public void setIsResetPwd(Boolean isResetPwd) {
		this.isResetPwd = isResetPwd;
	}

	public DesignationMasterPayload getDesignationId() {
		return designationId;
	}

	public void setDesignationId(DesignationMasterPayload designationId) {
		this.designationId = designationId;
	}

	public DistrictMasterPayload getDistrictId() {
		return districtId;
	}

	public void setDistrictId(DistrictMasterPayload districtId) {
		this.districtId = districtId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	public String getProfileImageName() {
		return profileImageName;
	}

	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public CompanyMasterPayload getCompanyId() {
		return companyId;
	}

	public void setCompanyId(CompanyMasterPayload companyId) {
		this.companyId = companyId;
	}

	public OfficeMasterPayload getOfficeId() {
		return officeId;
	}

	public void setOfficeId(OfficeMasterPayload officeId) {
		this.officeId = officeId;
	}

	public DeptMasterPayload getDeptId() {
		return deptId;
	}

	public void setDeptId(DeptMasterPayload deptId) {
		this.deptId = deptId;
	}

	public DivisionMasterPayload getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(DivisionMasterPayload divisionId) {
		this.divisionId = divisionId;
	}

}
