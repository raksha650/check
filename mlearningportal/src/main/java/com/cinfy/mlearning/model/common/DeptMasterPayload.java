package com.cinfy.mlearning.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeptMasterPayload {

	private Integer id;

	private String deptName;

	private CompanyMasterPayload companyId;

	private OfficeMasterPayload officeId;

	private Integer language;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public Integer getLanguage() {
		return language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

}
