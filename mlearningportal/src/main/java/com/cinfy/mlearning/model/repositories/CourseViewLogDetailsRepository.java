package com.cinfy.mlearning.model.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.CourseModule;
import com.cinfy.mlearning.model.CourseViewLogDetails;
import com.cinfy.mlearning.model.UserNew;


@Repository
@Transactional
public interface CourseViewLogDetailsRepository extends JpaRepository<CourseViewLogDetails, Integer>{
	
		
	@Query("select c from CourseViewLogDetails c" )
	List<CourseViewLogDetails> findAll();

	List<CourseViewLogDetails> findById(Integer id);
	
	List<CourseViewLogDetails> findByViewDate(Date viewDate);

	@Query("select q from CourseViewLogDetails q where  q.userId.id=:userId" )
	List<CourseViewLogDetails> findCourseAttemptByUserId(@Param("userId") Integer userId);

	@Query("select q from CourseViewLogDetails q where q.courseModuleId.id=:courseModuleId" )
	List<CourseViewLogDetails> findByCourseModuleId(@Param("courseModuleId") Integer courseModuleId);
	
	@Query("select q from CourseViewLogDetails q where q.userId.userId=:userId" )
	List<CourseViewLogDetails> findByUserId(@Param("userId") Integer userId);

	@Query("select count(q) from CourseViewLogDetails q where q.userId.id=:userId and q.courseModuleId.id=:courseModuleId" )
	Long countByUserIdCourseModuleId(@Param("userId") Integer userId, @Param("courseModuleId") Integer id);

	@Query("select q from CourseViewLogDetails q where q.userId.id=:userId and q.courseModuleId.id=:courseModuleId" )
	List<CourseViewLogDetails> findByUserIdIdAndCourseModuleIdId(@Param("userId") Integer userId, @Param("courseModuleId") Integer id);
	
	@Query("select q from CourseViewLogDetails q where q.userId.id=:userId and q.courseModuleId.id=:courseModuleId" )
	CourseViewLogDetails findByUserIdCourseModuleId(@Param("userId") Integer userId, @Param("courseModuleId") Integer id);
	
	@Query("select count(q) from CourseViewLogDetails q where q.userId.id=:userId" )
	Long countByUserId(@Param("userId") Integer userId);

//	@Query("select count(q) from CourseAttempt q where q.userId.id=:userId and q.courseModuleId.courseId.courseCategoryId.id=:courseCategoryId" )
//	Long countByUserIdAndCategory(@Param("userId") Integer userId, @Param("courseCategoryId") Integer id);

  
}
