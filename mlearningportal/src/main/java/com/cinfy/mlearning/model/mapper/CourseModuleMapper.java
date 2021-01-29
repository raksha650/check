package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.CourseModule;
import com.cinfy.mlearning.model.common.CourseModulePayload;

@Mapper(uses = {CourseMapper.class})
public interface CourseModuleMapper {

	CourseModuleMapper INSTANCE = Mappers.getMapper(CourseModuleMapper.class);

	@Mappings({

			@Mapping(source = "id", target = "id"),
			
		
			

	})

	CourseModule courseModulePayloadToCourseModuleEntity(CourseModulePayload courseModulePayload);

	@InheritInverseConfiguration(name = "courseModulePayloadToCourseModuleEntity")
	CourseModulePayload courseModuleEntityToCourseModulePayload(CourseModule courseModule);

}
