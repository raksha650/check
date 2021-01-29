package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.AssessmentLogDetails;
import com.cinfy.mlearning.model.CourseAttempt;
import com.cinfy.mlearning.model.common.AssessmentLogDetailsPayload;
import com.cinfy.mlearning.model.mapper.AssessmentLogDetailsMapper;
import com.cinfy.mlearning.model.repositories.AssessmentLogDetailsRepository;
import com.cinfy.mlearning.model.repositories.CourseAttemptRepository;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.Messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AssessmentLogDetailsService {
	public static final Logger logger = LoggerFactory.getLogger(AssessmentLogDetailsService.class);

	private static final int ERROR_CODE_CLASS = 1600;

	@Autowired
	private AssessmentLogDetailsRepository assessmentLogDetailsRepository;

	@Autowired
	private CourseAttemptRepository courseAttemptRepository;

	public GenericResponse getList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details .... ");
		List<AssessmentLogDetailsPayload> result = null;
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			Date assessmentDate = (Date) params.get("assessmentDate");

			Integer userId = (Integer) params.get("userId");

			Integer courseModuleId = (Integer) params.get("courseModuleId");

			List<AssessmentLogDetails> list = null;
			if (assessmentDate != null) {
				list = this.assessmentLogDetailsRepository.findByAssessmentDate(assessmentDate);
			} else if (userId != null) {
				list = this.assessmentLogDetailsRepository.findCourseAttemptByUserId(userId);
			} else if (courseModuleId != null) {
				list = this.assessmentLogDetailsRepository.findByCourseModuleId(courseModuleId);
			} else {
				list = this.assessmentLogDetailsRepository.findAll();
			}

			if (list != null && !list.isEmpty()) {
				result = new ArrayList<>();
				for (AssessmentLogDetails s : list) {
					AssessmentLogDetailsPayload payload = AssessmentLogDetailsMapper.INSTANCE
							.assessmentLogDetailsEntityToAssessmentLogDetailsPayload(s);
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

	public GenericResponse saveAndUpdate(AssessmentLogDetailsPayload assessmentLogDetailsPayload) {
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			
			AssessmentLogDetails assessmentLogDetails = AssessmentLogDetailsMapper.INSTANCE
					.assessmentLogDetailsPayloadToAssessmentLogDetailsEntity(assessmentLogDetailsPayload);

			// to update course attempt
			CourseAttempt courseAttempt = new CourseAttempt();
			courseAttempt = courseAttemptRepository.findByUserIdCourseModuleId(assessmentLogDetails.getUserId().getUserId(),
					assessmentLogDetails.getCourseModuleId().getId());
			if (courseAttempt != null) {
				if (courseAttempt.getIsAssessmentPass() == null) {

					courseAttempt.setIsAssessmentPass(assessmentLogDetails.getStatus().equals("pass") ? true : false);
					courseAttempt.setAssessmentMarks(assessmentLogDetails.getQuestionCorrected() + "/" + assessmentLogDetails.getTotalNoOfQuestion());
					courseAttempt.setUpdatedDate(new Date());

				} else if (!courseAttempt.getIsAssessmentPass()) {

					courseAttempt.setIsAssessmentPass(assessmentLogDetails.getStatus().equals("pass") ? true : false);
					courseAttempt.setAssessmentMarks(assessmentLogDetails.getQuestionCorrected() + "/" + assessmentLogDetails.getTotalNoOfQuestion());
					courseAttempt.setUpdatedDate(new Date());

				}

			}
			courseAttemptRepository.save(courseAttempt);
			// to update course attempt end

			assessmentLogDetails = this.assessmentLogDetailsRepository.save(assessmentLogDetails);

			assessmentLogDetailsPayload = AssessmentLogDetailsMapper.INSTANCE
					.assessmentLogDetailsEntityToAssessmentLogDetailsPayload(assessmentLogDetails);

			response.setData(assessmentLogDetailsPayload);

		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(
					new ErrorMessage(ERROR_CODE_CLASS, "Unable to process the create request.", ex.getMessage()));
		}

		return response;
	}

}
