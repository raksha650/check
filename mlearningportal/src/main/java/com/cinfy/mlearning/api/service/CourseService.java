package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.CourseAssignMaster;
import com.cinfy.mlearning.model.CourseModule;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.common.CourseModulePayload;
import com.cinfy.mlearning.model.common.CoursePayload;
import com.cinfy.mlearning.model.mapper.CourseMapper;
import com.cinfy.mlearning.model.mapper.CourseModuleMapper;
import com.cinfy.mlearning.model.repositories.CourseAssignMasterRepository;
import com.cinfy.mlearning.model.repositories.CourseRepository;
import com.cinfy.mlearning.model.repositories.UserNewRegisterRepository;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.Messages;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CourseService {
	public static final Logger logger = LoggerFactory.getLogger(CourseService.class);

	private static final int ERROR_CODE_CLASS = 1600;

	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private CourseAssignMasterRepository courseAssignMasterRepository;

	@Autowired
	private UserNewRegisterRepository userNewRegisterRepository;

	public GenericResponse findById(Integer id) {
		GenericResponse response = new GenericResponse();
		response.setStatus(0);
		try {

			Course course = this.courseRepository.findById(id);

			if (course != null) {
				CoursePayload coursePayload = CourseMapper.INSTANCE.courseEntityToCoursePayload(course);
				response.setData(coursePayload);
				response.setStatus(1);
			} else {
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

	public GenericResponse getCourseList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details123 .... ");
		List<CoursePayload> result = null;
		GenericResponse response = new GenericResponse();

		response.setStatus(1);
		try {
			Integer isCompliance = (Integer) params.get("isCompliance");
			Integer language = (Integer) params.get("language");
			Integer userId = (Integer) params.get("userId");

			List<Course> courseList = new ArrayList<Course>();
			List<Course> courseListTemp = new ArrayList<Course>();
			
			List<CourseAssignMaster> courseAssignList = new ArrayList<CourseAssignMaster>();
			
			//List<CourseModule> courseModuleList = new ArrayList<CourseModule>();

			if (userId != null && language != null && isCompliance != null) {

			
				UserNew user = userNewRegisterRepository.findByUserIdAndDeleted(userId, 0);
			

				//Check compliance
				if (isCompliance == 0) {
					try {
						if(user.getCompanyId() != null) {
							courseAssignList = courseAssignMasterRepository.findAllByCompanyId(user.getCompanyId().getId());
							for (CourseAssignMaster courseAssignMaster : courseAssignList) {
								if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() == null && courseAssignMaster.getDeptId() == null && courseAssignMaster.getDivisionId() == null) {
									
									courseListTemp = courseAssignMasterRepository
											.findAllByCompanyIdAndCompanyIdLanguageAndIsComplianceForCourseFetch(
													user.getCompanyId().getId(), language, false);
								}
							}
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
						if(user.getOfficeId() != null) {							
							courseAssignList = courseAssignMasterRepository.findAllByOfficeId(user.getOfficeId().getId());
							if(courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() != null && courseAssignMaster.getDeptId() == null && courseAssignMaster.getDivisionId() == null) {
										
										courseListTemp = courseAssignMasterRepository
												.findAllByOfficeIdAndOfficeIdLanguageAndIsComplianceForCourseFetch(
														user.getOfficeId().getId(), language, false);
									}
								}
							}
							
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
						if(user.getDeptId() != null) {							
							courseAssignList = courseAssignMasterRepository.findAllByDeptId(user.getDeptId().getId());
							if(courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {									
									if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() != null && courseAssignMaster.getDeptId() != null && courseAssignMaster.getDivisionId() == null) {
										
										courseListTemp = courseAssignMasterRepository
												.findAllByDeptIdAndDeptIdLanguageAndIsComplianceForCourseFetch(
														user.getDeptId().getId(), language, false);
									}
								}
							}
							
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
						if(user.getDivisionId() != null) {
							courseAssignList = courseAssignMasterRepository.findAllByDivisionId(user.getDivisionId().getId());
							if(courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() != null && courseAssignMaster.getDeptId() != null && courseAssignMaster.getDivisionId() != null) {
										
										courseListTemp = courseAssignMasterRepository
												.findAllByDivisionIdAndDivisionIdLanguageAndIsComplianceForCourseFetch(
														user.getDivisionId().getId(), language, false);
									}
								}
							}
							
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
					} catch (Exception e) {
						e.printStackTrace();
					}
					

					
				}else if (isCompliance == 1) {// for compliance
					courseList = new ArrayList<Course>();
					try {
						if(user.getCompanyId() != null) {
							courseAssignList = courseAssignMasterRepository.findAllByCompanyId(user.getCompanyId().getId());
							for (CourseAssignMaster courseAssignMaster : courseAssignList) {
								if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() == null && courseAssignMaster.getDeptId() == null && courseAssignMaster.getDivisionId() == null) {
									
									courseListTemp = courseAssignMasterRepository
											.findAllByCompanyIdAndCompanyIdLanguageAndIsComplianceForCourseFetch(
													user.getCompanyId().getId(), language, true);
								}
							}
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
						if(user.getOfficeId() != null) {							
							courseAssignList = courseAssignMasterRepository.findAllByOfficeId(user.getOfficeId().getId());
							if(courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() != null && courseAssignMaster.getDeptId() == null && courseAssignMaster.getDivisionId() == null) {
										
										courseListTemp = courseAssignMasterRepository
												.findAllByOfficeIdAndOfficeIdLanguageAndIsComplianceForCourseFetch(
														user.getOfficeId().getId(), language, true);
									}
								}
							}
							
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
						if(user.getDeptId() != null) {							
							courseAssignList = courseAssignMasterRepository.findAllByDeptId(user.getDeptId().getId());
							if(courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {									
									if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() != null && courseAssignMaster.getDeptId() != null && courseAssignMaster.getDivisionId() == null) {
										
										courseListTemp = courseAssignMasterRepository
												.findAllByDeptIdAndDeptIdLanguageAndIsComplianceForCourseFetch(
														user.getDeptId().getId(), language, true);
									}
								}
							}
							
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
						if(user.getDivisionId() != null) {
							courseAssignList = courseAssignMasterRepository.findAllByDivisionId(user.getDivisionId().getId());
							if(courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() != null && courseAssignMaster.getDeptId() != null && courseAssignMaster.getDivisionId() != null) {
										
										courseListTemp = courseAssignMasterRepository
												.findAllByDivisionIdAndDivisionIdLanguageAndIsComplianceForCourseFetch(
														user.getDivisionId().getId(), language, true);
									}
								}
							}
							
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
					} catch (Exception e) {
						e.printStackTrace();
					}
					

				}else if (isCompliance == 2) {// for pending (complete date in 7 days)
					courseList = new ArrayList<Course>();
					Calendar cal = Calendar.getInstance();
					Date today = cal.getTime();
					cal.add(Calendar.DAY_OF_MONTH, 7);
					Date expiryDate = cal.getTime();
					java.sql.Date nextDt = new java.sql.Date(expiryDate.getTime());
					java.sql.Date currentDt = new java.sql.Date(new Date().getTime());
					
					try {
						if(user.getCompanyId() != null) {
							courseAssignList = courseAssignMasterRepository.findAllByCompanyId(user.getCompanyId().getId());
							for (CourseAssignMaster courseAssignMaster : courseAssignList) {
								if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() == null && courseAssignMaster.getDeptId() == null && courseAssignMaster.getDivisionId() == null) {
									
									courseListTemp = courseAssignMasterRepository
											.findAllByCompanyIdAndCompanyIdLanguageAndComplianceDatewithin7daysForCourseFetch(
													user.getCompanyId().getId(), language, currentDt, nextDt);
								}
							}
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
						if(user.getOfficeId() != null) {							
							courseAssignList = courseAssignMasterRepository.findAllByOfficeId(user.getOfficeId().getId());
							if(courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() != null && courseAssignMaster.getDeptId() == null && courseAssignMaster.getDivisionId() == null) {
										
										courseListTemp = courseAssignMasterRepository
												.findAllByOfficeIdAndOfficeIdLanguageAndComplianceDatewithin7daysForCourseFetch(
														user.getOfficeId().getId(), language, currentDt, nextDt);
									}
								}
							}
							
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
						if(user.getDeptId() != null) {							
							courseAssignList = courseAssignMasterRepository.findAllByDeptId(user.getDeptId().getId());
							if(courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {									
									if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() != null && courseAssignMaster.getDeptId() != null && courseAssignMaster.getDivisionId() == null) {
										
										courseListTemp = courseAssignMasterRepository
												.findAllByDeptIdAndDeptIdLanguageAndComplianceDatewithin7daysForCourseFetch(
														user.getDeptId().getId(), language, currentDt, nextDt);
									}
								}
							}
							
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
						if(user.getDivisionId() != null) {
							courseAssignList = courseAssignMasterRepository.findAllByDivisionId(user.getDivisionId().getId());
							if(courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if(courseAssignMaster.getCompanyId() != null && courseAssignMaster.getOfficeId() != null && courseAssignMaster.getDeptId() != null && courseAssignMaster.getDivisionId() != null) {
										
										courseListTemp = courseAssignMasterRepository
												.findAllByDivisionIdAndDivisionIdLanguageAndComplianceDatewithin7daysForCourseFetch(
														user.getDivisionId().getId(), language, currentDt, nextDt);
									}
								}
							}
							
						}
						if(!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
							}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}//3rd if else end
			try {
				if (courseList != null && !courseList.isEmpty()) {
					result = new ArrayList<>();
					List<Course> deduped = courseList.stream().distinct().collect(Collectors.toList());
					for (Course course : deduped) {

						CoursePayload coursePayload = CourseMapper.INSTANCE.courseEntityToCoursePayload(course);

						result.add(coursePayload);
					}
				}
				
				response.setData(result);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			
			}
		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, "getCourseList Course Service", ex.getMessage()));
		}
		return response;
	}

	public List<Course> findAll() {

		List<Course> list = courseRepository.findAll();

		return list;
	}

}
