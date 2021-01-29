package com.cinfy.mlearning.model.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.CourseAttempt;


@Repository
@Transactional
public interface CourseAttemptRepository extends JpaRepository<CourseAttempt, Integer>{
	
		
	@Query("select c from CourseAttempt c" )
	List<CourseAttempt> findAll();

	List<CourseAttempt> findById(Integer id);
	
	List<CourseAttempt> findByCreatedDate(Date attemptDate);

	@Query("select q from CourseAttempt q where  q.userId.id=:userId" )
	List<CourseAttempt> findCourseAttemptByUserId(@Param("userId") Integer userId);

	@Query("select q from CourseAttempt q where q.courseModuleId.id=:courseModuleId" )
	List<CourseAttempt> findCourseAttemptByCourseModuleId(@Param("courseModuleId") Integer courseModuleId);

	@Query("select count(q) from CourseAttempt q where q.userId.id=:userId and q.courseModuleId.id=:courseModuleId" )
	Long countByUserIdCourseModuleId(@Param("userId") Integer userId, @Param("courseModuleId") Integer id);

	@Query("select q from CourseAttempt q where q.userId.id=:userId and q.courseModuleId.id=:courseModuleId" )
	CourseAttempt findByUserIdCourseModuleId(@Param("userId") Integer userId, @Param("courseModuleId") Integer id);
	
	@Query("select count(q) from CourseAttempt q where q.userId.id=:userId" )
	Long countByUserId(@Param("userId") Integer userId);

	@Query("select count(q) from CourseAttempt q where q.userId.id=:userId and q.courseModuleId.courseId.courseCategoryId.id=:courseCategoryId" )
	Long countByUserIdAndCategory(@Param("userId") Integer userId, @Param("courseCategoryId") Integer id);

  
}
