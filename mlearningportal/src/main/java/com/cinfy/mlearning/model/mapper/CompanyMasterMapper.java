package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.CompanyMaster;
import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.common.CompanyMasterPayload;
import com.cinfy.mlearning.model.common.DeptMasterPayload;

@Mapper
public interface CompanyMasterMapper {	
		
		CompanyMasterMapper INSTANCE = Mappers.getMapper(CompanyMasterMapper.class);
		
		@Mappings({
			
			@Mapping(source = "id", target = "id")
			
		})
		
		CompanyMaster companyMasterPayloadToCompanyMasterEntity(CompanyMasterPayload companyMasterPayload);
		
		@InheritInverseConfiguration(name="companyMasterPayloadToCompanyMasterEntity")
		CompanyMasterPayload companyMasterEntityToCompanyMasterPayload(CompanyMaster companyMaster);

}
