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
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author j.singh
 */
@Entity
@Table(name = "user_new")
public class UserNew implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "userId")
	private Integer userId;

	@Column(name = "deleted")
	private Integer deleted = 0;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "language_id")
	private Integer languageId;

	@Column(name = "password")
	private String password;

	@Column(name = "added_date")
	private Date addedDate;

	@Column(name = "profile_image_path")
	private String profileImagePath;

	@Column(name = "emp_id")
	private String empId;
	
	
	@Column(name = "is_reset_pwd")
	private Boolean isResetPwd=false;
	
	

	@JoinColumn(name = "company_id", referencedColumnName = "id")
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	private CompanyMaster companyId;

	@JoinColumn(name = "office_id", referencedColumnName = "id")
	@ManyToOne(optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	private OfficeMaster officeId;

	@JoinColumn(name = "dept_id", referencedColumnName = "id")
	@ManyToOne(optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	private DeptMaster deptId;

	@JoinColumn(name = "division_id", referencedColumnName = "id")
	@ManyToOne(optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	private DivisionMaster divisionId;

	@JoinColumn(name = "designation_id", referencedColumnName = "id")
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	private DesignationMaster designationId;

	@Column(name = "is_manager")
	private Integer isManager;

	@Column(name = "role")
	private String role;

	@JoinColumn(name = "district_id", referencedColumnName = "id")
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	private District districtId;

	// @Column(name = "district_name")
	// private String districtName;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "manager_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private UserNew managerId;

	public District getDistrictId() {
		return districtId;
	}

	public void setDistrictId(District districtId) {
		this.districtId = districtId;
	}

	public DesignationMaster getDesignationId() {
		return designationId;
	}

	public void setDesignationId(DesignationMaster designationId) {
		this.designationId = designationId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public Boolean getIsResetPwd() {
		return isResetPwd;
	}

	public void setIsResetPwd(Boolean isResetPwd) {
		this.isResetPwd = isResetPwd;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public String getProfileImagePath() {
		return profileImagePath;
	}

	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
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

	public DeptMaster getDeptId() {
		return deptId;
	}

	public void setDeptId(DeptMaster deptId) {
		this.deptId = deptId;
	}

	public DivisionMaster getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(DivisionMaster divisionId) {
		this.divisionId = divisionId;
	}

	public Integer getIsManager() {
		return isManager;
	}

	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public UserNew getManagerId() {
		return managerId;
	}

	public void setManagerId(UserNew managerId) {
		this.managerId = managerId;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userId != null ? userId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserNew)) {
			return false;
		}
		UserNew other = (UserNew) object;
		if ((this.userId == null && other.userId != null)
				|| (this.userId != null && !this.userId.equals(other.userId))) {
			return false;
		}
		return true;
	}

}
