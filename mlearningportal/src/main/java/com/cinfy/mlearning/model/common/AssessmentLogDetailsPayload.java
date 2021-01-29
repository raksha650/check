package com.cinfy.mlearning.model.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AssessmentLogDetailsPayload {
	private Integer id;

	private UserNewPayload userId;

	private CourseModulePayload courseModuleId;

	private String startTime;

	private String endTime;

	private String totalSpendTime;

	private Date assessmentDate;

	private String totalNoOfQuestion;

	private String questionAttempted;

	private String questionCorrected;

	private String status;

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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTotalSpendTime() {
		return totalSpendTime;
	}

	public void setTotalSpendTime(String totalSpendTime) {
		this.totalSpendTime = totalSpendTime;
	}

	public Date getAssessmentDate() {
		return assessmentDate;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	public String getTotalNoOfQuestion() {
		return totalNoOfQuestion;
	}

	public void setTotalNoOfQuestion(String totalNoOfQuestion) {
		this.totalNoOfQuestion = totalNoOfQuestion;
	}

	public String getQuestionAttempted() {
		return questionAttempted;
	}

	public void setQuestionAttempted(String questionAttempted) {
		this.questionAttempted = questionAttempted;
	}

	public String getQuestionCorrected() {
		return questionCorrected;
	}

	public void setQuestionCorrected(String questionCorrected) {
		this.questionCorrected = questionCorrected;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
