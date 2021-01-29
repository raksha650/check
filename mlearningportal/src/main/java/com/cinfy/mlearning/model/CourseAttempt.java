/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cinfy.mlearning.model;

/**
*
* @author j.pathak
*/
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "course_attempt")
public class CourseAttempt implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@JoinColumn(name = "user_id")
	@ManyToOne(targetEntity = UserNew.class, optional = false, fetch = FetchType.EAGER)
	private UserNew userId;

	@JoinColumn(name = "course_module_id", referencedColumnName = "id")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private CourseModule courseModuleId;

	@Column(name = "is_course_completed")
	private Boolean isCourseCompleted;
	
	@Column(name = "is_assessment_pass")
	private Boolean isAssessmentPass;
	
	@Column(name = "assessment_marks")
	private String assessmentMarks;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;
	
	public CourseAttempt() {}

	public CourseAttempt(UserNew userId, CourseModule courseModuleId, Boolean isCourseCompleted,
			Boolean isAssessmentPass, String assessmentMarks, Date createdDate, Date updatedDate) {
		super();
		this.userId = userId;
		this.courseModuleId = courseModuleId;
		this.isCourseCompleted = isCourseCompleted;
		this.isAssessmentPass = isAssessmentPass;
		this.assessmentMarks = assessmentMarks;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserNew getUserId() {
		return userId;
	}

	public void setUserId(UserNew userId) {
		this.userId = userId;
	}

	public CourseModule getCourseModuleId() {
		return courseModuleId;
	}

	public void setCourseModuleId(CourseModule courseModuleId) {
		this.courseModuleId = courseModuleId;
	}

	public Boolean getIsCourseCompleted() {
		return isCourseCompleted;
	}

	public void setIsCourseCompleted(Boolean isCourseCompleted) {
		this.isCourseCompleted = isCourseCompleted;
	}

	public Boolean getIsAssessmentPass() {
		return isAssessmentPass;
	}

	public void setIsAssessmentPass(Boolean isAssessmentPass) {
		this.isAssessmentPass = isAssessmentPass;
	}

	public String getAssessmentMarks() {
		return assessmentMarks;
	}

	public void setAssessmentMarks(String assessmentMarks) {
		this.assessmentMarks = assessmentMarks;
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



}
