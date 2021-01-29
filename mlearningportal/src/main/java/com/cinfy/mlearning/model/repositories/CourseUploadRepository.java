package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.CourseUpload;
import com.cinfy.mlearning.model.CourseModule;

@Repository
@Transactional
public interface CourseUploadRepository extends CrudRepository<CourseUpload, Integer> {

	@SuppressWarnings("unchecked")
	CourseUpload save(CourseUpload courseUpload);
	
	@Query("SELECT c FROM CourseUpload c WHERE c.courseModuleId =:courseModuleId and deleted=0")	
	List<CourseUpload> findByCourseModuleId(@Param("courseModuleId") CourseModule courseModuleId);
	
	
	
	@Query("SELECT c FROM CourseUpload c WHERE c.courseModuleId.id = :courseModuleId  and deleted=0")
	List<CourseUpload>  findByCourseModuleId1(@Param("courseModuleId") Integer courseModuleId);
	
	@Query("SELECT c FROM CourseUpload c WHERE c.id =:id")	
	List<CourseUpload> listbyId(@Param("id") Integer id);
	
	/*@Modifying
	@Query("update CourseUpload courseUpload set courseUpload.audioPath in (?1) where courseUpload.courseContentId.id = :id")
	void update(@Param("id") Integer id, List<String> paths);*/

	@Query("SELECT c FROM CourseUpload c WHERE c.id = :id")	
	CourseUpload findById(@Param("id") Integer id);
	
	@Modifying
	@Query("delete from CourseUpload c where c.id = :id ")
	void courseUploadDelete(@Param("id") Integer id);
	
	/*//CourseContent findById(Integer id);
	
	CourseContent findBySubjectMasterId(CourseModule subjectId);
	
	

	@Query("select courseContent from CourseContent courseContent")
	List<CourseContent> findAll();

	@Modifying
	@Query("delete from CourseContent courseContent where id in (?1) ")
	void updateDeleted(List<Integer> ids);*/

	/*@Query("select courseContent from CourseContent courseContent where courseContent.courseType like %:courseType% ")
	List<CourseContent> findByCourseTypeName(@Param("courseType") String courseType);*/

	

}
