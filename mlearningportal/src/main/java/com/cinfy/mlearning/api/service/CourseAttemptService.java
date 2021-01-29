package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.CourseAttempt;
import com.cinfy.mlearning.model.CourseModule;
import com.cinfy.mlearning.model.UserDashboardLogs;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.common.CourseAttemptPayload;
import com.cinfy.mlearning.model.common.UserDashboardLogsPayload;
import com.cinfy.mlearning.model.mapper.CourseAttemptMapper;
import com.cinfy.mlearning.model.mapper.UserDashboardLogsMapper;
import com.cinfy.mlearning.model.repositories.CourseAssignMasterRepository;
import com.cinfy.mlearning.model.repositories.CourseAttemptRepository;
import com.cinfy.mlearning.model.repositories.CourseModuleRepository;
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
public class CourseAttemptService {
	public static final Logger logger = LoggerFactory.getLogger(CourseAttemptService.class);

	private static final int ERROR_CODE_CLASS = 1600;

	@Autowired
	private CourseAttemptRepository courseAttemptRepository;
	
	@Autowired
	private CourseAssignMasterRepository courseAssignMasterRepository;
	
	@Autowired
	private CourseModuleRepository courseModuleRepository;
	
	@Autowired
	UserNewRegisterRepository userNewRegisterRepository;
	
	public GenericResponse getListReadOrNot(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details .... ");
	
		GenericResponse response = new GenericResponse();
		UserDashboardLogs userDashboardLogs =new UserDashboardLogs();
		response.setStatus(1);
		try {
			
		
			 
			Integer userId =  params.get("userId")!=null?Integer.parseInt(params.get("userId").toString()) :0;
			
			Integer courseModuleId =  params.get("courseModuleId")!=null?Integer.parseInt(params.get("courseModuleId").toString()) :0;
			CourseAttempt courseAttempt=new CourseAttempt();
			if(userId>0 && courseModuleId>0) {
			
			 courseAttempt= courseAttemptRepository.findByUserIdCourseModuleId(userId,courseModuleId);
				
			}
			
			//mav.addObject("userDashboardLogs", userDashboardLogs);
			
		
			CourseAttemptPayload payload = CourseAttemptMapper.INSTANCE.courseAttemptEntityToCourseAttemptPayload(courseAttempt);
			

			response.setData(payload);
			
		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("sales.save.update"), ex.getMessage()));
		}
		return response;
	}
	

	public GenericResponse getList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details .... ");
		UserDashboardLogsPayload result = null;
		GenericResponse response = new GenericResponse();
		UserDashboardLogs userDashboardLogs =new UserDashboardLogs();
		response.setStatus(1);
		try {
			
			Integer language= params.get("language")!=null? Integer.parseInt(params.get("language").toString()) :-1;
			 
			Integer userId =  params.get("userId")!=null?Integer.parseInt(params.get("userId").toString()) :-1;
			if(userId!= -1 && language!= -1) {
			UserNew loggedInUser= userNewRegisterRepository.getOne(userId);

		//	Integer courseModuleId = (Integer) params.get("courseModuleId");

			

			List<Course> courseList = new ArrayList<Course>();

			// not compulsory
			Integer isCompliance = 0;

			if (loggedInUser.getDivisionId() != null) {
				courseList = courseAssignMasterRepository
						.findAllByDivisionIdAndDivisionIdLanguageAndIsComplianceForCourseFetch(
								loggedInUser.getDivisionId().getId(), language, false);
			}
			if (loggedInUser.getDeptId() != null) {
				courseList = courseAssignMasterRepository
						.findAllByDeptIdAndDeptIdLanguageAndIsComplianceForCourseFetch(
								loggedInUser.getDeptId().getId(), language, false);
			}

			if (loggedInUser.getOfficeId() != null) {
				courseList = courseAssignMasterRepository
						.findAllByOfficeIdAndOfficeIdLanguageAndIsComplianceForCourseFetch(
								loggedInUser.getOfficeId().getId(), language, false);
			}

			if (loggedInUser.getCompanyId() != null) {
				courseList = courseAssignMasterRepository
						.findAllByCompanyIdAndCompanyIdLanguageAndIsComplianceForCourseFetch(
								loggedInUser.getCompanyId().getId(), language, false);
			}
			List<Course> deduped0 = courseList.stream().distinct().collect(Collectors.toList());
//			mav.addObject("NonCompliance", deduped0);
			
			
		
		
			List<CourseModule> totalIncompleteModulesNonCompliance=new ArrayList<CourseModule>();
			List<CourseModule> totalCompleteModulesNonCompliance = new ArrayList<CourseModule>();
			Integer totalIncompleteModulesNonComplianceCount=0;
			Integer totalCompleteModulesNonComplianceCount =0;
			List<CourseModule> totalPassNonCompliance=new ArrayList<CourseModule>();
			List<CourseModule> totalFailNonCompliance = new ArrayList<CourseModule>();
			Integer totalFailNonComplianceCount=0;
			Integer totalPassNonComplianceCount =0;
			List<CourseModule> courseModulePendingNonCompliance=new ArrayList<CourseModule>();
			for (Course course : deduped0) {
			
				List<CourseModule> courseModules = this.courseModuleRepository.findSubjectBycourseId(course.getId());
				
				for (CourseModule courseModule : courseModules) {
					
					CourseAttempt courseAttempt= courseAttemptRepository.findByUserIdCourseModuleId(loggedInUser.getUserId(),courseModule.getId());
					if(courseAttempt!=null) {
					if(courseAttempt.getIsCourseCompleted()) {
						totalCompleteModulesNonCompliance.add(courseAttempt.getCourseModuleId());	
						totalCompleteModulesNonComplianceCount++;
					}else {
						totalIncompleteModulesNonCompliance.add(courseAttempt.getCourseModuleId());
						totalIncompleteModulesNonComplianceCount++;
					}
				
					if(courseAttempt.getIsAssessmentPass()!=null) {
					if(courseAttempt.getIsAssessmentPass()) {
						totalPassNonCompliance.add(courseAttempt.getCourseModuleId());	
						totalPassNonComplianceCount++;
					}else {
						totalFailNonCompliance.add(courseAttempt.getCourseModuleId());
						totalFailNonComplianceCount++;
					}
					}						
					
					}else {
						courseModulePendingNonCompliance.add(courseModule);
					}	
					
					
				}
				
			}
			userDashboardLogs.setCourseModulePendingNonCompliance(courseModulePendingNonCompliance);
			userDashboardLogs.setTotalCourseModulePendingNonCompliance(courseModulePendingNonCompliance.size());
			
			userDashboardLogs.setTotalCompleteModulesNonCompliance(totalCompleteModulesNonCompliance);
			userDashboardLogs.setTotalCompleteModulesNonComplianceCount(totalCompleteModulesNonCompliance.size());
			userDashboardLogs.setTotalIncompleteModulesNonCompliance(totalIncompleteModulesNonCompliance);
			userDashboardLogs.setTotalIncompleteModulesNonComplianceCount(totalIncompleteModulesNonCompliance.size());
			
			userDashboardLogs.setTotalPassNonCompliance(totalPassNonCompliance);
			userDashboardLogs.setTotalPassNonComplianceCount(totalPassNonCompliance.size());
			userDashboardLogs.setTotalFailNonCompliance(totalFailNonCompliance);
			userDashboardLogs.setTotalFailNonComplianceCount(totalFailNonCompliance.size());
	

			// for compliance
			isCompliance = 1;

			if (loggedInUser.getDivisionId() != null) {
				
				
				courseList = courseAssignMasterRepository
						.findAllByDivisionIdAndDivisionIdLanguageAndIsComplianceForCourseFetch(
								loggedInUser.getDivisionId().getId(), language, true);
				
			}
			if (loggedInUser.getDeptId() != null) {
				
			
				courseList = courseAssignMasterRepository
						.findAllByDeptIdAndDeptIdLanguageAndIsComplianceForCourseFetch(
								loggedInUser.getDeptId().getId(), language, true);
				
			}

			if (loggedInUser.getOfficeId() != null) {
				
				
				courseList = courseAssignMasterRepository
						.findAllByOfficeIdAndOfficeIdLanguageAndIsComplianceForCourseFetch(
								loggedInUser.getOfficeId().getId(), language, true);
				
			}

			if (loggedInUser.getCompanyId() != null) {
				
				
				courseList = courseAssignMasterRepository
						.findAllByCompanyIdAndCompanyIdLanguageAndIsComplianceForCourseFetch(
								loggedInUser.getCompanyId().getId(), language, true);
				
			}
			List<Course> deduped1 = courseList.stream().distinct().collect(Collectors.toList());
			//mav.addObject("Compliance", deduped1);
			
			
			List<CourseModule> totalIncompleteModulesCompliance=new ArrayList<CourseModule>();
			List<CourseModule> totalCompleteModulesCompliance = new ArrayList<CourseModule>();
			Integer totalIncompleteModulesComplianceCount=0;
			Integer totalCompleteModulesComplianceCount =0;
			List<CourseModule> totalPassCompliance=new ArrayList<CourseModule>();
			List<CourseModule> totalFailCompliance = new ArrayList<CourseModule>();
			Integer totalFailComplianceCount=0;
			Integer totalPassComplianceCount =0;
			List<CourseModule> courseModulePendingCompliance=new ArrayList<CourseModule>();
				
			
			for (Course course : deduped1) {
				
				List<CourseModule> courseModules = this.courseModuleRepository.findSubjectBycourseId(course.getId());
				for (CourseModule courseModule : courseModules) {
					CourseAttempt courseAttempt= courseAttemptRepository.findByUserIdCourseModuleId(loggedInUser.getUserId(),courseModule.getId());
					if(courseAttempt!=null) {
					if(courseAttempt.getIsCourseCompleted()) {
						totalCompleteModulesCompliance.add(courseAttempt.getCourseModuleId());	
						totalCompleteModulesComplianceCount++;
					}else {
						totalIncompleteModulesCompliance.add(courseAttempt.getCourseModuleId());
						totalIncompleteModulesComplianceCount++;
					}
				
					if(courseAttempt.getIsAssessmentPass()!=null) {
					if(courseAttempt.getIsAssessmentPass()) {
						totalPassCompliance.add(courseAttempt.getCourseModuleId());	
						totalPassComplianceCount++;
					}else {
						totalFailCompliance.add(courseAttempt.getCourseModuleId());
						totalFailComplianceCount++;
					}
					}						
					
					}else {
						courseModulePendingCompliance.add(courseModule);
					}						
					
				}
				
			}
		
			userDashboardLogs.setCourseModulePendingCompliance(courseModulePendingCompliance);
			userDashboardLogs.setTotalCourseModulePendingCompliance(courseModulePendingCompliance.size());
			
			userDashboardLogs.setTotalCompleteModulesCompliance(totalCompleteModulesCompliance);
			userDashboardLogs.setTotalCompleteModulesComplianceCount(totalCompleteModulesCompliance.size());
			userDashboardLogs.setTotalIncompleteModulesCompliance(totalIncompleteModulesCompliance);
			userDashboardLogs.setTotalIncompleteModulesComplianceCount(totalIncompleteModulesCompliance.size());
			
			userDashboardLogs.setTotalPassCompliance(totalPassCompliance);
			userDashboardLogs.setTotalPassComplianceCount(totalPassCompliance.size());
			userDashboardLogs.setTotalFailCompliance(totalFailCompliance);
			userDashboardLogs.setTotalFailComplianceCount(totalFailCompliance.size());
			
			
			
			
			// for pending (complete date in 7 days)
			isCompliance = 2;
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.add(Calendar.DAY_OF_MONTH, 7);
			Date expiryDate = cal.getTime();
			java.sql.Date nextDt = new java.sql.Date(expiryDate.getTime());
			java.sql.Date currentDt = new java.sql.Date(new Date().getTime());
			

			if (loggedInUser.getDivisionId() != null) {
				courseList = courseAssignMasterRepository
						.findAllByDivisionIdAndDivisionIdLanguageAndComplianceDatewithin7daysForCourseFetch(
								loggedInUser.getDivisionId().getId(), language, currentDt, nextDt);
			}
			if (loggedInUser.getDeptId() != null) {
				courseList = courseAssignMasterRepository
						.findAllByDeptIdAndDeptIdLanguageAndComplianceDatewithin7daysForCourseFetch(
								loggedInUser.getDeptId().getId(), language, currentDt, nextDt);
			}

			if (loggedInUser.getOfficeId() != null) {
				courseList = courseAssignMasterRepository
						.findAllByOfficeIdAndOfficeIdLanguageAndComplianceDatewithin7daysForCourseFetch(
								loggedInUser.getOfficeId().getId(), language, currentDt, nextDt);
			}

			if (loggedInUser.getCompanyId() != null) {
				courseList = courseAssignMasterRepository
						.findAllByCompanyIdAndCompanyIdLanguageAndComplianceDatewithin7daysForCourseFetch(
								loggedInUser.getCompanyId().getId(), language, currentDt, nextDt);
			}

			List<Course> deduped2 = courseList.stream().distinct().collect(Collectors.toList());
		
			//mav.addObject("Others", deduped2);
			
			List<CourseModule> totalIncompleteModulesOthers=new ArrayList<CourseModule>();
			List<CourseModule> totalCompleteModulesOthers = new ArrayList<CourseModule>();
			Integer totalIncompleteModulesOthersCount=0;
			Integer totalCompleteModulesOthersCount =0;
			List<CourseModule> totalPassOthersCompliance=new ArrayList<CourseModule>();
			List<CourseModule> totalFailOthersCompliance = new ArrayList<CourseModule>();
			Integer totalFailOthersComplianceCount=0;
			Integer totalPassOthersComplianceCount =0;
			for (Course course : deduped1) {
				
				List<CourseModule> courseModules = this.courseModuleRepository.findSubjectBycourseId(course.getId());
				for (CourseModule courseModule : courseModules) {
				
					
					CourseAttempt courseAttempt= courseAttemptRepository.findByUserIdCourseModuleId(loggedInUser.getUserId(),courseModule.getId());
					if(courseAttempt!=null) {
					if(courseAttempt.getIsCourseCompleted()) {
						totalCompleteModulesOthers.add(courseAttempt.getCourseModuleId());	
						totalCompleteModulesOthersCount++;
					}else {
						totalIncompleteModulesOthers.add(courseAttempt.getCourseModuleId());
						totalIncompleteModulesOthersCount++;
					}
				
					if(courseAttempt.getIsAssessmentPass()!=null) {
					if(courseAttempt.getIsAssessmentPass()) {
						totalPassOthersCompliance.add(courseAttempt.getCourseModuleId());	
						totalPassOthersComplianceCount++;
					}else {
						totalFailOthersCompliance.add(courseAttempt.getCourseModuleId());
						totalFailOthersComplianceCount++;
					}
					}				
					
					}							
				}
				
			}
		
			
			
			userDashboardLogs.setTotalCompleteModulesOthers(totalCompleteModulesOthers);
			userDashboardLogs.setTotalCompleteModulesOthersCount(totalCompleteModulesOthers.size());
			userDashboardLogs.setTotalIncompleteModulesOthers(totalIncompleteModulesOthers);
			userDashboardLogs.setTotalFailOthersComplianceCount(totalIncompleteModulesOthers.size());
			
			userDashboardLogs.setTotalPassOthersCompliance(totalPassOthersCompliance);
			userDashboardLogs.setTotalPassOthersComplianceCount(totalPassOthersCompliance.size());
			userDashboardLogs.setTotalFailOthersCompliance(totalFailOthersCompliance);
			userDashboardLogs.setTotalFailOthersComplianceCount(totalFailOthersCompliance.size());
			
			
			//mav.addObject("userDashboardLogs", userDashboardLogs);
			
		
			UserDashboardLogsPayload payload = UserDashboardLogsMapper.INSTANCE.userDashboardLogsEntityToUserDashboardLogsPayload(userDashboardLogs);
			result = payload;

			response.setData(result);
			}
		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("sales.save.update"), ex.getMessage()));
		}
		return response;
	}

	public GenericResponse saveAndUpdate(CourseAttemptPayload courseAttemptPayload) {
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			CourseAttempt courseAttempt = CourseAttemptMapper.INSTANCE
					.courseAttemptPayloadToCourseAttemptEntity(courseAttemptPayload);

			CourseAttempt isExist = courseAttemptRepository.findByUserIdCourseModuleId(
					courseAttempt.getUserId().getUserId(), courseAttempt.getCourseModuleId().getId());

			if (isExist == null) {
				courseAttempt = this.courseAttemptRepository.save(courseAttempt);
			} else {
				courseAttempt = isExist;
			}

			courseAttemptPayload = CourseAttemptMapper.INSTANCE
					.courseAttemptEntityToCourseAttemptPayload(courseAttempt);

			response.setData(courseAttemptPayload);

		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(
					new ErrorMessage(ERROR_CODE_CLASS, "Unable to process the create request.", ex.getMessage()));
		}

		return response;
	}

}
