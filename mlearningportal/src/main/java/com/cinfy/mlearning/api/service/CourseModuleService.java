package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.CourseModule;
import com.cinfy.mlearning.model.common.CourseModulePayload;
import com.cinfy.mlearning.model.mapper.CourseModuleMapper;
import com.cinfy.mlearning.model.repositories.CourseAttemptRepository;
import com.cinfy.mlearning.model.repositories.CourseModuleRepository;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.model.response.CourseModuleResponse;
import com.cinfy.mlearning.utils.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CourseModuleService {
	public static final Logger logger = LoggerFactory.getLogger(CourseModuleService.class);

	private static final int ERROR_CODE_CLASS = 1600;
	
	@Autowired
	private CourseAttemptRepository courseAttemptRepository;

	@Autowired
	private CourseModuleRepository courseModuleRepository;

	

	public GenericResponse findById(Integer id) {
		GenericResponse response = new GenericResponse();
		response.setStatus(0);
		try {
			
			CourseModule courseModule = this.courseModuleRepository.findById(id);
			
			if (courseModule != null) {				
				CourseModulePayload courseModulePayload = CourseModuleMapper.INSTANCE
						.courseModuleEntityToCourseModulePayload(courseModule);
				response.setData(courseModulePayload);
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

	/**
	 * 
	 * @param payload
	 * @return
	 */
	@Transactional
	public GenericResponse saveOrUpdate(CourseModulePayload payload) {

		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		
		try {
			
			if(payload.getName() != null) {				
			
				CourseModule courseModule = CourseModuleMapper.INSTANCE
					.courseModulePayloadToCourseModuleEntity(payload);

				CourseModule account = this.courseModuleRepository
					.findByNameAllIgnoreCase(courseModule.getName());

			if (account == null) {
				courseModule = this.courseModuleRepository.save(courseModule);

				CourseModuleResponse courseModuleResponse = new CourseModuleResponse(courseModule.getId(),
						courseModule.getName(), "Subject Master created successfully");

				// coursecategoryResponse.setMessage("Course Category created successfully.");

				// Final response
				response.setStatus(1);
				response.setData(courseModuleResponse);
			} else {
				logger.info("Subject exist:" + courseModule.getName());
				response.setStatus(0);
				response.setError(new ErrorMessage(ERROR_CODE_CLASS, "Subject exist :" + courseModule.getName()));
			}
			}else {
				logger.info("This course type not found:" + payload.getName());
				response.setStatus(0);
				response.setError(new ErrorMessage(ERROR_CODE_CLASS, "Null Values"));
			}
			
		}catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("user.login.failed"), ex.getMessage()));
		}		

		return response;
	}

//	@Transactional
//	public GenericResponse updateDelete(CourseModuleDeleteRequest courseModuleDeleteRequest) {
//		logger.info("Course Category delete by ID: [{}]", courseModuleDeleteRequest.getId());
//		GenericResponse response = new GenericResponse();
//		response.setStatus(1);
//		try {
//			List<Integer> ids = courseModuleDeleteRequest.getId().stream().map(Integer::valueOf)
//					.collect(Collectors.toList());
//			this.courseModuleRepository.updateDeleted(ids);
//			// this.cu.updateItemsDeleteFlag(ids);
//		} catch (Exception ex) {
//			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
//			response.setStatus(0);
//			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("sales.save.update"), ex.getMessage()));
//		}
//		return response;
//
//	}

	public GenericResponse getCourseModuleList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details .... ");
		List<CourseModulePayload> result = null;
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			String name = params.get("name") != null ? params.get("name").toString() : "";
			Integer courseId= (Integer) params.get("courseId");
			Integer id= (Integer) params.get("id");
			Integer language= (Integer) params.get("language");
			Integer userId = (Integer)params.get("userId");
			// Response convert to customers into List
			List<CourseModule> courseModules = null;
			if (name != null && !name.isEmpty()) {
				courseModules = this.courseModuleRepository.findByName(name);
			} else if (courseId != null ) {
				courseModules = this.courseModuleRepository.findSubjectBycourseId(courseId);
			}else if (id != null ) {
				courseModules = this.courseModuleRepository.getListById(id);
			}else if (language != null ) {
				courseModules = this.courseModuleRepository.findByLanguage(language);
			} 

			if (courseModules != null && !courseModules.isEmpty()) {
				result = new ArrayList<>();
				for (CourseModule s : courseModules) {
					CourseModulePayload courseModulePayload = CourseModuleMapper.INSTANCE
							.courseModuleEntityToCourseModulePayload(s);
					if(userId!=null) {
					courseModulePayload.setIsRead(Integer.parseInt(courseAttemptRepository.countByUserIdCourseModuleId(userId, courseModulePayload.getId()).toString())>0?true:false);
					}
					result.add(courseModulePayload);
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

}
