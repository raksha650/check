package com.cinfy.mlearning.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseContentPayload {
	private Integer id;
	private Integer totalSlide;
	private String totalAllottedTime;
	private Integer totalAssessmentQuestion;
	private Integer minPassNo;
	private String videoOrImage;

	// private CourseAssessmentPayload courseAssessmentId;
	private CourseModulePayload courseModuleId;

	public String getVideoOrImage() {
		return videoOrImage;
	}

	public void setVideoOrImage(String videoOrImage) {
		this.videoOrImage = videoOrImage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotalSlide() {
		return totalSlide;
	}

	public void setTotalSlide(Integer totalSlide) {
		this.totalSlide = totalSlide;
	}

	public String getTotalAllottedTime() {
		return totalAllottedTime;
	}

	public void setTotalAllottedTime(String totalAllottedTime) {
		this.totalAllottedTime = totalAllottedTime;
	}

	public Integer getTotalAssessmentQuestion() {
		return totalAssessmentQuestion;
	}

	public void setTotalAssessmentQuestion(Integer totalAssessmentQuestion) {
		this.totalAssessmentQuestion = totalAssessmentQuestion;
	}

	public Integer getMinPassNo() {
		return minPassNo;
	}

	public void setMinPassNo(Integer minPassNo) {
		this.minPassNo = minPassNo;
	}

	/*
	 * public CourseAssessmentPayload getCourseAssessmentId() { return
	 * courseAssessmentId; } public void
	 * setCourseAssessmentId(CourseAssessmentPayload courseAssessmentId) {
	 * this.courseAssessmentId = courseAssessmentId; }
	 */
	public CourseModulePayload getCourseModuleId() {
		return courseModuleId;
	}

	public void setCourseModuleId(CourseModulePayload courseModuleId) {
		this.courseModuleId = courseModuleId;
	}

}
