package com.cinfy.mlearning.model.common;

public class OfficeMasterPayload {

	private Integer id;

	private CompanyMasterPayload companyId;

	private String name;

	private Integer deleted;

	private Integer language;

	private Long commonId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CompanyMasterPayload getCompanyId() {
		return companyId;
	}

	public void setCompanyId(CompanyMasterPayload companyId) {
		this.companyId = companyId;
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

}
