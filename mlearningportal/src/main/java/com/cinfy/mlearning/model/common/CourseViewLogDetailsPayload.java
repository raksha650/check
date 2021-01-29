package com.cinfy.mlearning.model.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseViewLogDetailsPayload {
	private Integer id;
	
	private UserNewPayload userId;
	private CourseModulePayload courseModuleId;
	private String startTime;
	private String endTime;
	private String totalSpendTime;
	private Date viewDate;
	private Boolean isCourseFinished;

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

	public Date getViewDate() {
		return viewDate;
	}

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	public Boolean getIsCourseFinished() {
		return isCourseFinished;
	}

	public void setIsCourseFinished(Boolean isCourseFinished) {
		this.isCourseFinished = isCourseFinished;
	}

}
