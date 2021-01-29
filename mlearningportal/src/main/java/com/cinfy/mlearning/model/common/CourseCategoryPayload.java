package com.cinfy.mlearning.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseCategoryPayload {

	private Integer id;

	private String courseType;

	private Integer totalSubject;

	private Integer totalSubjectRead;

	public Integer getTotalSubject() {
		return totalSubject;
	}

	public void setTotalSubject(Integer totalSubject) {
		this.totalSubject = totalSubject;
	}

	public Integer getTotalSubjectRead() {
		return totalSubjectRead;
	}

	public void setTotalSubjectRead(Integer totalSubjectRead) {
		this.totalSubjectRead = totalSubjectRead;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

}
