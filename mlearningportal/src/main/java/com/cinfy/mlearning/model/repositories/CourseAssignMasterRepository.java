package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.CourseAssignMaster;
import com.cinfy.mlearning.model.CourseModule;

@Repository
@Transactional
public interface CourseAssignMasterRepository extends JpaRepository<CourseAssignMaster, Integer>{
	
	@Query("select courseAssignMaster from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 " )
	List<CourseAssignMaster> findAll();

	CourseAssignMaster findById(Integer id);
	
	CourseAssignMaster findByCourseIdId(Integer id);
	
	@Query("select courseAssignMaster from CourseAssignMaster courseAssignMaster where courseAssignMaster.id=:id" )
	List<CourseAssignMaster> findByIdList(Integer id);

	@Query("select courseAssignMaster from CourseAssignMaster courseAssignMaster where courseAssignMaster.language=:language and courseAssignMaster.deleted=0 order by id desc" )
	List<CourseAssignMaster> findByLanguage(@Param("language") Integer language);

	@Query("select courseAssignMaster from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.commonId = :commonId order by commonId desc")
	List<CourseAssignMaster> findByCommonId(@Param("commonId") Long commonId);

	@Query("select courseAssignMaster from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 order by commonId desc")
	List<CourseAssignMaster> findAllOrderByCommonId();

	@Query("select courseAssignMaster from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.deptId.commonId=:deptCommonId order by courseAssignMaster.commonId desc")
	List<CourseAssignMaster> findAllByDeptCommonId(@Param("deptCommonId") Long deptCommonId);
	
	@Query("select courseAssignMaster.courseId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.courseId.deleted=0 and courseAssignMaster.divisionId.id=:divisionId AND courseAssignMaster.divisionId.language=:language AND courseAssignMaster.courseId.isCompliance=:isCompliance")
	List<Course> findAllByDivisionIdAndDivisionIdLanguageAndIsComplianceForCourseFetch(@Param("divisionId") Integer divisionId, @Param("language") Integer language, @Param("isCompliance") Boolean isCompliance);
		
	@Query("select courseAssignMaster.courseId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.courseId.deleted=0 and courseAssignMaster.deptId.id=:deptId AND courseAssignMaster.language=:language AND courseAssignMaster.courseId.isCompliance=:isCompliance")
	List<Course> findAllByDeptIdAndDeptIdLanguageAndIsComplianceForCourseFetch(@Param("deptId") Integer deptId, @Param("language") Integer language, @Param("isCompliance") Boolean isCompliance);
	
	@Query("select courseAssignMaster.courseId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.courseId.deleted=0 and courseAssignMaster.officeId.id=:officeId AND courseAssignMaster.officeId.language=:language AND courseAssignMaster.courseId.isCompliance=:isCompliance")
	List<Course> findAllByOfficeIdAndOfficeIdLanguageAndIsComplianceForCourseFetch(@Param("officeId") Integer officeId, @Param("language") Integer language, @Param("isCompliance") Boolean isCompliance);
	
	@Query("select courseAssignMaster.courseId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.courseId.deleted=0 and courseAssignMaster.companyId.id=:companyId AND courseAssignMaster.companyId.language=:language AND courseAssignMaster.courseId.isCompliance=:isCompliance")
	List<Course> findAllByCompanyIdAndCompanyIdLanguageAndIsComplianceForCourseFetch(@Param("companyId") Integer companyId, @Param("language") Integer language, @Param("isCompliance") Boolean isCompliance);
	
	@Query("select courseAssignMaster.courseId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.courseId.deleted=0 and courseAssignMaster.divisionId.id=:divisionId and courseAssignMaster.divisionId.language=:language and courseAssignMaster.completionDate>= :currentDt AND courseAssignMaster.completionDate<:nextDt")
	List<Course> findAllByDivisionIdAndDivisionIdLanguageAndComplianceDatewithin7daysForCourseFetch (@Param("divisionId") Integer divisionId,@Param("language") Integer language,@Param("currentDt") java.util.Date currentDt,@Param("nextDt") java.util.Date nextDt);
	
	@Query("select courseAssignMaster.courseId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.courseId.deleted=0 and courseAssignMaster.deptId.id=:deptId and courseAssignMaster.deptId.language=:language and courseAssignMaster.completionDate>= :currentDt AND courseAssignMaster.completionDate<:nextDt")
	List<Course> findAllByDeptIdAndDeptIdLanguageAndComplianceDatewithin7daysForCourseFetch (@Param("deptId") Integer deptId,@Param("language") Integer language,@Param("currentDt") java.util.Date currentDt,@Param("nextDt") java.util.Date nextDt);
	
	@Query("select courseAssignMaster.courseId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.courseId.deleted=0 and courseAssignMaster.officeId.id=:officeId and courseAssignMaster.officeId.language=:language and courseAssignMaster.completionDate>= :currentDt AND courseAssignMaster.completionDate<:nextDt")
	List<Course> findAllByOfficeIdAndOfficeIdLanguageAndComplianceDatewithin7daysForCourseFetch (@Param("officeId") Integer officeId,@Param("language") Integer language,@Param("currentDt") java.util.Date currentDt,@Param("nextDt") java.util.Date nextDt);
	
	@Query("select courseAssignMaster.courseId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.courseId.deleted=0 and courseAssignMaster.companyId.id=:companyId and courseAssignMaster.companyId.language=:language and courseAssignMaster.completionDate>= :currentDt AND courseAssignMaster.completionDate<:nextDt")
	List<Course> findAllByCompanyIdAndCompanyIdLanguageAndComplianceDatewithin7daysForCourseFetch (@Param("companyId") Integer companyId,@Param("language") Integer language,@Param("currentDt") java.util.Date currentDt,@Param("nextDt") java.util.Date nextDt);
	
	
//	@Query("select courseAssignMaster.courseId.courseCategoryId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.deptId.commonId=:deptCommonId and courseAssignMaster.deptId.language=:language order by courseAssignMaster.commonId desc")
//	List<CourseCategory> findAllByDeptIdCommonIdAndDeptIdLanguage (@Param("deptCommonId") Long deptCommonId,@Param("language") Integer langusge);
	
//	@Query("select courseAssignMaster.courseId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.deptId.commonId=:deptCommonId and courseAssignMaster.deptId.language=:language order by courseAssignMaster.commonId desc")
//	List<Course> findAllByDeptIdCommonIdAndDeptIdLanguageForCourseFetch (@Param("deptCommonId") Long deptCommonId,@Param("language") Integer langusge);
	
//	@Query("select courseAssignMaster.courseId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.deptId.commonId=:deptCommonId and courseAssignMaster.deptId.language=:language and courseAssignMaster.courseId.isCompliance=:isCompliance order by courseAssignMaster.commonId desc")
//	List<Course> findAllByDeptIdCommonIdAndDeptIdLanguageAndIsComplianceForCourseFetch (@Param("deptCommonId") Long deptCommonId,@Param("language") Integer langusge,@Param("isCompliance") Boolean isCompliance);
	
//	@Query("select courseAssignMaster.courseId from CourseAssignMaster courseAssignMaster where courseAssignMaster.deleted = 0 and courseAssignMaster.deptId.commonId=:deptCommonId and courseAssignMaster.deptId.language=:language and courseAssignMaster.completionDate>= :currentDt AND courseAssignMaster.completionDate<:nextDt")
//	List<Course> findAllByDeptIdCommonIdAndDeptIdLanguageAndComplianceDatewithin7daysForCourseFetch (@Param("deptCommonId") Long deptCommonId,@Param("language") Integer language,@Param("currentDt") java.util.Date currentDt,@Param("nextDt") java.util.Date nextDt);
	
	 
	@Modifying
	@Query("update CourseAssignMaster courseAssignMaster set courseAssignMaster.deleted = 1 where courseAssignMaster.commonId = :commonId ")
	void courseAssignMasterDeleteCommon(@Param("commonId") Long commonId);
	
	@Query("SELECT courseAssignMaster FROM CourseAssignMaster courseAssignMaster WHERE courseAssignMaster.deleted = 0 and courseAssignMaster.companyId.id=:companyId")
	List<CourseAssignMaster> findAllByCompanyId(@Param("companyId") Integer companyId);
	
	@Query("SELECT courseAssignMaster FROM CourseAssignMaster courseAssignMaster WHERE courseAssignMaster.deleted = 0 and courseAssignMaster.officeId.id=:officeId")
	List<CourseAssignMaster> findAllByOfficeId(@Param("officeId") Integer officeId);
	
	@Query("SELECT courseAssignMaster FROM CourseAssignMaster courseAssignMaster WHERE courseAssignMaster.deleted = 0 and courseAssignMaster.deptId.id=:deptId")
	List<CourseAssignMaster> findAllByDeptId(@Param("deptId") Integer deptId);
	
	@Query("SELECT courseAssignMaster FROM CourseAssignMaster courseAssignMaster WHERE courseAssignMaster.deleted = 0 and courseAssignMaster.divisionId.id=:divisionId")
	List<CourseAssignMaster> findAllByDivisionId(@Param("divisionId") Integer divisionId);

	
//	@Query("SELECT CASE WHEN COUNT(courseAssignMaster) > 0 THEN true ELSE false END from CourseAssignMaster courseAssignMaster where courseAssignMaster.companyId.id=:companyId AND courseAssignMaster.officeId.id=:officeId AND courseAssignMaster.deptId.id=:deptId AND courseAssignMaster.divisionId.id=:divisionId AND courseAssignMaster.courseId.id=:courseId AND courseAssignMaster.language=:language")
//	Boolean findCompanyIdOfficeIdDivisionIdDeptIdCourseIdExist(@Param("companyId") Integer companyId, @Param("officeId") Integer officeId, @Param("divisionId") Integer divisionId,@Param("deptId") Integer deptId, @Param("courseId") Integer courseId, @Param("language") Integer language);
}
