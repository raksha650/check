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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author j.singh
 */
@Entity
@Table(name = "course_exam")
public class CourseExam implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@JoinColumn(name = "user_id")
	@ManyToOne(targetEntity = UserNew.class, optional = false, fetch = FetchType.EAGER)
	private UserNew userId;

	@JoinColumn(name = "course_module_id")
	@ManyToOne(targetEntity = CourseModule.class, optional = false, fetch = FetchType.EAGER)
	private CourseModule courseModuleId;

	// @JoinColumn(name = "subject_master_id", referencedColumnName = "id")
	// @ManyToOne(optional = false, fetch = FetchType.EAGER)
	// private CourseModule courseModuleId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "exam_date")
	private Date examDate;

	@Column(name = "attempt_no")
	private Integer attemptNo;

	@Column(name = "total_question")
	private Integer totalQuestion;

	@Column(name = "total_attempt")
	private Integer totalAttempt;

	@Column(name = "total_right")
	private Integer totalRight;

	@Column(name = "total_time")
	private String totalTime;

	@Column(name = "result")
	private String result;

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

	public CourseExam() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
