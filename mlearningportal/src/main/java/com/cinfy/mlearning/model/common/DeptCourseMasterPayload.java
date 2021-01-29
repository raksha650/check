package com.cinfy.mlearning.model.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeptCourseMasterPayload {

	private Integer id;

	private DeptMasterPayload deptId;

	// private SubDeptMasterPayload subDeptId;

	private CoursePayload courseId;

	private Date completionDate;

	private Integer companyId;

	private Integer language;

	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DeptMasterPayload getDeptId() {
		return deptId;
	}

	public void setDeptId(DeptMasterPayload deptId) {
		this.deptId = deptId;
	}

	public CoursePayload getCourseId() {
		return courseId;
	}

	public void setCourseId(CoursePayload courseId) {
		this.courseId = courseId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getLanguage() {
		return language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

}
