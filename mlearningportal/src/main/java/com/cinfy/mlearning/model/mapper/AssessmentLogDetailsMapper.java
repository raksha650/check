package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.AssessmentLogDetails;
import com.cinfy.mlearning.model.common.AssessmentLogDetailsPayload;

@Mapper(uses = {UserNewMapper.class, CourseModuleMapper.class})
public interface AssessmentLogDetailsMapper {
	
	AssessmentLogDetailsMapper INSTANCE = Mappers.getMapper(AssessmentLogDetailsMapper.class);
	
	@Mappings({
		
		@Mapping(source = "id", target = "id")
		
	})
	
	AssessmentLogDetails assessmentLogDetailsPayloadToAssessmentLogDetailsEntity(AssessmentLogDetailsPayload assessmentLogDetailsPayload);
	
	@InheritInverseConfiguration(name="assessmentLogDetailsPayloadToAssessmentLogDetailsEntity")
	AssessmentLogDetailsPayload assessmentLogDetailsEntityToAssessmentLogDetailsPayload(AssessmentLogDetails assessmentLogDetails);

}
