package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.common.UserNewPayload;

@Mapper(uses = {CompanyMasterMapper.class, OfficeMasterMapper.class, DeptMasterMapper.class,
		DivisionMasterMapper.class, DesignationMasterMapper.class, DistrictMasterMapper.class})
public interface UserNewMapper {
	
	UserNewMapper INSTANCE = Mappers.getMapper(UserNewMapper.class);
	
	@Mappings({
		
		@Mapping(source = "userId", target = "userId")
		
	})
	
	UserNew userNewPayloadToUserNewEntity(UserNewPayload userNewPayload);
	
	@InheritInverseConfiguration(name="userNewPayloadToUserNewEntity")
	UserNewPayload userNewEntityToUserNewPayload(UserNew userNew);

}
