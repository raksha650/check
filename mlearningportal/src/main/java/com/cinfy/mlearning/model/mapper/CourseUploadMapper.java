package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.CourseUpload;
import com.cinfy.mlearning.model.common.CourseUploadPayload;

@Mapper(uses = {CourseModuleMapper.class})
public interface CourseUploadMapper {
	
	CourseUploadMapper INSTANCE = Mappers.getMapper(CourseUploadMapper.class);

	@Mappings({

			@Mapping(source = "id", target = "id")

	})

	CourseUpload courseUploadPayloadToCourseUploadEntity(CourseUploadPayload courseUploadPayload);

	@InheritInverseConfiguration(name = "courseUploadPayloadToCourseUploadEntity")
	CourseUploadPayload courseUploadEntityToCourseUploadPayload(CourseUpload courseUpload);

}
