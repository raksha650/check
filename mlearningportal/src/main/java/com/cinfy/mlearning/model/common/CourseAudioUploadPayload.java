package com.cinfy.mlearning.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseAudioUploadPayload {
	private Integer id;
    private String audioPath;
    private CourseContentPayload courseContentId;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAudioPath() {
		return audioPath;
	}
	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}
	public CourseContentPayload getCourseContentId() {
		return courseContentId;
	}
	public void setCourseContentId(CourseContentPayload courseContentId) {
		this.courseContentId = courseContentId;
	}
    

}
