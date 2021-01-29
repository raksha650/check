package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.CourseContent;
import com.cinfy.mlearning.model.CourseModule;
@Repository
@Transactional
public interface CourseContentRepository extends JpaRepository<CourseContent, Integer> {

	@SuppressWarnings("unchecked")
	CourseContent save(CourseContent courseContent);
	
	
	/*@Query("SELECT c FROM CourseContent c WHERE c.deleted = 0 and c.language=:language")
	List<CourseContent> findByLanguage(@Param("language")Integer language);*/
	

	@Query("SELECT c FROM CourseContent c WHERE c.id = :id")	
	CourseContent findById(@Param("id") Integer id);
	
	@Query("SELECT c FROM CourseContent c WHERE c.id =:id ")	
	List<CourseContent> listbyId(@Param("id") Integer id);
	
	//CourseContent findById(Integer id);
	
	@Query("SELECT c FROM CourseContent c WHERE c.courseModuleId.id = :courseModuleId  and deleted=0")
	CourseContent  findByCourseModuleIdId(@Param("courseModuleId") Integer courseModuleId);
	
	@Query("SELECT c FROM CourseContent c WHERE c.courseModuleId.id = :courseModuleId  and deleted=0")
	List<CourseContent>  findByCourseModuleId1(@Param("courseModuleId") Integer courseModuleId);
	
	@Query("SELECT c FROM CourseContent c WHERE c.courseModuleId = :courseModuleId and deleted=0 ")
	CourseContent  findByCourseModuleId(@Param("courseModuleId") CourseModule courseModuleId);
	
	//List<CourseContent> findBySubjectMasterId(Integer courseModuleId);
	//List<CourseContent> findBySubjectMasterId();

//	@Query("select courseContent from CourseContent courseContent ")
//	List<CourseContent> findAll();
	/*
	@Query("select courseContent from CourseContent courseContent ")
	List<CourseContent> findAllByLanguage(@Param("language") Integer language);*/

	@Modifying
	@Query("delete from CourseContent courseContent where id in (?1) ")
	void updateDeleted(List<Integer> ids);
	
	@Modifying
	@Query("delete from CourseContent courseContent where courseContent.id = :id ")
	void courseContentDelete(@Param("id") Integer id);

	/*@Query("select q from CourseContent q where q.deleted = 0 and q.commonId = :commonId order by commonId desc")
	List<CourseContent> findByCommonId(@Param("commonId") Long commonId);*/

	/*@Query("select q from CourseContent q where q.deleted = 0 order by commonId desc")
	List<CourseContent> findAllOrderByCommonId();*/
	
	/*@Modifying
	@Query("update CourseContent q set q.deleted = 1 where q.commonId = :commonId ")
	void deleteCommon(@Param("commonId") Long commonId);*/

	

	

}
