package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.CourseCategory;

@Repository
@Transactional
public abstract interface CourseCategoryRepository extends JpaRepository<CourseCategory, Integer> {

	@SuppressWarnings("unchecked")
	CourseCategory save(CourseCategory courseCategory);
	


//	@Query("SELECT c.id,c.courseType FROM CourseCategory c WHERE c.id = :id")	
//	CourseCategory findById(@Param("id") Integer id);
	
	@Query("select courseCategory from CourseCategory courseCategory where courseCategory.deleted = 0 order by commonId desc")
	List<CourseCategory> findAllOrderByCommonId();
	
	@Query("select courseCategory from CourseCategory courseCategory where courseCategory.id =:id")
	List<CourseCategory> getListById(@Param("id")  Integer id);
	
	CourseCategory findById(@Param("id") Integer id);
	
	@Query("select courseCategory from CourseCategory courseCategory where courseCategory.deleted = 0 and courseCategory.commonId = :commonId order by commonId desc")
	List<CourseCategory> findByCommonId(@Param("commonId") Long commonId);
	
	@Query("select courseCategory from CourseCategory courseCategory where courseCategory.deleted = 0 and courseCategory.courseType like %:courseType%")
	CourseCategory findByCourseTypeAllIgnoreCase(@Param("courseType") String courseType);

	@Query("select courseCategory from CourseCategory courseCategory where courseCategory.deleted = 0 and courseCategory.language=:language")
	List<CourseCategory> findAllByLanguage(@Param("language") Integer language);

	/*@Modifying
	@Query("delete from CourseCategory courseCategory where  id in (?1) ")
	void updateDeleted(List<Integer> ids);
	
	@Modifying
	@Query("delete from CourseCategory courseCategory where courseCategory.id = :id ")
	void courseCategoryDelete(@Param("id") Integer id);	
	*/
	@Modifying
	@Query("update CourseCategory courseCategory set courseCategory.deleted = 1 where courseCategory.commonId = :commonId ")
	void courseCategoryDeleteCommon(@Param("commonId") Long commonId);	

	@Query("select courseCategory from CourseCategory courseCategory where courseCategory.deleted = 0 and courseCategory.courseType like %:courseType% ")
	List<CourseCategory> findByCourseTypeName(@Param("courseType") String courseType);
	
	@Query("SELECT CASE WHEN COUNT(courseCategory) > 0 THEN true ELSE false END FROM CourseCategory courseCategory WHERE courseCategory.courseType=:courseTypeName")
    boolean existsByCourseTypeName(@Param("courseTypeName") String courseTypeName);

	

}
