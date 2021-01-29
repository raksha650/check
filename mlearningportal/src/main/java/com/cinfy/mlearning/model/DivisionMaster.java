package com.cinfy.mlearning.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author j.singh
 */
@Entity
@Table(name = "m_division")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"entryDate", "updatedDate"}, 
allowGetters = true)
public class DivisionMaster implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "deleted")
	private Integer deleted;

	@Column(name = "language")
	private Integer language;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "common_id")
	private Long commonId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "entry_date", nullable = false, updatable = false)
	private Date entryDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;

	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@ManyToOne
	private CompanyMaster companyId;

	@JoinColumn(name = "office_id", referencedColumnName = "id")
	@ManyToOne
	private OfficeMaster officeId;
	
	@JoinColumn(name = "dept_id", referencedColumnName = "id")
	@ManyToOne
	private DeptMaster deptId;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DeptMaster getDeptId() {
		return deptId;
	}

	public void setDeptId(DeptMaster deptId) {
		this.deptId = deptId;
	}

	public CompanyMaster getCompanyId() {
		return companyId;
	}

	public void setCompanyId(CompanyMaster companyId) {
		this.companyId = companyId;
	}

	public OfficeMaster getOfficeId() {
		return officeId;
	}

	public void setOfficeId(OfficeMaster officeId) {
		this.officeId = officeId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getLanguage() {
		return language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}

	public Long getCommonId() {
		return commonId;
	}

	public void setCommonId(Long commonId) {
		this.commonId = commonId;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public DivisionMaster() {
	}

	public DivisionMaster(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		if (!(object instanceof DivisionMaster)) {
			return false;
		}
		DivisionMaster other = (DivisionMaster) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DivisionMaster [id=" + id + ", deleted=" + deleted + ", language=" + language
				+ ", createdBy=" + createdBy + ", commonId=" + commonId + ", entryDate=" + entryDate + ", updatedDate="
				+ updatedDate + ", companyId=" + companyId + "]";
	}

}
