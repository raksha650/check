package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.CourseModule;
@Repository
@Transactional
public interface CourseModuleRepository extends JpaRepository<CourseModule, Integer> {

	@SuppressWarnings("unchecked")
	CourseModule save(CourseModule courseModule);

//	@Query("SELECT c.id,c.courseType FROM Course c WHERE c.id = :id")	
//	Course findById(@Param("id") Integer id);
	
	@Query("select count(course) from CourseModule course where course.deleted = 0 and course.courseId.deleted = 0 and language=1")
	Integer findAllCount();
	
	CourseModule findById(Integer id);
	
	@Query("select course from CourseModule course where course.id =:id ")
	CourseModule findByIntegerId(@Param("id") Integer id);
	
	@Query("select course from CourseModule course where course.id =:id ")
	List<CourseModule> getListById(@Param("id") Integer id);
	
	List<CourseModule> findByCourseId(Course id);
	
	@Query("select course from CourseModule course where course.deleted = 0 and course.name like %:name% ")
	CourseModule findByNameAllIgnoreCase(String name);

	@Query("select course from CourseModule course where course.deleted = 0 and course.language =:language order by course.id desc")
	List<CourseModule> findByLanguage(@Param("language")Integer language);

//	@Modifying
//	@Query("delete from CourseModule course where id in (?1) ")
//	void updateDeleted(List<Integer> ids);
	
	@Modifying
	@Query("delete from CourseModule course where course.id = :id ")
	void courseDelete(@Param("id") Integer id);	

	@Query("select course from CourseModule course where course.deleted = 0 and course.name like %:name% ")
	List<CourseModule> findByName(@Param("name") String name);
	
	@Query("select count(course) from CourseModule course where course.deleted = 0 and course.courseId.id =:courseId and course.language=1")
	Integer findCountBycourseId(@Param("courseId") Integer courseId);

	@Query("select course from CourseModule course where course.deleted = 0 and course.courseId.id =:courseId ")
	List<CourseModule> findSubjectBycourseId(@Param("courseId") Integer courseId);

	@Query("select course from CourseModule course where course.deleted = 0 and course.commonId = :commonId order by commonId desc")
	List<CourseModule> findByCommonId(@Param("commonId") Long commonId);
	
	@Query("select course from CourseModule course where course.deleted = 0 and course.courseId.commonId = :courseCommonId order by course.commonId desc")
	List<CourseModule> findByCourseCommonId(@Param("courseCommonId") Long courseCommonId);

	@Query("select course from CourseModule course where course.deleted = 0 order by commonId desc")
	List<CourseModule> findAllOrderByCommonId();

	@Modifying
	@Query("update CourseModule course set course.deleted = 1 where course.commonId = :commonId ")
	void courseDeleteCommon(@Param("commonId") Long commonId);

	@Query("select course from CourseModule course where course.deleted = 0 and course.courseId.commonId=:categoryCommonId order by course.commonId desc")
	List<CourseModule> findAllByCategoryCommonId(@Param("categoryCommonId") Long categoryCommonId);

	@Query("select count(course) from CourseModule course where course.deleted = 0 and course.courseId.id =:courseId ")
	Long countByCourseId(@Param("courseId") Integer courseId);	
	
	@Query("select count(course) from CourseModule course where course.deleted = 0 and course.courseId.courseCategoryId.id =:courseCategoryId ")
	Long countByCourseCategoryId(@Param("courseCategoryId") Integer courseCategoryId);	

	@Query("SELECT CASE WHEN COUNT(courseModule) > 0 THEN true ELSE false END FROM CourseModule courseModule WHERE  courseModule.deleted = 0 and courseModule.courseId.id=:courseId AND courseModule.name=:moduleName")
    boolean existsByCourseIdAndModuleName(@Param("courseId") Integer courseId, @Param("moduleName") String moduleName);


}
