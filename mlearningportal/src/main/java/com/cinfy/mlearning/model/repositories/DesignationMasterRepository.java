package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.DesignationMaster;

@Repository
@Transactional
public interface DesignationMasterRepository extends JpaRepository<DesignationMaster, Integer>{
	
	
	
	List<DesignationMaster> findByNameAllIgnoreCase(String subDeptName);
	
	@Query("select designationMaster from DesignationMaster designationMaster" )
	List<DesignationMaster> findAll();

	DesignationMaster findById(Integer id);
	
	@Query("select designationMaster from DesignationMaster designationMaster where designationMaster.id=:id" )
	List<DesignationMaster> findByIdList(Integer id);

	@Query("select designationMaster from DesignationMaster designationMaster where designationMaster.language=:language and designationMaster.deleted=0 order by id desc" )
	List<DesignationMaster> findByLanguage(@Param("language") Integer language);

	@Query("select designationMaster from DesignationMaster designationMaster where designationMaster.deleted = 0 and designationMaster.commonId = :commonId order by commonId desc")
	List<DesignationMaster> findByCommonId(@Param("commonId") Long commonId);

	@Query("select designationMaster from DesignationMaster designationMaster where designationMaster.deleted = 0 order by commonId desc")
	List<DesignationMaster> findAllOrderByCommonId();

//	@Query("select designationMaster from DesignationMaster designationMaster where designationMaster.deleted = 0 and designationMaster.deptId.commonId=:deptCommonId order by designationMaster.commonId desc")
//	List<DesignationMaster> findAllByDeptCommonId(@Param("deptCommonId") Long deptCommonId);
	
	
	@Modifying
	@Query("update DesignationMaster designationMaster set designationMaster.deleted = 1 where designationMaster.commonId = :commonId ")
	void designationMasterDeleteCommon(@Param("commonId") Long commonId);
	
//	@Query("SELECT CASE WHEN COUNT(designationMaster) > 0 THEN true ELSE false END FROM DesignationMaster designationMaster WHERE designationMaster.deptId.id=:deptId AND designationMaster.name=:designation")
//    boolean existsByDeptIdAndDesignationName(@Param("deptId") Integer deptId, @Param("designation") String designation);

	@Query("SELECT CASE WHEN COUNT(designationMaster) > 0 THEN true ELSE false END FROM DesignationMaster designationMaster WHERE designationMaster.name=:designation")
    boolean existsByDesignationName(@Param("designation") String designation);

	
}
