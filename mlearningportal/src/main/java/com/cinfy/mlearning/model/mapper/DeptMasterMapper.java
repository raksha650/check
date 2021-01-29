package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.common.DeptMasterPayload;

@Mapper(uses = {OfficeMasterMapper.class, CompanyMasterMapper.class})
public interface DeptMasterMapper {	
		
		DeptMasterMapper INSTANCE = Mappers.getMapper(DeptMasterMapper.class);
		
		@Mappings({
			
			@Mapping(source = "id", target = "id")
			
		})
		
		DeptMaster deptPayloadToDeptMasterEntity(DeptMasterPayload deptPayload);
		
		@InheritInverseConfiguration(name="deptPayloadToDeptMasterEntity")
		DeptMasterPayload deptMasterEntityToDeptPayload(DeptMaster deptMaster);

}
