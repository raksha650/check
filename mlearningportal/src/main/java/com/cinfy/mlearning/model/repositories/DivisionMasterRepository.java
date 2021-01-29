package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.DivisionMaster;

@Repository
@Transactional
public interface DivisionMasterRepository extends JpaRepository<DivisionMaster, Integer> {

	List<DivisionMaster> findByNameAllIgnoreCase(String divisionName);

	@Query("select divisionMaster from DivisionMaster divisionMaster")
	List<DivisionMaster> findAll();

	DivisionMaster findById(Integer id);

	@Query("select divisionMaster from DivisionMaster divisionMaster where divisionMaster.id=:id")
	List<DivisionMaster> findByIdList(Integer id);

	@Query("select divisionMaster from DivisionMaster divisionMaster where divisionMaster.language=:language and divisionMaster.deleted=0 order by id desc")
	List<DivisionMaster> findByLanguage(@Param("language") Integer language);

	@Query("select divisionMaster from DivisionMaster divisionMaster where divisionMaster.deleted = 0 and divisionMaster.commonId = :commonId order by commonId desc")
	List<DivisionMaster> findByCommonId(@Param("commonId") Long commonId);

//	@Query("select deptMaster from DivisionMaster divisionMaster where divisionMaster.deleted = 0 and divisionMaster.companyId.id = :companyId order by commonId desc")
//	List<DivisionMaster> findByCompanyId(@Param("companyId") Integer companyId);

	@Query("select divisionMaster from DivisionMaster divisionMaster where divisionMaster.deleted = 0 order by commonId desc")
	List<DivisionMaster> findAllOrderByCommonId();
	
	@Query("select divisionMaster from DivisionMaster divisionMaster where divisionMaster.deleted = 0 and divisionMaster.deptId.commonId=:deptCommonId order by divisionMaster.commonId desc")
	List<DivisionMaster> findAllByDeptCommonId(@Param("deptCommonId") Long deptCommonId);

	@Modifying
	@Query("update DivisionMaster divisionMaster set divisionMaster.deleted = 1 where divisionMaster.commonId = :commonId ")
	void deptMasterDeleteCommon(@Param("commonId") Long commonId);

	@Query("SELECT CASE WHEN COUNT(divisionMaster) > 0 THEN true ELSE false END FROM DivisionMaster divisionMaster WHERE divisionMaster.companyId.id=:companyId AND divisionMaster.officeId.id=:officeId AND divisionMaster.deptId.id=:deptId AND divisionMaster.name=:division")
    boolean existsByCompanyIdOfficeIdDeptIdAndDivisionName(@Param("companyId") Integer companyId,@Param("officeId") Integer officeId,@Param("deptId") Integer deptId, @Param("division") String division);


}
