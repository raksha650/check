package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.CourseExam;
import com.cinfy.mlearning.model.common.CourseExamPayload;

@Mapper(uses = {UserNewMapper.class,CourseModuleMapper.class})
public interface CourseExamMapper {

	CourseExamMapper INSTANCE = Mappers.getMapper(CourseExamMapper.class);

	@Mappings({

			@Mapping(source = "id", target = "id")

	})

	CourseExam courseExamPayloadToCourseExamEntity(CourseExamPayload courseExamPayload);

	@InheritInverseConfiguration(name = "courseExamPayloadToCourseExamEntity")
	CourseExamPayload courseExamEntityToCourseExamPayload(CourseExam courseExam);

}
