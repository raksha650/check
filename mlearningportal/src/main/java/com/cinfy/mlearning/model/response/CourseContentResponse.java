package com.cinfy.mlearning.model.response;

import com.cinfy.mlearning.model.CourseModule;

public class CourseContentResponse {
	private Integer id;
	private String message;
	private CourseModule courseModuleId;
	
	
	public CourseContentResponse(Integer id, CourseModule courseModuleId, String message) {
		super();
		this.id = id;
		this.courseModuleId = courseModuleId;
		this.message = message;
	}

	public CourseModule getSubjectMasterId() {
		return courseModuleId;
	}

	public void setSubjectMasterId(CourseModule courseModuleId) {
		this.courseModuleId = courseModuleId;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
