package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.Question;
import com.cinfy.mlearning.model.common.QuestionPayload;

@Mapper(uses = {UserNewMapper.class,CourseModuleMapper.class})
public interface QuestionMapper {

	QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

	@Mappings({

			@Mapping(source = "id", target = "id")

	})

	Question questionPayloadToQuestionEntity(QuestionPayload questionPayload);

	@InheritInverseConfiguration(name = "questionPayloadToQuestionEntity")
	QuestionPayload questionEntityToQuestionPayload(Question question);

}
