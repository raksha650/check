package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.CourseAttempt;
import com.cinfy.mlearning.model.CourseViewLogDetails;
import com.cinfy.mlearning.model.common.CourseViewLogDetailsPayload;
import com.cinfy.mlearning.model.mapper.CourseViewLogDetailsMapper;
import com.cinfy.mlearning.model.repositories.CourseAttemptRepository;
import com.cinfy.mlearning.model.repositories.CourseViewLogDetailsRepository;
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
public class CourseViewLogDetailsService {
	public static final Logger logger = LoggerFactory.getLogger(CourseViewLogDetailsService.class);

	private static final int ERROR_CODE_CLASS = 1600;

	@Autowired
	private CourseViewLogDetailsRepository courseViewLogDetailsRepository;
	
	@Autowired
	private CourseAttemptRepository courseAttemptRepository;

	public GenericResponse getList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details .... ");
		List<CourseViewLogDetailsPayload> result = null;
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			Date viewDate = (Date) params.get("viewDate");

			Integer userId = (Integer) params.get("userId");

			Integer courseModuleId = (Integer) params.get("courseModuleId");

			List<CourseViewLogDetails> list = null;
			if (viewDate != null) {
				list = this.courseViewLogDetailsRepository.findByViewDate(viewDate);
			} else if (userId != null) {
				list = this.courseViewLogDetailsRepository.findCourseAttemptByUserId(userId);
			} else if (courseModuleId != null) {
				list = this.courseViewLogDetailsRepository.findByCourseModuleId(courseModuleId);
			} else {
				list = this.courseViewLogDetailsRepository.findAll();
			}

			if (list != null && !list.isEmpty()) {
				result = new ArrayList<>();
				for (CourseViewLogDetails s : list) {
					CourseViewLogDetailsPayload payload = CourseViewLogDetailsMapper.INSTANCE
							.courseViewLogDetailsEntityToCourseViewLogDetailsPayload(s);
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

	public GenericResponse saveAndUpdate(CourseViewLogDetailsPayload courseViewLogDetailsPayload) {
		GenericResponse response = new GenericResponse();
		logger.info("Request data object123:");
		response.setStatus(1);
		try {
			CourseAttempt courseAttempt= new CourseAttempt();
			CourseViewLogDetails courseViewLogDetails = CourseViewLogDetailsMapper.INSTANCE
					.courseViewLogDetailsPayloadToCourseViewLogDetailsEntity(courseViewLogDetailsPayload);
			
		     // to update course attempt
				courseAttempt= courseAttemptRepository.findByUserIdCourseModuleId(courseViewLogDetails.getUserId().getUserId(), courseViewLogDetails.getCourseModuleId().getId());
				if(courseAttempt!=null) {
					if(!courseAttempt.getIsCourseCompleted()) {
						courseAttempt.setIsCourseCompleted(courseViewLogDetails.getIsCourseFinished());
						courseAttempt.setUpdatedDate(new Date());
					}
					
				}else if(courseAttempt==null) {
					courseAttempt= new CourseAttempt(courseViewLogDetails.getUserId(), courseViewLogDetails.getCourseModuleId(), courseViewLogDetails.getIsCourseFinished(),
							null, null, new Date(), new Date());					
				}
				courseAttemptRepository.save(courseAttempt);
				// to update course attempt end

			
			
			courseViewLogDetails = this.courseViewLogDetailsRepository.save(courseViewLogDetails);

			courseViewLogDetailsPayload = CourseViewLogDetailsMapper.INSTANCE
					.courseViewLogDetailsEntityToCourseViewLogDetailsPayload(courseViewLogDetails);

			response.setData(courseViewLogDetailsPayload);

		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(
					new ErrorMessage(ERROR_CODE_CLASS, "Unable to process the create request.", ex.getMessage()));
		}

		return response;
	}

}
