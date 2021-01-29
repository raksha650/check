package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.CompanyMaster;
@Repository
@Transactional
public interface CompanyMasterRepository extends JpaRepository<CompanyMaster, Integer> {
	
	CompanyMaster findById(Integer id);
	
	@Query("select companyMaster from CompanyMaster companyMaster where companyMaster.deleted = 0 order by commonId desc")
	List<CompanyMaster> findAllOrderByCommonId();
	
	@Query("select companyMaster from CompanyMaster companyMaster where companyMaster.deleted = 0 and companyMaster.commonId = :commonId order by commonId desc")
	List<CompanyMaster> findByCommonId(@Param("commonId") Long commonId);
	
	@Modifying
	@Query("update CompanyMaster companyMaster set companyMaster.deleted = 1 where companyMaster.commonId = :commonId ")
	void companyMasterDeleteCommon(@Param("commonId") Long commonId);	


}
