package com.cinfy.mlearning.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseUploadPayload {
	private Integer id;
    private String slidePath;
    private String audioPath;
    private Boolean isImgOrVideo;
    private CourseModulePayload courseModuleId;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSlidePath() {
		return slidePath;
	}
	public void setSlidePath(String slidePath) {
		this.slidePath = slidePath;
	}
	public String getAudioPath() {
		return audioPath;
	}
	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}
	public CourseModulePayload getCourseModuleId() {
		return courseModuleId;
	}
	public void setCourseModuleId(CourseModulePayload courseModuleId) {
		this.courseModuleId = courseModuleId;
	}
	public Boolean getIsImgOrVideo() {
		return isImgOrVideo;
	}
	public void setIsImgOrVideo(Boolean isImgOrVideo) {
		this.isImgOrVideo = isImgOrVideo;
	}
	
	
    

}
