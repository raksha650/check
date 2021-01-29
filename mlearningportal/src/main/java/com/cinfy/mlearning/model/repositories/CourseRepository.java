package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.Course;

@Repository
@Transactional
public abstract interface CourseRepository extends JpaRepository<Course, Integer> {

	@SuppressWarnings("unchecked")
	Course save(Course course);
	


//	@Query("SELECT c.id,c.courseType FROM Course c WHERE c.id = :id")	
//	Course findById(@Param("id") Integer id);
	
	@Query("select course from Course course where course.deleted = 0 order by commonId desc")
	List<Course> findAllOrderByCommonId();
	
	@Query("select course from Course course where course.courseCategoryId.commonId=:commonId and  course.deleted = 0 order by commonId desc")
	List<Course> findAllOrderByCourseCategoryIdCommonId(@Param("commonId") Long commonId);
	
	@Query("select course from Course course where course.id =:id")
	List<Course> getListById(@Param("id")  Integer id);
	
	Course findById(@Param("id") Integer id);
	
	@Query("select course from Course course where course.deleted = 0 and course.commonId = :commonId order by commonId desc")
	List<Course> findByCommonId(@Param("commonId") Long commonId);
	
/*	@Query("select course from Course course where course.deleted = 0 and course.courseType like %:courseType%")
	Course findByCourseTypeAllIgnoreCase(@Param("courseType") String courseType);*/

	@Query("select course from Course course where course.deleted = 0 and course.language=:language")
	List<Course> findAllByLanguage(@Param("language") Integer language);

	@Modifying
	@Query("update Course course set course.deleted = 1 where course.commonId = :commonId ")
	void courseDeleteCommon(@Param("commonId") Long commonId);	

	@Query("SELECT CASE WHEN COUNT(course) > 0 THEN true ELSE false END FROM Course course WHERE course.courseCategoryId.id=:courseCategoryId AND course.name=:course")
    boolean existsByCourseCategoryIdAndCourseName(@Param("courseCategoryId") Integer courseCategoryId, @Param("course") String course);

}
