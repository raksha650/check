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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author j.singh
 */
@Entity
@Table(name = "assessment_log_detail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssessmentLogDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	@ManyToOne
	private UserNew userId;

	@JoinColumn(name = "course_module_id", referencedColumnName = "id")
	@ManyToOne
	private CourseModule courseModuleId;

	@Column(name = "start_time")
	private String startTime;

	@Column(name = "end_time")
	private String endTime;

	@Column(name = "total_spend_time")
	private String totalSpendTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "assessment_date")
	private Date assessmentDate;

	@Column(name = "total_no_of_question")
	private String totalNoOfQuestion;

	@Column(name = "question_attempted")
	private String questionAttempted;

	@Column(name = "question_corrected")
	private String questionCorrected;

	@Column(name = "status")
	private String status;

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

	public Date getAssessmentDate() {
		return assessmentDate;
	}

	public void setAssessmentDate(Date assessmentDate) {
		this.assessmentDate = assessmentDate;
	}

	public String getTotalNoOfQuestion() {
		return totalNoOfQuestion;
	}

	public void setTotalNoOfQuestion(String totalNoOfQuestion) {
		this.totalNoOfQuestion = totalNoOfQuestion;
	}

	public String getQuestionAttempted() {
		return questionAttempted;
	}

	public void setQuestionAttempted(String questionAttempted) {
		this.questionAttempted = questionAttempted;
	}

	public String getQuestionCorrected() {
		return questionCorrected;
	}

	public void setQuestionCorrected(String questionCorrected) {
		this.questionCorrected = questionCorrected;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
