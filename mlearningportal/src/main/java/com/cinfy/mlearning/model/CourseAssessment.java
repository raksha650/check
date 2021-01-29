package com.cinfy.mlearning.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
*
* @author j.singh
*/
@Entity
@Table(name = "course_assessment")
public class CourseAssessment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "question_no")
    private Integer questionNo;
    @Lob
    @Column(name = "question")
    private String question;
    @Column(name = "option1")
    private String option1;
    @Column(name = "option2")
    private String option2;
    @Column(name = "option3")
    private String option3;
    @Column(name = "option4")
    private String option4;
    @Column(name = "correct_answer")
    private String correctAnswer;
    @Column(name = "explanation")
    private String explanation;
    
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "courseAssessmentId")
    private Collection<CourseContent> courseContentCollection;*/
    /*@JoinColumn(name = "course_category_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CourseCategory courseCategoryId;*/
    
    @JoinColumn(name = "subject_master_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CourseModule courseModuleId;

    public CourseAssessment() {
    }

    public CourseAssessment(Integer id) {
        this.id = id;
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
    
    public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

   /* @XmlTransient
    public Collection<CourseContent> getCourseContentCollection() {
        return courseContentCollection;
    }

    public void setCourseContentCollection(Collection<CourseContent> courseContentCollection) {
        this.courseContentCollection = courseContentCollection;
    }*/
    public CourseModule getSubjectMasterId() {
		return courseModuleId;
	}

	public void setSubjectMasterId(CourseModule courseModuleId) {
		this.courseModuleId = courseModuleId;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseAssessment)) {
            return false;
        }
        CourseAssessment other = (CourseAssessment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.CourseAssessment[ id=" + id + " ]";
    }
    
}
