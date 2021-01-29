package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.OfficeMaster;

@Repository
@Transactional
public interface OfficeMasterRepository extends JpaRepository<OfficeMaster, Integer> {

	List<OfficeMaster> findByNameAllIgnoreCase(String subDeptName);

	@Query("select officeMaster from OfficeMaster officeMaster")
	List<OfficeMaster> findAll();

	OfficeMaster findById(Integer id);

	@Query("select officeMaster from OfficeMaster officeMaster where officeMaster.id=:id")
	List<OfficeMaster> findByIdList(Integer id);

	@Query("select officeMaster from OfficeMaster officeMaster where officeMaster.language=:language and officeMaster.deleted=0 order by id desc")
	List<OfficeMaster> findByLanguage(@Param("language") Integer language);

	@Query("select officeMaster from OfficeMaster officeMaster where officeMaster.deleted = 0 and officeMaster.commonId = :commonId order by commonId desc")
	List<OfficeMaster> findByCommonId(@Param("commonId") Long commonId);

	@Query("select officeMaster from OfficeMaster officeMaster where officeMaster.deleted = 0 order by commonId desc")
	List<OfficeMaster> findAllOrderByCommonId();

	@Query("select officeMaster from OfficeMaster officeMaster where officeMaster.deleted = 0 and officeMaster.companyId.commonId=:companyCommonId order by officeMaster.commonId desc")
	List<OfficeMaster> findAllByCompanyCommonId(@Param("companyCommonId") Long companyCommonId);

	@Modifying
	@Query("update OfficeMaster officeMaster set officeMaster.deleted = 1 where officeMaster.commonId = :commonId ")
	void officeMasterDeleteCommon(@Param("commonId") Long commonId);

	@Query("SELECT CASE WHEN COUNT(officeMaster) > 0 THEN true ELSE false END FROM OfficeMaster officeMaster WHERE officeMaster.companyId.id=:companyId AND officeMaster.name=:office")
	boolean existsByCompanyIdAndOfficeName(@Param("companyId") Integer companyId, @Param("office") String office);

}
