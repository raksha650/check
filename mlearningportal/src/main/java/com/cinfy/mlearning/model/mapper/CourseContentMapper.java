package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.CourseContent;
import com.cinfy.mlearning.model.common.CourseContentPayload;

@Mapper(uses = {CourseModuleMapper.class})
public interface CourseContentMapper {
	
	CourseContentMapper INSTANCE = Mappers.getMapper(CourseContentMapper.class);

	@Mappings({

			@Mapping(source = "id", target = "id")

	})

	CourseContent courseContentPayloadToCourseContentEntity(CourseContentPayload courseContentPayload);

	@InheritInverseConfiguration(name = "courseContentPayloadToCourseContentEntity")
	CourseContentPayload courseContentEntityToCourseContentPayload(CourseContent courseContent);

}
