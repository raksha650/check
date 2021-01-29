package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.CourseCategory;
import com.cinfy.mlearning.model.common.CourseCategoryPayload;

@Mapper
public interface CourseCategoryMapper {
	
	CourseCategoryMapper INSTANCE = Mappers.getMapper(CourseCategoryMapper.class);
	
	@Mappings({
		
		@Mapping(source = "id", target = "id")
		
	})
	
	CourseCategory courseCategoryPayloadToCourseCategoryEntity(CourseCategoryPayload courseCategoryPayload);
	
	@InheritInverseConfiguration(name="courseCategoryPayloadToCourseCategoryEntity")
	CourseCategoryPayload courseCategoryEntityToCourseCategoryPayload(CourseCategory courseCategory);

}
