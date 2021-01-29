package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.CourseViewLogDetails;
import com.cinfy.mlearning.model.common.CourseViewLogDetailsPayload;

@Mapper(uses = {UserNewMapper.class, CourseModuleMapper.class})
public interface CourseViewLogDetailsMapper {
	
	CourseViewLogDetailsMapper INSTANCE = Mappers.getMapper(CourseViewLogDetailsMapper.class);
	
	@Mappings({
		
		@Mapping(source = "id", target = "id")
		
	})
	
	CourseViewLogDetails courseViewLogDetailsPayloadToCourseViewLogDetailsEntity(CourseViewLogDetailsPayload courseViewLogDetailsPayload);
	
	@InheritInverseConfiguration(name="courseViewLogDetailsPayloadToCourseViewLogDetailsEntity")
	CourseViewLogDetailsPayload courseViewLogDetailsEntityToCourseViewLogDetailsPayload(CourseViewLogDetails courseViewLogDetails);

}
