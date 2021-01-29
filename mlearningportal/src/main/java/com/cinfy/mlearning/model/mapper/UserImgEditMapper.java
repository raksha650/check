package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.UserImgEdit;
import com.cinfy.mlearning.model.common.UserImgEditPayload;

@Mapper
public interface UserImgEditMapper {	
		
		UserImgEditMapper INSTANCE = Mappers.getMapper(UserImgEditMapper.class);
		
		@Mappings({
			
			@Mapping(source = "userId", target = "userId"),
			@Mapping(source = "imgPath", target = "imgPath"),
			@Mapping(source = "imgName", target = "imgName")
			
		})
		
		UserImgEdit userImgEditPayloadToUserImgEditEntity(UserImgEditPayload userImgEditPayload);
		
		@InheritInverseConfiguration(name="userImgEditPayloadToUserImgEditEntity")
		UserImgEditPayload userImgEditEntityToUserImgEditPayload(UserImgEdit userImgEdit);

}
