package com.cinfy.mlearning.model.common;

import java.util.Date;

public class CourseExamPayload {

	private Integer id;

	private UserNewPayload userId;

	private CourseModulePayload courseModuleId;

	private Date examDate;

	private Integer attemptNo;

	private Integer totalQuestion;

	private Integer totalAttempt;

	private Integer totalRight;

	private String totalTime;

	private String result;

	public Integer getId() {
		return id;
	}

	public CourseModulePayload getCourseModuleId() {
		return courseModuleId;
	}

	public void setCourseModuleId(CourseModulePayload courseModuleId) {
		this.courseModuleId = courseModuleId;
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

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public Integer getAttemptNo() {
		return attemptNo;
	}

	public void setAttemptNo(Integer attemptNo) {
		this.attemptNo = attemptNo;
	}

	public Integer getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(Integer totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public Integer getTotalAttempt() {
		return totalAttempt;
	}

	public void setTotalAttempt(Integer totalAttempt) {
		this.totalAttempt = totalAttempt;
	}

	public Integer getTotalRight() {
		return totalRight;
	}

	public void setTotalRight(Integer totalRight) {
		this.totalRight = totalRight;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
