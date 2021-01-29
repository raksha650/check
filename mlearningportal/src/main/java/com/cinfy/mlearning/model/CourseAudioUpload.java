package com.cinfy.mlearning.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
*
* @author j.singh
*/
@Entity
@Table(name = "course_audio_upload")
public class CourseAudioUpload implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "audio_path")
    private String audioPath;
   
    @JoinColumn(name = "course_content_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CourseContent courseContentId;

    public CourseAudioUpload() {
    }

    public CourseAudioUpload(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getAudioPath() {
		return audioPath;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}

	public CourseContent getCourseContentId() {
        return courseContentId;
    }

    public void setCourseContentId(CourseContent courseContentId) {
        this.courseContentId = courseContentId;
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
        if (!(object instanceof CourseAudioUpload)) {
            return false;
        }
        CourseAudioUpload other = (CourseAudioUpload) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   
    
}
