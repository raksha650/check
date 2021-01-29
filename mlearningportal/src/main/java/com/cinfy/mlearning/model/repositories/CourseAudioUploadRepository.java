package com.cinfy.mlearning.model.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.CourseAudioUpload;

@Repository
@Transactional
public interface CourseAudioUploadRepository extends CrudRepository<CourseAudioUpload, Integer> {

	@SuppressWarnings("unchecked")
	CourseAudioUpload save(CourseAudioUpload courseAudioUpload);
	
	
	/*@Modifying
	@Query("update CourseUpload courseUpload set courseUpload.audioPath in (?1) where courseUpload.courseContentId.id = :id")
	void update(@Param("id") Integer id, List<String> paths);*/

	/*@Query("SELECT c FROM CourseContent c WHERE c.id = :id")	
	CourseContent findById(@Param("id") Integer id);
	
	//CourseContent findById(Integer id);
	
	CourseContent findBySubjectMasterId(CourseModule subjectId);
	
	//List<CourseContent> findBySubjectMasterId(Integer courseModuleId);

	@Query("select courseContent from CourseContent courseContent")
	List<CourseContent> findAll();

	@Modifying
	@Query("delete from CourseContent courseContent where id in (?1) ")
	void updateDeleted(List<Integer> ids);*/

	/*@Query("select courseContent from CourseContent courseContent where courseContent.courseType like %:courseType% ")
	List<CourseContent> findByCourseTypeName(@Param("courseType") String courseType);*/

	

}
