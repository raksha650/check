package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.CourseAudioUpload;
import com.cinfy.mlearning.model.common.CourseAudioUploadPayload;

@Mapper(uses = {CourseContentMapper.class})
public interface CourseAudioUploadMapper {
	
	CourseAudioUploadMapper INSTANCE = Mappers.getMapper(CourseAudioUploadMapper.class);

	@Mappings({

			@Mapping(source = "id", target = "id")

	})

	CourseAudioUpload courseAudioUploadPayloadToCourseAudioUploadEntity(CourseAudioUploadPayload courseAudioUploadPayload);

	@InheritInverseConfiguration(name = "courseAudioUploadPayloadToCourseAudioUploadEntity")
	CourseAudioUploadPayload courseAudioUploadEntityToCourseAudioUploadPayload(CourseAudioUpload courseAudioUpload);

}
