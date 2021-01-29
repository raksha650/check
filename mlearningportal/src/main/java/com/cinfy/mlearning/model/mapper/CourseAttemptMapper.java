package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.CourseAttempt;
import com.cinfy.mlearning.model.common.CourseAttemptPayload;

@Mapper(uses = {UserNewMapper.class,CourseModuleMapper.class})
public interface CourseAttemptMapper {

	CourseAttemptMapper INSTANCE = Mappers.getMapper(CourseAttemptMapper.class);

	@Mappings({

			@Mapping(source = "id", target = "id")

	})

	CourseAttempt courseAttemptPayloadToCourseAttemptEntity(CourseAttemptPayload courseAttemptPayload);

	@InheritInverseConfiguration(name = "courseAttemptPayloadToCourseAttemptEntity")
	CourseAttemptPayload courseAttemptEntityToCourseAttemptPayload(CourseAttempt courseAttempt);

}
