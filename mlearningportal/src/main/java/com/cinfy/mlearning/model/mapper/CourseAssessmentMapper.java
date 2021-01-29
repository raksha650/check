package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.CourseAssessment;
import com.cinfy.mlearning.model.common.CourseAssessmentPayload;

@Mapper(uses = {CourseModuleMapper.class})
public interface CourseAssessmentMapper {
	
	CourseAssessmentMapper INSTANCE = Mappers.getMapper(CourseAssessmentMapper.class);

	@Mappings({

			@Mapping(source = "id", target = "id")

	})

	CourseAssessment courseAssessmentPayloadToCourseAssessmentEntity(CourseAssessmentPayload courseAssessmentPayload);

	@InheritInverseConfiguration(name = "courseAssessmentPayloadToCourseAssessmentEntity")
	CourseAssessmentPayload courseAssessmentEntityToCourseAssessmentPayload(CourseAssessment courseAssessment);

}
