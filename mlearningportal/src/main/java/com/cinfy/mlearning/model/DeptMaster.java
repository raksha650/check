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

/**
 *
 * @author j.singh
 */
@Entity
@Table(name = "m_department")
public class DeptMaster implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "dept_name")
	private String deptName;

	@Column(name = "deleted")
	private Integer deleted;

	@Column(name = "language")
	private Integer language;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "common_id")
	private Long commonId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "entry_date")
	private Date entryDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	private Date updatedDate;

	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@ManyToOne
	private CompanyMaster companyId;

	@JoinColumn(name = "office_id", referencedColumnName = "id")
	@ManyToOne
	private OfficeMaster officeId;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public DeptMaster() {
	}

	public DeptMaster(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
		if (!(object instanceof DeptMaster)) {
			return false;
		}
		DeptMaster other = (DeptMaster) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "DeptMaster [id=" + id + ", deptName=" + deptName + ", deleted=" + deleted + ", language=" + language
				+ ", userId=" + userId + ", commonId=" + commonId + ", entryDate=" + entryDate + ", updatedDate="
				+ updatedDate + ", companyId=" + companyId + "]";
	}

}
