package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.CourseExam;
import com.cinfy.mlearning.model.common.CourseExamPayload;
import com.cinfy.mlearning.model.mapper.CourseExamMapper;
import com.cinfy.mlearning.model.repositories.CourseAttemptRepository;
import com.cinfy.mlearning.model.repositories.CourseExamRepository;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CourseExamService {
	public static final Logger logger = LoggerFactory.getLogger(CourseExamService.class);

	private static final int ERROR_CODE_CLASS = 1600;

	@Autowired
	private CourseExamRepository courseExamRepository;
	
	@Autowired
	private CourseAttemptRepository courseAttemptRepository;

	public GenericResponse getList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details .... ");
		List<CourseExamPayload> result = null;
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {

			Integer userId = (Integer) params.get("userId");

			Integer courseModuleId = (Integer) params.get("courseModuleId");
			logger.info("Into Dept Details fetch details in service:UserId-{},courseModuleId-{}",userId, courseModuleId);
			// Response convert to countries into List
			List<CourseExam> quesList = null;
			if (userId != null) {
				quesList = this.courseExamRepository.findCourseExamByUserId(userId);
			} else if (courseModuleId != null) {
				logger.info("aaagaya .... "+courseModuleId);
				quesList = this.courseExamRepository.getBySubjectMasterId(courseModuleId);
			} else {
				quesList = this.courseExamRepository.findAll();
			}

			if (quesList != null && !quesList.isEmpty()) {
				result = new ArrayList<>();
				for (CourseExam s : quesList) {
					CourseExamPayload payload = CourseExamMapper.INSTANCE.courseExamEntityToCourseExamPayload(s);
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

	public GenericResponse saveAndUpdate(CourseExamPayload courseExamPayload) {
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			CourseExam courseExam = CourseExamMapper.INSTANCE.courseExamPayloadToCourseExamEntity(courseExamPayload);

			Long attemptNo = courseExamRepository.countByUserIdSubjectMasterId(courseExam.getUserId().getUserId(),courseExam.getCourseModuleId().getId()); 
			courseExam.setAttemptNo(Integer.parseInt(attemptNo.toString())+1);
			courseExam = this.courseExamRepository.save(courseExam);
			
//			if(courseExam.getId()!=null) {
//			CourseAttempt courseAttempt=new CourseAttempt();
//			courseAttempt.setAttemptDate(courseExam.getExamDate());
//			courseAttempt.setSubjectMasterId(courseExam.getSubjectMasterId());
//			courseAttempt.setTimeTaken(courseExam.getTotalTime());
//			courseAttempt.setUserId(courseExam.getUserId());
//			courseAttemptRepository.save(courseAttempt);
//			}

			courseExamPayload = CourseExamMapper.INSTANCE.courseExamEntityToCourseExamPayload(courseExam);

			response.setData(courseExamPayload);

		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(
					new ErrorMessage(ERROR_CODE_CLASS, "Unable to process the create request.", ex.getMessage()));
		}

		return response;
	}

}
