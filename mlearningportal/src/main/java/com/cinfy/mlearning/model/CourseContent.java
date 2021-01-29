/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import com.couchbase.client.deps.com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author j.singh
 */
@Entity
@Table(name = "course_content")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseContent implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "total_slide")
	private Integer totalSlide;

	@Column(name = "total_allotted_time")
	private String totalAllottedTime;

	@Column(name = "total_assessment_question")
	private Integer totalAssessmentQuestion;

	@Column(name = "min_pass_no")
	private Integer minPassNo;

	@Column(name = "deleted")
	private Integer deleted = 0;

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

	@JsonManagedReference
	@JoinColumn(name = "course_module_id", referencedColumnName = "id")
	@ManyToOne
	private CourseModule courseModuleId;

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

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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

	public CourseModule getCourseModuleId() {
		return courseModuleId;
	}

	public void setCourseModuleId(CourseModule courseModuleId) {
		this.courseModuleId = courseModuleId;
	}

	@Override
	public String toString() {
		return "CourseContent [id=" + id + ", totalSlide=" + totalSlide + ", totalAllottedTime=" + totalAllottedTime
				+ ", totalAssessmentQuestion=" + totalAssessmentQuestion + ", minPassNo=" + minPassNo + ", deleted="
				+ deleted + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", courseModuleId=" + courseModuleId + "]";
	}

	
}
