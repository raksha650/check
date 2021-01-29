package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.CourseCategory;
import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.common.CourseCategoryPayload;
import com.cinfy.mlearning.model.mapper.CourseCategoryMapper;
import com.cinfy.mlearning.model.repositories.CourseAttemptRepository;
import com.cinfy.mlearning.model.repositories.CourseCategoryRepository;
import com.cinfy.mlearning.model.repositories.CourseModuleRepository;
import com.cinfy.mlearning.model.repositories.CourseRepository;
import com.cinfy.mlearning.model.repositories.UserNewRegisterRepository;
import com.cinfy.mlearning.model.request.CourseCategoryDeleteRequest;
import com.cinfy.mlearning.model.response.CoursecategoryResponse;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CourseCategoryService {
	public static final Logger logger = LoggerFactory.getLogger(CourseCategoryService.class);

	private static final int ERROR_CODE_CLASS = 1600;
	
	@Autowired
	private CourseModuleRepository courseModuleRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private UserNewRegisterRepository userNewRegisterRepository;
	
	@Autowired
	private CourseAttemptRepository courseAttemptRepository;
	
	@Autowired
	private CourseCategoryRepository courseCategoryRepository;
	
	

	public GenericResponse findById(Integer id) {
		GenericResponse response = new GenericResponse();
		response.setStatus(0);
		try {
			
			CourseCategory courseCategory = this.courseCategoryRepository.findById(id);
			
			if (courseCategory != null) {				
				CourseCategoryPayload courseCategoryPayload = CourseCategoryMapper.INSTANCE
						.courseCategoryEntityToCourseCategoryPayload(courseCategory);
				response.setData(courseCategoryPayload);
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
	
	
	
//	@Transactional
//	public boolean saveOrUpdate(CourseCategory courseCategory) {
//
//		try {
//			
//			this.courseCategoryRepository.saveOrUpdate(courseCategory);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//				
//
//		return true;
//	}
	
	@Transactional
	public GenericResponse saveOrUpdate(CourseCategoryPayload payload) {

		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		
		try {
			
			if(payload.getCourseType() != null) {				
			
			CourseCategory courseCategory = CourseCategoryMapper.INSTANCE
					.courseCategoryPayloadToCourseCategoryEntity(payload);

			CourseCategory account = this.courseCategoryRepository
					.findByCourseTypeAllIgnoreCase(courseCategory.getCourseType());

			if (account == null) {
				courseCategory = this.courseCategoryRepository.save(courseCategory);

				CoursecategoryResponse coursecategoryResponse = new CoursecategoryResponse(courseCategory.getId(),
						courseCategory.getCourseType(), "Course Category created successfully");

				// coursecategoryResponse.setMessage("Course Category created successfully.");

				// Final response
				response.setStatus(1);
				response.setData(coursecategoryResponse);
			} else {
				logger.info("Course Category exist:" + courseCategory.getCourseType());
				response.setStatus(0);
				response.setError(new ErrorMessage(ERROR_CODE_CLASS, "Course Category exist :" + courseCategory.getCourseType()));
			}
			}else {
				logger.info("This course type not found:" + payload.getCourseType());
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
//	public GenericResponse updateDelete(CourseCategoryDeleteRequest courseCategoryDeleteRequest) {
//		logger.info("Course Category delete by ID: [{}]", courseCategoryDeleteRequest.getId());
//		GenericResponse response = new GenericResponse();
//		response.setStatus(1);
//		try {
//			List<Integer> ids = courseCategoryDeleteRequest.getId().stream().map(Integer::valueOf)
//					.collect(Collectors.toList());
//			this.courseCategoryRepository.updateDeleted(ids);
//			// this.cu.updateItemsDeleteFlag(ids);
//		} catch (Exception ex) {
//			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
//			response.setStatus(0);
//			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("sales.save.update"), ex.getMessage()));
//		}
//		return response;
//
//	}

	public GenericResponse getCourseCategoryList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details .... ");
		List<CourseCategoryPayload> result = null;
		GenericResponse response = new GenericResponse();
		
		response.setStatus(1);
		try {
			/*String courseType = params.get("courseType") != null ? params.get("courseType").toString() : "";
			Integer id =  (Integer) params.get("id");*/
			Integer language = (Integer)params.get("language");
			Integer userId = (Integer)params.get("userId");
			
		
			//List<CourseCategory> courseCategories = null;
			List<CourseCategory> courseCategories=new ArrayList<CourseCategory>();
			
			if (userId!=null && language != null) {
				
				DeptMaster deptMaster = userNewRegisterRepository.findByUserIdAndDeleted(userId,0).getDeptId();
				
				
				
				//courseCategories  = deptCourseMasterRepository.findAllByDeptIdCommonIdAndDeptIdLanguage(deptMaster.getCommonId(),language);
				
				/*for (DeptCourseMaster deptCourseMaster2 : deptCourseMaster) {
					courseCategories.add(deptCourseMaster2.getCourseId().getCourseCategoryId());
				}*/
				
			//	courseCategories = this.courseCategoryRepository.findAllByLanguage(language);
			}

			if (courseCategories != null && !courseCategories.isEmpty()) {
				result = new ArrayList<>();
				for (CourseCategory s : courseCategories) {
					
					System.out.println("courseCategories_+_+"+s.toString());
					CourseCategoryPayload courseCategoryPayload = CourseCategoryMapper.INSTANCE
							.courseCategoryEntityToCourseCategoryPayload(s);
					
					//courseCategoryPayload.setTotalSubject(Integer.parseInt(courseModuleRepository.countByCourseCategoryId(courseCategoryPayload.getId()).toString()));
					if(userId!=null) {
					//courseCategoryPayload.setTotalSubjectRead(Integer.parseInt(courseAttemptRepository.countByUserIdAndCategory(userId,courseCategoryPayload.getId()).toString()));
					}
					result.add(courseCategoryPayload);
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

	public List<CourseCategory> findAll() {
		
		List<CourseCategory> list= courseCategoryRepository.findAll();
		
		return list;
	}

}
