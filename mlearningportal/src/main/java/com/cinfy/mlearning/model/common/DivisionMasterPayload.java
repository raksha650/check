package com.cinfy.mlearning.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DivisionMasterPayload {

	private Integer id;

	private String name;

	private Integer deleted;

	private Integer language;

	private Long commonId;

	private CompanyMasterPayload companyId;

	private OfficeMasterPayload officeId;

	private DeptMasterPayload deptId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getLanguage() {
		return language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

	public Long getCommonId() {
		return commonId;
	}

	public void setCommonId(Long commonId) {
		this.commonId = commonId;
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

}
