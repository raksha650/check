package com.cinfy.mlearning.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinfy.mlearning.model.UserNew;

@Repository
@Transactional
public interface UserNewRegisterRepository extends JpaRepository<UserNew, Integer>{
	
	
	UserNew findByEmailOrPhoneAllIgnoreCase (String email, String phone);
	
	
	
	@Modifying
	@Query("update UserNew u set u.profileImagePath=:profileImagePath where u.userId=:userId")
	void updateUserImage(@Param("profileImagePath") String profileImagePath,@Param("userId") Integer userId);
	
	@Query("SELECT CASE WHEN COUNT(account) > 0 THEN true ELSE false END FROM UserNew account WHERE account.email=:email")
    boolean existsByEmail(@Param("email") String email);
	
	@Query("SELECT CASE WHEN COUNT(account) > 0 THEN true ELSE false END FROM UserNew account WHERE account.phone=:contact")
    boolean existsByMobile(@Param("contact") String contact);
	
	@Query("select account from UserNew account where account.userId=(?1) and account.deleted = 0" )
	UserNew findById(Integer id);
	
	//@Query("select userDetail from UserDetail userDetail where userDetail.id =:id")
	List<UserNew> findByUserId(Integer userId);
	
	List<UserNew> findByEmail(String email);
	
	List<UserNew> findByPhone(String phone);
	
	
	@Query("select account from UserNew account where account.phone=(?1) and account.deleted = 0" )
	UserNew findOneByPhone(String phone);
	
	@Query("select account from UserNew account where upper(account.password)=upper(?1) "+
        " and ( upper(account.email)=upper(?2)  or upper(account.phone)=upper(?3) ) and upper(account.role)=upper(?4) and account.deleted = 0" )
	UserNew findByPasswordAndEmailOrPhoneAndRoleAllIgnoreCase (String password, String email, String phone, String role);
	
	
	@Query("select account from UserNew account where "+
	        " ( upper(account.email)=upper(?2)  or upper(account.phone)=upper(?3) ) and upper(account.role)=upper(?4) and account.deleted = 0" )
		UserNew findByEmailOrPhoneAndRoleAllIgnoreCase (String email, String phone, String role);
		
	
	@Query("select account from UserNew account where account.deleted = 0 and account.deptId.commonId=:deptCommonId and account.deptId.language=1 order by account.id desc")
	List<UserNew> findAllByDeptCommonId(@Param("deptCommonId") Long deptCommonId);
	
	UserNew findByUserIdAndDeleted(Integer userId,Integer deleted);
	
	/*
	 * @Query("select account from User account where account.email =:email and account.status =:status"
	 * ) User findByEmailAndS (@Param("email") String email, @Param("status") Short
	 * status);
	 */
	
	@Query("select account from UserNew account where account.deleted = 0" )
	List<UserNew> findAllUser();
	
	@Query("SELECT account FROM UserNew account WHERE account.deleted = 0 AND account.companyId.id=:companyId" )
	List<UserNew> findAllUserByCompanyId(@Param("companyId") Integer companyId);
	
	@Query("SELECT account FROM UserNew account WHERE account.deleted = 0 AND account.companyId.id=:companyId AND account.officeId.id=:officeId AND account.deptId.id=:deptId AND account.divisionId.id=:divisionId" )
	List<UserNew> findAllUserByCompanyIdOfficeIdDeptIdDivisionId(@Param("companyId") Integer companyId, @Param("officeId") Integer officeId, @Param("deptId") Integer deptId, @Param("divisionId") Integer divisionId);
	
	@Query("SELECT account FROM UserNew account WHERE account.deleted = 0 AND account.companyId.id=:companyId AND account.officeId.id=:officeId AND account.deptId.id=:deptId" )
	List<UserNew> findAllUserByCompanyIdOfficeIdDeptId(@Param("companyId") Integer companyId, @Param("officeId") Integer officeId, @Param("deptId") Integer deptId);

	@Query("SELECT account FROM UserNew account WHERE account.deleted = 0 AND account.companyId.id=:companyId AND account.officeId.id=:officeId" )
	List<UserNew> findAllUserByCompanyIdOfficeId(@Param("companyId") Integer companyId, @Param("officeId") Integer officeId);

	@Query("select count(account.id) from UserNew account where account.deleted = 0" )
	Integer findAllUserCount();
  
}
