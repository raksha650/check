package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.CourseContent;
import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.Question;
import com.cinfy.mlearning.model.common.DeptMasterPayload;
import com.cinfy.mlearning.model.common.QuestionPayload;
import com.cinfy.mlearning.model.mapper.DeptMasterMapper;
import com.cinfy.mlearning.model.mapper.QuestionMapper;
import com.cinfy.mlearning.model.repositories.CourseContentRepository;
import com.cinfy.mlearning.model.repositories.DeptMasterRepository;
import com.cinfy.mlearning.model.repositories.QuestionRepository;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.Messages;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class QuestionService {
	public static final Logger logger = LoggerFactory.getLogger(QuestionService.class);

	private static final int ERROR_CODE_CLASS = 1600;

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	CourseContentRepository courseContentRepository;


	public GenericResponse getList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details .... ");
		List<QuestionPayload> result = null;
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			String question = params.get("question") != null ? params.get("question").toString() : "";
			
			Integer userId =  (Integer) params.get("userId");
			Integer id =  (Integer) params.get("id");
			
			Integer courseModuleId =  (Integer) params.get("courseModuleId");
			CourseContent courseContent = courseContentRepository.findByCourseModuleIdId(courseModuleId);
			
			// Response convert to countries into List
			List<Question> quesList = null;
			if (question != null && !question.isEmpty()) {
				quesList =  this.questionRepository.findByQuestionAllIgnoreCase(question);	
			}else if(id != null){				
				quesList =  this.questionRepository.listById(id);				
			}else if(courseModuleId != null){				
				quesList =  this.questionRepository.findQuestionBycourseModuleId(courseModuleId);
				quesList = getRandomQuestions(quesList, courseContent.getTotalAssessmentQuestion());
			} else {
				quesList = this.questionRepository.findAll();
			}

			if (quesList != null && !quesList.isEmpty()) {
				result = new ArrayList<>();
				for (Question s : quesList) {
					QuestionPayload payload = QuestionMapper.INSTANCE
							.questionEntityToQuestionPayload(s);
					result.add(payload);
				}
			}
			response.setData(result);
		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("sales.save.update"), ex.getMessage()));
		}
		return response;
	}
	
	public List<Question> getRandomQuestions(List<Question> questions, int numberOfQuestions) {
	    List<Question> randomQuestions = new ArrayList<>();
	    List<Question> copy = new ArrayList<>(questions);

	    SecureRandom rand = new SecureRandom();
	    for (int i = 0; i < Math.min(numberOfQuestions, questions.size()); i++) {
	        randomQuestions.add( copy.remove( rand.nextInt( copy.size() ) ));
	    }

	    return randomQuestions;
	}

}
