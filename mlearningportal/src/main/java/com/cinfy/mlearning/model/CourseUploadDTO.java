package com.cinfy.mlearning.model;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author j.pathak
 */

public class CourseUploadDTO {

	private String id;

	private String slidePath;

	private String audioPath;

	private Boolean isImgOrVideo;

	private Boolean deleted = false;

	@Transient
	MultipartFile slidePathMultipart;

	@Transient
	MultipartFile audioPathMultipart;

	private String courseModuleId;

	private Date createdDate;

	private Date updatedDate;

	private String createdBy;

	private String updatedBy;

	public CourseUploadDTO() {
	}

	public CourseUploadDTO(String id, String slidePath, String audioPath, Boolean isImgOrVideo, Boolean deleted,
			MultipartFile slidePathMultipart, MultipartFile audioPathMultipart, String courseModuleId, String createdBy,
			String updatedBy) {
		super();
		this.id = id;
		this.slidePath = slidePath;
		this.audioPath = audioPath;
		this.isImgOrVideo = isImgOrVideo;
		this.deleted = deleted;
		this.slidePathMultipart = slidePathMultipart;
		this.audioPathMultipart = audioPathMultipart;
		this.courseModuleId = courseModuleId;

		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Boolean getIsImgOrVideo() {
		return isImgOrVideo;
	}

	public void setIsImgOrVideo(Boolean isImgOrVideo) {
		this.isImgOrVideo = isImgOrVideo;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public MultipartFile getSlidePathMultipart() {
		return slidePathMultipart;
	}

	public void setSlidePathMultipart(MultipartFile slidePathMultipart) {
		this.slidePathMultipart = slidePathMultipart;
	}

	public MultipartFile getAudioPathMultipart() {
		return audioPathMultipart;
	}

	public void setAudioPathMultipart(MultipartFile audioPathMultipart) {
		this.audioPathMultipart = audioPathMultipart;
	}

	public String getCourseModuleId() {
		return courseModuleId;
	}

	public void setCourseModuleId(String courseModuleId) {
		this.courseModuleId = courseModuleId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

}
