package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.CourseContent;
import com.cinfy.mlearning.model.CourseUpload;
import com.cinfy.mlearning.model.common.CourseContentPayload;
import com.cinfy.mlearning.model.mapper.CourseContentMapper;
import com.cinfy.mlearning.model.repositories.CourseContentRepository;
import com.cinfy.mlearning.model.repositories.CourseUploadRepository;
import com.cinfy.mlearning.model.request.CourseContentDeleteRequest;
import com.cinfy.mlearning.model.response.CourseContentResponse;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.Messages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CourseContentService {
	public static final Logger logger = LoggerFactory.getLogger(CourseContentService.class);

	private static final int ERROR_CODE_CLASS = 1600;

	@Autowired
	CourseContentRepository courseContentRepository;
	
	
	

	public GenericResponse findById(Integer id) {
		GenericResponse response = new GenericResponse();
		response.setStatus(0);
		try {
			
			CourseContent courseContent = this.courseContentRepository.findById(id);
			
			if (courseContent != null) {				
				CourseContentPayload courseContentPayload = CourseContentMapper.INSTANCE
						.courseContentEntityToCourseContentPayload(courseContent);
				response.setData(courseContentPayload);
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

	

	@Transactional
	public GenericResponse updateDelete(CourseContentDeleteRequest courseContentDeleteRequest) {
		logger.info("Course Content delete by ID: [{}]", courseContentDeleteRequest.getId());
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			List<Integer> ids = courseContentDeleteRequest.getId().stream().map(Integer::valueOf)
					.collect(Collectors.toList());
			this.courseContentRepository.updateDeleted(ids);
			// this.cu.updateItemsDeleteFlag(ids);
		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("sales.save.update"), ex.getMessage()));
		}
		return response;

	}

	public GenericResponse getCourseContentList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details .... ");
		List<CourseContentPayload> result = null;
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			Integer courseModuleId = (Integer) params.get("courseModuleId");
			Integer id = (Integer) params.get("id");
			Integer language = (Integer) params.get("language");
			
			// Response convert to customers into List
			List<CourseContent> courseContents = null;
			if (courseModuleId != null) {
				courseContents = this.courseContentRepository.findByCourseModuleId1(courseModuleId);				
			} else if (id != null) {
				courseContents = this.courseContentRepository.listbyId(id);
			} 
			
		

			if (courseContents != null && !courseContents.isEmpty()) {
				result = new ArrayList<>();
				for (CourseContent s : courseContents) {
					
					List<CourseUpload> courseUploadCollection= new ArrayList<CourseUpload>();
					
					CourseContentPayload courseContentPayload = CourseContentMapper.INSTANCE
							.courseContentEntityToCourseContentPayload(s);
					
					//courseUploadCollection= courseUploadRepository.getByOnlyCourseModuleId(s.getCourseModuleId().getId());
					
					/*if(courseUploadCollection!=null) {
					courseContentPayload.setCourseUploadCollection(courseUploadCollection);
					}*/
					
					result.add(courseContentPayload);
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
