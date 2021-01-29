package com.cinfy.mlearning.model.common;

import java.util.Collection;

import com.cinfy.mlearning.model.CourseContent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseAssessmentPayload {
	private Integer id;
    private Integer questionNo;
    private String question;
    private Short option1;
    private Short option2;
    private Short option3;
    private Short option4;
    private Integer correctAnswer;
    private String explanation;
    
    //private Collection<CourseContent> courseContentCollection;
   
    //private CourseCategoryPayload courseCategoryId;
    
    private CourseModulePayload courseModuleId;

	public CourseModulePayload getSubjectMasterId() {
		return courseModuleId;
	}

	public void setSubjectMasterId(CourseModulePayload courseModuleId) {
		this.courseModuleId = courseModuleId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(Integer questionNo) {
		this.questionNo = questionNo;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Short getOption1() {
		return option1;
	}

	public void setOption1(Short option1) {
		this.option1 = option1;
	}

	public Short getOption2() {
		return option2;
	}

	public void setOption2(Short option2) {
		this.option2 = option2;
	}

	public Short getOption3() {
		return option3;
	}

	public void setOption3(Short option3) {
		this.option3 = option3;
	}

	public Short getOption4() {
		return option4;
	}

	public void setOption4(Short option4) {
		this.option4 = option4;
	}

	public Integer getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Integer correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/*public Collection<CourseContent> getCourseContentCollection() {
		return courseContentCollection;
	}

	public void setCourseContentCollection(Collection<CourseContent> courseContentCollection) {
		this.courseContentCollection = courseContentCollection;
	}*/

	

}
