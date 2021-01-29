package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.OfficeMaster;
import com.cinfy.mlearning.model.common.OfficeMasterPayload;

@Mapper(uses = {CompanyMasterMapper.class})
public interface OfficeMasterMapper {	
		
		OfficeMasterMapper INSTANCE = Mappers.getMapper(OfficeMasterMapper.class);
		
		@Mappings({
			
			@Mapping(source = "id", target = "id")
			
		})
		
		OfficeMaster officeMasterPayloadToOfficeMasterEntity(OfficeMasterPayload officeMasterPayload);
		
		@InheritInverseConfiguration(name="officeMasterPayloadToOfficeMasterEntity")
		OfficeMasterPayload officeMasterEntityToOfficeMasterPayload(OfficeMaster officeMaster);

}
