package com.cinfy.mlearning.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author j.singh
 */
@Entity
@Table(name = "course_slide_upload")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseUpload implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "slide_path")
	private String slidePath;

	@Column(name = "audio_path")
	private String audioPath;

	@Column(name = "is_img_or_video")
	private Boolean isImgOrVideo;

	@Column(name = "deleted")
	private Boolean deleted = false;

	@Transient
	MultipartFile slidePathMultipart;

	@Transient
	MultipartFile audioPathMultipart;

	@JsonManagedReference
	@JoinColumn(name = "course_module_id", referencedColumnName = "id")
	@ManyToOne
	private CourseModule courseModuleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "updated_by")
	private Integer updatedBy;

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

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
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

	public CourseUpload() {
	}

	public CourseUpload(Integer id) {
		this.id = id;
	}

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

	public CourseModule getCourseModuleId() {
		return courseModuleId;
	}

	public void setCourseModuleId(CourseModule courseModuleId) {
		this.courseModuleId = courseModuleId;
	}

	@Override
	public String toString() {
		return "CourseUpload [id=" + id + ", slidePath=" + slidePath + ", audioPath=" + audioPath + ", isImgOrVideo="
				+ isImgOrVideo + ", deleted=" + deleted + ", slidePathMultipart=" + slidePathMultipart
				+ ", audioPathMultipart=" + audioPathMultipart + ", courseModuleId=" + courseModuleId + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ "]";
	}

}
