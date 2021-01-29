package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.DeptMaster;

@Repository
@Transactional
public interface DeptMasterRepository extends JpaRepository<DeptMaster, Integer>{
		
	List<DeptMaster> findByDeptNameAllIgnoreCase(String deptName);
	
	@Query("select deptMaster from DeptMaster deptMaster" )
	List<DeptMaster> findAll();

	DeptMaster findById(Integer id);
	
	@Query("select deptMaster from DeptMaster deptMaster where deptMaster.id=:id" )
	List<DeptMaster> findByIdList(Integer id);

	@Query("select deptMaster from DeptMaster deptMaster where deptMaster.language=:language and deptMaster.deleted=0 order by id desc" )
	List<DeptMaster> findByLanguage(@Param("language") Integer language);

	@Query("select deptMaster from DeptMaster deptMaster where deptMaster.deleted = 0 and deptMaster.commonId = :commonId order by commonId desc")
	List<DeptMaster> findByCommonId(@Param("commonId") Long commonId);
	
	@Query("select deptMaster from DeptMaster deptMaster where deptMaster.deleted = 0 and deptMaster.companyId.id = :companyId order by commonId desc")
	List<DeptMaster> findByCompanyId(@Param("companyId") Integer companyId);

	@Query("select deptMaster from DeptMaster deptMaster where deptMaster.deleted = 0 order by commonId desc")
	List<DeptMaster> findAllOrderByCommonId();
	
	@Query("select deptMaster from DeptMaster deptMaster where deptMaster.deleted = 0 and deptMaster.officeId.commonId=:officeCommonId order by deptMaster.commonId desc")
	List<DeptMaster> findAllByOfficeCommonId(@Param("officeCommonId") Long officeCommonId);

	@Modifying
	@Query("update DeptMaster deptMaster set deptMaster.deleted = 1 where deptMaster.commonId = :commonId ")
	void deptMasterDeleteCommon(@Param("commonId") Long commonId);
		
	@Query("SELECT CASE WHEN COUNT(deptMaster) > 0 THEN true ELSE false END FROM DeptMaster deptMaster WHERE deptMaster.companyId.id=:companyId AND deptMaster.officeId.id=:officeId AND deptMaster.deptName=:deptName")
    boolean existsByCompanyIdOfficeIdAndDeptName(@Param("companyId") Integer companyId,@Param("officeId") Integer officeId, @Param("deptName") String deptName);
	
}
