package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.DivisionMaster;
import com.cinfy.mlearning.model.common.DivisionMasterPayload;

@Mapper(uses = {CompanyMasterMapper.class, OfficeMasterMapper.class, DeptMasterMapper.class})
public interface DivisionMasterMapper {	
		
		DivisionMasterMapper INSTANCE = Mappers.getMapper(DivisionMasterMapper.class);
		
		@Mappings({
			
			@Mapping(source = "id", target = "id")
			
		})
		
		DivisionMaster divisionMasterPayloadToDivisionMasterEntity(DivisionMasterPayload divisionMasterPayload);
		
		@InheritInverseConfiguration(name="divisionMasterPayloadToDivisionMasterEntity")
		DivisionMasterPayload divisionMasterEntityToDivisionMasterPayload(DivisionMaster divisionMaster);

}
