package com.cinfy.mlearning.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoursePayload {

	private Integer id;

	private String name;

	private Boolean isCompliance;

	private String courseImagePath;

	private CourseCategoryPayload courseCategoryId;

	private Integer language;

	public String getCourseImagePath() {
		return courseImagePath;
	}

	public void setCourseImagePath(String courseImagePath) {
		this.courseImagePath = courseImagePath;
	}

	public Boolean getIsCompliance() {
		return isCompliance;
	}

	public void setIsCompliance(Boolean isCompliance) {
		this.isCompliance = isCompliance;
	}

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

	public CourseCategoryPayload getCourseCategoryId() {
		return courseCategoryId;
	}

	public void setCourseCategoryId(CourseCategoryPayload courseCategoryId) {
		this.courseCategoryId = courseCategoryId;
	}

	public Integer getLanguage() {
		return language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

}
