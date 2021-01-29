package com.cinfy.mlearning.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseAttemptPayload {

	private Integer id;

	
	private UserNewPayload userId;

	private CourseModulePayload courseModuleId;

	private Boolean isCourseCompleted;
	
	private Boolean isAssessmentPass;
	
	private String assessmentMarks;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserNewPayload getUserId() {
		return userId;
	}

	public void setUserId(UserNewPayload userId) {
		this.userId = userId;
	}

	public CourseModulePayload getCourseModuleId() {
		return courseModuleId;
	}

	public void setCourseModuleId(CourseModulePayload courseModuleId) {
		this.courseModuleId = courseModuleId;
	}

	public Boolean getIsCourseCompleted() {
		return isCourseCompleted;
	}

	public void setIsCourseCompleted(Boolean isCourseCompleted) {
		this.isCourseCompleted = isCourseCompleted;
	}

	public Boolean getIsAssessmentPass() {
		return isAssessmentPass;
	}

	public void setIsAssessmentPass(Boolean isAssessmentPass) {
		this.isAssessmentPass = isAssessmentPass;
	}

	public String getAssessmentMarks() {
		return assessmentMarks;
	}

	public void setAssessmentMarks(String assessmentMarks) {
		this.assessmentMarks = assessmentMarks;
	}

	

}
