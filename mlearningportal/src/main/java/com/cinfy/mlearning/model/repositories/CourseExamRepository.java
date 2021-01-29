package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.CourseExam;

@Repository
@Transactional
public interface CourseExamRepository extends JpaRepository<CourseExam, Integer>{
	
		
	@Query("select c from CourseExam c" )
	List<CourseExam> findAll();

	List<CourseExam> findById(Integer id);
	

	@Query("select q from CourseExam q where q.userId.id=:userId" )
	List<CourseExam> findCourseExamByUserId(@Param("userId") Integer userId);
	
	@Query("select q from CourseExam q where q.courseModuleId.id=:courseModuleId" )
	List<CourseExam> getBySubjectMasterId(@Param("courseModuleId") Integer courseModuleId);
	
	@Query("select count(q) from CourseExam q where q.userId.id=:userId and q.courseModuleId.id=:courseModuleId" )
	Long countByUserIdSubjectMasterId(@Param("userId") Integer userId, @Param("courseModuleId") Integer courseModuleId);

	
}
