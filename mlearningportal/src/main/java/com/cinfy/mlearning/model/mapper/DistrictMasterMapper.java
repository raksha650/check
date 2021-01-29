package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.District;
import com.cinfy.mlearning.model.common.DistrictMasterPayload;

@Mapper
public interface DistrictMasterMapper {	
		
		DistrictMasterMapper INSTANCE = Mappers.getMapper(DistrictMasterMapper.class);
		
		@Mappings({
			
			@Mapping(source = "id", target = "id")
			
		})
		
		District districtMasterPayloadToDistrictEntity(DistrictMasterPayload districtMasterPayload);
		
		@InheritInverseConfiguration(name="districtMasterPayloadToDistrictEntity")
		DistrictMasterPayload districtEntityToDistrictMasterPayload(District district);

}
