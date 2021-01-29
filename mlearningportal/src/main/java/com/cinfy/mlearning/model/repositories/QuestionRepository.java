package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.Question;

@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	@SuppressWarnings("unchecked")
	Question save(Question question);

	@Query("select q from Question q where q.deleted=0")
	List<Question> findAll();

	List<Question> findById(Integer id);

	@Query("select q from Question q where q.id=:id")
	List<Question> listById(@Param("id") Integer id);

	@Query("select q from Question q where q.deleted=0 and q.question like %:question%")
	List<Question> findByQuestionAllIgnoreCase(@Param("question") String question);

	// @Query("select q from Question q where q.deleted=0 and q.userId.id=:userId" )
	// List<Question> findQuestionByUserId(@Param("userId") Integer userId);

	@Query("select q from Question q where q.deleted=0 and q.courseModuleId.id=:courseModuleId")
	List<Question> findQuestionBycourseModuleId(@Param("courseModuleId") Integer courseModuleId);
	
	//@Query(value = "select * from Question order by ORDER BY RAND() LIMIT 1", nativeQuery = true)
//	@Query("SELECT u FROM Question u order by function('RAND'),")
//	List<Question> findQuestion();

	@Query("select q from Question q where q.deleted = 0 and q.commonId = :commonId order by commonId desc")
	List<Question> findByCommonId(@Param("commonId") Long commonId);

	@Query("select q from Question q where q.deleted = 0 order by commonId desc")
	List<Question> findAllOrderByCommonId();

	@Modifying
	@Query("update Question q set q.deleted = 1 where q.commonId = :commonId ")
	void questionDeleteCommon(@Param("commonId") Long commonId);

}
