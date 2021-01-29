package com.cinfy.mlearning.model.response;

public class CourseModuleResponse {
	private Integer id;
	private String message;
	private String courseType;
	
	
	public CourseModuleResponse(Integer id, String courseType, String message) {
		super();
		this.id = id;
		this.courseType = courseType;
		this.message = message;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
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
