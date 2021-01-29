package com.cinfy.mlearning.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


public class QuestionListAccess {

	List<Question> questions ;
	
	Integer courseModuleId;
	
	private String startTime;

	

	//private String totalNoOfQuestion;

	

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	
//
//	public String getTotalNoOfQuestion() {
//		return totalNoOfQuestion;
//	}
//
//	public void setTotalNoOfQuestion(String totalNoOfQuestion) {
//		this.totalNoOfQuestion = totalNoOfQuestion;
//	}

	public Integer getCourseModuleId() {
		return courseModuleId;
	}

	public void setCourseModuleId(Integer courseModuleId) {
		this.courseModuleId = courseModuleId;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "QuestionListAccess [questions=" + questions + "]";
	}
	
	

}
