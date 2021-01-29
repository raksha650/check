package com.cinfy.mlearning.model.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.AssessmentLogDetails;

@Repository
@Transactional
public interface AssessmentLogDetailsRepository extends JpaRepository<AssessmentLogDetails, Integer> {

	@Query("select c from AssessmentLogDetails c")
	List<AssessmentLogDetails> findAll();

	List<AssessmentLogDetails> findById(Integer id);

	List<AssessmentLogDetails> findByAssessmentDate(Date assessmentDate);

	@Query("select q from AssessmentLogDetails q where  q.userId.id=:userId")
	List<AssessmentLogDetails> findCourseAttemptByUserId(@Param("userId") Integer userId);

	@Query("select q from AssessmentLogDetails q where q.courseModuleId.id=:courseModuleId")
	List<AssessmentLogDetails> findByCourseModuleId(@Param("courseModuleId") Integer courseModuleId);
	
	@Query("select q from AssessmentLogDetails q where q.userId.id=:userId")
	List<AssessmentLogDetails> findByUserId(@Param("userId") Integer userId);

	@Query("select q from AssessmentLogDetails q where q.userId.id=:userId and q.courseModuleId.id=:courseModuleId")
	List<AssessmentLogDetails> countByUserIdIdAndCourseModuleIdId(@Param("userId") Integer userId, @Param("courseModuleId") Integer id);
	
	@Query("select count(q) from AssessmentLogDetails q where q.userId.id=:userId and q.courseModuleId.id=:courseModuleId")
	Long countByUserIdCourseModuleId(@Param("userId") Integer userId, @Param("courseModuleId") Integer id);

	@Query("select q from AssessmentLogDetails q where q.userId.id=:userId and q.courseModuleId.id=:courseModuleId")
	AssessmentLogDetails findByUserIdCourseModuleId(@Param("userId") Integer userId,
			@Param("courseModuleId") Integer id);

	@Query("select count(q) from AssessmentLogDetails q where q.userId.id=:userId")
	Long countByUserId(@Param("userId") Integer userId);

	// @Query("select count(q) from CourseAttempt q where q.userId.id=:userId and
	// q.courseModuleId.courseId.courseCategoryId.id=:courseCategoryId" )
	// Long countByUserIdAndCategory(@Param("userId") Integer userId,
	// @Param("courseCategoryId") Integer id);

}
