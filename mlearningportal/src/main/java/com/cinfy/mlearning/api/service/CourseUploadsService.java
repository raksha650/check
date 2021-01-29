package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.CourseUpload;
import com.cinfy.mlearning.model.common.CourseUploadPayload;
import com.cinfy.mlearning.model.mapper.CourseUploadMapper;
import com.cinfy.mlearning.model.repositories.CourseUploadRepository;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CourseUploadsService {
	public static final Logger logger = LoggerFactory.getLogger(CourseUploadsService.class);

	private static final int ERROR_CODE_CLASS = 1600;

	
	@Autowired
	CourseUploadRepository courseUploadRepository;

	

	public GenericResponse findById(Integer id) {
		GenericResponse response = new GenericResponse();
		response.setStatus(0);
		try {
			
			CourseUpload courseUpload = this.courseUploadRepository.findById(id);
			
			if (courseUpload != null) {				
				CourseUploadPayload courseUploadPayload = CourseUploadMapper.INSTANCE
						.courseUploadEntityToCourseUploadPayload(courseUpload);
				response.setData(courseUploadPayload);
				response.setStatus(1);
			}else {
				logger.info("Id does not exist:" + id);
				response.setStatus(0);
				response.setError(new ErrorMessage(ERROR_CODE_CLASS, "Id does not exist :" + id));
			}
		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("sales.save.update"), ex.getMessage()));
		}
		return response;
	}

	

	public GenericResponse getCourseUploadList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details .... ");
		List<CourseUploadPayload> result = null;
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			Integer courseModuleId = (Integer) params.get("courseModuleId");
			Integer id = (Integer) params.get("id");
			Integer language = (Integer) params.get("language");
			
			// Response convert to customers into List
			List<CourseUpload> courseUploads = null;
			if (courseModuleId != null) {
				courseUploads = this.courseUploadRepository.findByCourseModuleId1(courseModuleId);				
			} else if (id != null) {
				courseUploads = this.courseUploadRepository.listbyId(id);
			} 
			
		

			if (courseUploads != null && !courseUploads.isEmpty()) {
				result = new ArrayList<>();
				for (CourseUpload s : courseUploads) {
					
					
					
					CourseUploadPayload courseUploadPayload = CourseUploadMapper.INSTANCE
							.courseUploadEntityToCourseUploadPayload(s);
					
					
					result.add(courseUploadPayload);
				}
			}
			response.setData(result);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("sales.save.update"), ex.getMessage()));
		}
		return response;
	}

}
