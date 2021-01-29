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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author j.singh
 */
@Entity
@Table(name = "course_view_log_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseViewLogDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	
	@JoinColumn(name = "user_id", referencedColumnName = "userId")
	@ManyToOne(fetch = FetchType.LAZY)
	private UserNew userId;
	
	@JoinColumn(name = "course_module_id", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.LAZY)
	private CourseModule courseModuleId;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "end_time")
	private String endTime;

	@Column(name = "total_spend_time")
	private String totalSpendTime;	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "view_date")
	private Date viewDate;
	
	@Column(name = "is_course_finished")
	private Boolean isCourseFinished;

	
	
	public CourseViewLogDetails(UserNew userId, Integer courseModuleId, String startTime, String endTime,
			String totalSpendTime, Date viewDate, Boolean isCourseFinished) {
		super();
		this.userId = userId;
		this.courseModuleId.setId(courseModuleId);
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalSpendTime = totalSpendTime;
		this.viewDate = viewDate;
		this.isCourseFinished = isCourseFinished;
	}

	
	
	public CourseViewLogDetails() {
		// TODO Auto-generated constructor stub
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

	public Date getViewDate() {
		return viewDate;
	}

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	public Boolean getIsCourseFinished() {
		return isCourseFinished;
	}

	public void setIsCourseFinished(Boolean isCourseFinished) {
		this.isCourseFinished = isCourseFinished;
	}

}
