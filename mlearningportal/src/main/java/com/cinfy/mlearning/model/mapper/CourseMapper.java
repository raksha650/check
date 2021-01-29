package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.common.CoursePayload;

@Mapper(uses = {CourseCategoryMapper.class})
public interface CourseMapper {
	
	CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
	
	@Mappings({
		
		@Mapping(source = "id", target = "id")
		
	})
	
	Course coursePayloadToCourseEntity(CoursePayload coursePayload);
	
	@InheritInverseConfiguration(name="coursePayloadToCourseEntity")
	CoursePayload courseEntityToCoursePayload(Course course);

}
