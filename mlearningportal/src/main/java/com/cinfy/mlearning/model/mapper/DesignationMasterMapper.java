package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.DesignationMaster;
import com.cinfy.mlearning.model.common.DesignationMasterPayload;

@Mapper(uses = {DeptMasterMapper.class})
public interface DesignationMasterMapper {	
		
		DesignationMasterMapper INSTANCE = Mappers.getMapper(DesignationMasterMapper.class);
		
		@Mappings({
			
			@Mapping(source = "id", target = "id")
			
		})
		
		DesignationMaster designationMasterPayloadToDesignationMasterEntity(DesignationMasterPayload designationPayload);
		
		@InheritInverseConfiguration(name="designationMasterPayloadToDesignationMasterEntity")
		DesignationMasterPayload designationMasterEntityToDesignationPayload(DesignationMaster designationMaster);

}
