package com.cinfy.mlearning.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cinfy.mlearning.commonmodel.DeptMasterCommon;
import com.cinfy.mlearning.commonmodel.DivisionMasterCommon;
import com.cinfy.mlearning.commonmodel.OfficeMasterCommon;
import com.cinfy.mlearning.commonmodel.CompanyMasterCommon;
import com.cinfy.mlearning.commonmodel.CourseAssignMasterCommon;
import com.cinfy.mlearning.commonmodel.CourseCategoryCommon;
import com.cinfy.mlearning.commonmodel.CourseCommon;
import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.DivisionMaster;
import com.cinfy.mlearning.model.OfficeMaster;
import com.cinfy.mlearning.model.CompanyMaster;
import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.CourseAssignMaster;
import com.cinfy.mlearning.model.CourseCategory;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.CompanyMasterRepository;
import com.cinfy.mlearning.model.repositories.CourseAssignMasterRepository;
import com.cinfy.mlearning.model.repositories.CourseCategoryRepository;
import com.cinfy.mlearning.model.repositories.CourseModuleRepository;
import com.cinfy.mlearning.model.repositories.CourseRepository;
import com.cinfy.mlearning.model.repositories.DeptMasterRepository;
import com.cinfy.mlearning.model.repositories.DivisionMasterRepository;
import com.cinfy.mlearning.model.repositories.OfficeMasterRepository;

@Controller
public class CourseAssignMasterController {

	@Autowired
	CourseRepository courseRepository;

	
	@Autowired
	CompanyMasterRepository companyMasterRepository;

	@Autowired
	DeptMasterRepository deptMasterRepository;

	@Autowired
	OfficeMasterRepository officeMasterRepository;

	@Autowired
	DivisionMasterRepository divisionMasterRepository;

	@Autowired
	CourseAssignMasterRepository courseAssignMasterRepository;
	
	@Autowired
	CourseModuleRepository courseModuleRepository;
	
	@Autowired
	CourseCategoryRepository courseCategoryRepository;

	private static final Logger logger = LoggerFactory.getLogger(CourseAssignMasterController.class);

	@RequestMapping(value = "/courseAssignMaster", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = request.getParameter("success");
		String failure = request.getParameter("failure");

		List<CourseAssignMaster> courseAssignMasterList = new ArrayList<CourseAssignMaster>();
		List<CourseAssignMasterCommon> courseAssignMasterCommonList = new ArrayList<CourseAssignMasterCommon>();

		CourseAssignMasterCommon courseAssignMasterCommon = new CourseAssignMasterCommon();

		String commonId = request.getParameter("commonId");

		try {
			if (commonId != null) {
				courseAssignMasterList = courseAssignMasterRepository.findByCommonId(Long.parseLong(commonId));
				if (courseAssignMasterList.size() > 0) {
					for (CourseAssignMaster c : courseAssignMasterList) {
						if (c.getLanguage() == 1) {
							courseAssignMasterCommon.setModelEnglish(c);
						} else {
							courseAssignMasterCommon.setModelHindi(c);
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			courseAssignMasterCommon = new CourseAssignMasterCommon();
		}

		ModelAndView view = new ModelAndView("course_assign");
		view.addObject("courseAssignMaster", courseAssignMasterCommon);
		if (success != null && !success.isEmpty()) {
			view.addObject("success", success);
		} else {
			view.addObject("failure", failure);
		}

		try {
			
			courseAssignMasterList = courseAssignMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < courseAssignMasterList.size(); i = i + 2) {

				CourseAssignMasterCommon camc = new CourseAssignMasterCommon();

				if (courseAssignMasterList.get(i).getLanguage() == 1) {
					camc.setModelEnglish(courseAssignMasterList.get(i));
					camc.setModelHindi(courseAssignMasterList.get(i + 1));

				} else {
					camc.setModelHindi(courseAssignMasterList.get(i));
					camc.setModelEnglish(courseAssignMasterList.get(i + 1));
				}

				courseAssignMasterCommonList.add(camc);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("courseAssignMasterList", courseAssignMasterCommonList);
		
		List<CourseCategory> courseCategoryLi = new ArrayList<CourseCategory>();
		List<CourseCategoryCommon> courseCategoryCommonLi = new ArrayList<CourseCategoryCommon>();
		try {
			courseCategoryLi = courseCategoryRepository.findAllOrderByCommonId();
			for (int i = 0; i < courseCategoryLi.size(); i = i + 2) {

				CourseCategoryCommon ccm = new CourseCategoryCommon();

				if (courseCategoryLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(courseCategoryLi.get(i));
					ccm.setModelHindi(courseCategoryLi.get(i + 1));

				} else {
					ccm.setModelHindi(courseCategoryLi.get(i));
					ccm.setModelEnglish(courseCategoryLi.get(i + 1));
				}

				courseCategoryCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("courseCategoryList", courseCategoryCommonLi);
		
		List<Course> courseLi = new ArrayList<Course>();
		List<CourseCommon> courseCommonLi = new ArrayList<CourseCommon>();
		try {
			courseLi = courseRepository.findAllOrderByCommonId();
			for (int i = 0; i < courseLi.size(); i = i + 2) {

				CourseCommon ccm = new CourseCommon();

				if (courseLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(courseLi.get(i));
					ccm.setModelHindi(courseLi.get(i + 1));

				} else {
					ccm.setModelHindi(courseLi.get(i));
					ccm.setModelEnglish(courseLi.get(i + 1));
				}

				courseCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("courseMasterList", courseCommonLi);
		
//		List<CourseModule> courseModule = new ArrayList<CourseModule>();
//		List<CourseModuleCommon> courseModuleCommonLi = new ArrayList<CourseModuleCommon>();
//		try {
//			courseModule = courseModuleRepository.findAllOrderByCommonId();
//			for (int i = 0; i < courseModule.size(); i = i + 2) {
//
//				CourseModuleCommon ccm = new CourseModuleCommon();
//
//				if (courseModule.get(i).getLanguage() == 1) {
//					ccm.setModelEnglish(courseModule.get(i));
//					ccm.setModelHindi(courseModule.get(i + 1));
//
//				} else {
//					ccm.setModelHindi(courseModule.get(i));
//					ccm.setModelEnglish(courseModule.get(i + 1));
//				}
//
//				courseModuleCommonLi.add(ccm);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		view.addObject("courseModuleList", courseModuleCommonLi);
		

		List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();
		List<CompanyMasterCommon> companyMasterCommonList = new ArrayList<CompanyMasterCommon>();

		try {
			companyMasterList = companyMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < companyMasterList.size(); i = i + 2) {

				CompanyMasterCommon ccm = new CompanyMasterCommon();

				if (companyMasterList.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(companyMasterList.get(i));
					ccm.setModelHindi(companyMasterList.get(i + 1));

				} else {
					ccm.setModelHindi(companyMasterList.get(i));
					ccm.setModelEnglish(companyMasterList.get(i + 1));
				}

				companyMasterCommonList.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("companyMasterList", companyMasterCommonList);

		List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();
		List<OfficeMasterCommon> officeMasterCommonList = new ArrayList<OfficeMasterCommon>();
		try {
			officeMasterList = officeMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < officeMasterList.size(); i = i + 2) {

				OfficeMasterCommon omc = new OfficeMasterCommon();

				if (officeMasterList.get(i).getLanguage() == 1) {
					omc.setModelEnglish(officeMasterList.get(i));
					omc.setModelHindi(officeMasterList.get(i + 1));

				} else {
					omc.setModelHindi(officeMasterList.get(i));
					omc.setModelEnglish(officeMasterList.get(i + 1));
				}

				officeMasterCommonList.add(omc);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("officeMasterList", officeMasterCommonList);

		List<DeptMaster> deptMasterList = new ArrayList<DeptMaster>();
		List<DeptMasterCommon> deptMasterCommonList = new ArrayList<DeptMasterCommon>();

		try {
			deptMasterList = deptMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < deptMasterList.size(); i = i + 2) {

				DeptMasterCommon dmc = new DeptMasterCommon();

				if (deptMasterList.get(i).getLanguage() == 1) {
					dmc.setModelEnglish(deptMasterList.get(i));
					dmc.setModelHindi(deptMasterList.get(i + 1));

				} else {
					dmc.setModelHindi(deptMasterList.get(i));
					dmc.setModelEnglish(deptMasterList.get(i + 1));
				}

				deptMasterCommonList.add(dmc);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("deptMasterList", deptMasterCommonList);

		List<DivisionMaster> divisionMasterList = new ArrayList<DivisionMaster>();
		List<DivisionMasterCommon> divisionMasterCommonList = new ArrayList<DivisionMasterCommon>();
		try {
			divisionMasterList = divisionMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < divisionMasterList.size(); i = i + 2) {

				DivisionMasterCommon dmc = new DivisionMasterCommon();

				if (divisionMasterList.get(i).getLanguage() == 1) {
					dmc.setModelEnglish(divisionMasterList.get(i));
					dmc.setModelHindi(divisionMasterList.get(i + 1));

				} else {
					dmc.setModelHindi(divisionMasterList.get(i));
					dmc.setModelEnglish(divisionMasterList.get(i + 1));
				}

				divisionMasterCommonList.add(dmc);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("divisionMasterList", divisionMasterCommonList);
		
		return view;
	}

	@RequestMapping(value = "/saveOrUpdateCourseAssignMaster", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateCourseAssignMaster(HttpServletRequest request,
			@ModelAttribute("courseAssignMaster") CourseAssignMasterCommon courseAssignMasterCommon) {
		logger.info("## saveOrUpdate Course Assign Master Content...");
		
		CourseAssignMaster courseAssignMaster = new CourseAssignMaster();			

		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String success = "";
		String failure = "";

		Long newCommonId = System.currentTimeMillis();
		
		Boolean isExistHindi = false;
		Boolean isExistEnglish = false;

		try {
			CourseAssignMaster modelHindi = courseAssignMasterCommon.getModelHindi();
			CourseAssignMaster modelEnglish = courseAssignMasterCommon.getModelEnglish();
			
			List<CompanyMaster> companyMasterList = companyMasterRepository.findByCommonId(modelEnglish.getCompanyId().getCommonId());
			for (CompanyMaster companyMaster : companyMasterList) {
				if (companyMaster.getLanguage() == 1) {
					modelEnglish.setCompanyId(companyMaster);
				}else {
					modelHindi.setCompanyId(companyMaster);
				}
			}
			
			if(modelEnglish.getOfficeId()!=null) {
			if(modelEnglish.getOfficeId().getCommonId() != 0) {
				List<OfficeMaster> officeMasterList = officeMasterRepository.findByCommonId(modelEnglish.getOfficeId().getCommonId());
				for (OfficeMaster officeMaster : officeMasterList) {
					if (officeMaster.getLanguage() == 1) {
						modelEnglish.setOfficeId(officeMaster);
					}else {
						modelHindi.setOfficeId(officeMaster);
					}
				}
			}else {
				modelEnglish.setOfficeId(null);
				modelHindi.setOfficeId(null);
			}
			}
			
			System.out.println("modelEnglish.getDivisionId()---"+modelEnglish.getDeptId());
			if(modelEnglish.getDeptId()!=null) {
			if(modelEnglish.getDeptId().getCommonId() != 0) {				
				List<DeptMaster> de = deptMasterRepository.findByCommonId(modelEnglish.getDeptId().getCommonId());
				for (DeptMaster deptMaster : de) {
					if (deptMaster.getLanguage() == 1) {
						modelEnglish.setDeptId(deptMaster);
					}else {
						modelHindi.setDeptId(deptMaster);
					}
				}
			}else {
				modelEnglish.setDeptId(null);
				modelHindi.setDeptId(null);
			}	
			}
			
			
		
			if(modelEnglish.getDivisionId()!=null) {
			if(modelEnglish.getDivisionId().getCommonId() != 0) {
				List<DivisionMaster> divisionMasterList = divisionMasterRepository.findByCommonId(modelEnglish.getDivisionId().getCommonId());
				for (DivisionMaster divisionMaster : divisionMasterList) {
					if (divisionMaster.getLanguage() == 1) {
						modelEnglish.setDivisionId(divisionMaster);
					}else {
						modelHindi.setDivisionId(divisionMaster);
					}
				}
			}else {
				modelEnglish.setDivisionId(null);
				modelHindi.setDivisionId(null);
			}
			}
			List<Course> courseList = new ArrayList<Course>();
			if (modelEnglish.getCourseId().getCommonId() != null) {
				courseList = courseRepository.findByCommonId(modelEnglish.getCourseId().getCommonId());
				if (courseList.size() > 0) {
					for (Course c : courseList) {
						if (c.getLanguage() == 1) {
							modelEnglish.setCourseId(c);
						} else {
							modelHindi.setCourseId(c);
						}
					}

				}
			}
			
			//isExistHindi = courseAssignMasterRepository.findCompanyIdOfficeIdDivisionIdDeptIdCourseIdExist(modelHindi.getCompanyId().getId(), modelHindi.getOfficeId().getId(),modelHindi.getDeptId().getId(),modelHindi.getDivisionId().getId(),modelHindi.getCourseId().getId(), 2);
			//isExistEnglish = courseAssignMasterRepository.findCompanyIdOfficeIdDivisionIdDeptIdCourseIdExist(modelEnglish.getCompanyId().getId(), modelEnglish.getOfficeId().getId(),modelEnglish.getDeptId().getId(),modelEnglish.getDivisionId().getId(),modelEnglish.getCourseId().getId(), 1);
			
			//if(isExistHindi && isExistEnglish) {
			//	failure = "This course has already been added";
			//	return new ModelAndView("redirect:/courseAssignMaster?failure=" + failure);
			//}else {
			
			modelEnglish.setUpdatedDate(new Date());
			modelEnglish.setLanguage(1);
			modelEnglish.setUpdatedBy(loginUser.getUserId());
			modelHindi.setUpdatedDate(new Date());
			modelHindi.setLanguage(2);
			modelHindi.setUpdatedBy(loginUser.getUserId());
			modelHindi.setCompletionDate(modelEnglish.getCompletionDate());
			
			if (modelEnglish.getId() != null && modelHindi.getId() != null) {
				
				courseAssignMaster = courseAssignMasterRepository.save(modelEnglish);
				courseAssignMaster = courseAssignMasterRepository.save(modelHindi);
				success = "Course Assign Master has been updated successfully";
			} else {
				
				modelEnglish.setCreatedDate(new Date());
				modelEnglish.setCommonId(newCommonId);
				modelHindi.setCreatedDate(new Date());
				modelHindi.setCommonId(newCommonId);
				modelEnglish.setUpdatedBy(loginUser.getUserId());
				modelHindi.setUpdatedBy(loginUser.getUserId());
				modelEnglish.setCreatedBy(loginUser.getUserId());
				modelHindi.setCreatedBy(loginUser.getUserId());
				
				modelHindi.setCompletionDate(modelEnglish.getCompletionDate());

				courseAssignMaster = courseAssignMasterRepository.save(modelEnglish);
				courseAssignMaster = courseAssignMasterRepository.save(modelHindi);
				success = "Course Assign Master has been created successfully";
			}
			//}

		} catch (Exception ex) {
			ex.printStackTrace();
			success = "Please try again !";
		}

		return new ModelAndView("redirect:/courseAssignMaster?success=" + success);
	}

	@RequestMapping(value = "/deleteCourseAssignMaster", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request,
			@RequestParam("commonId") Long commonId) throws Exception {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = "";

		if (commonId != null) {
			courseAssignMasterRepository.courseAssignMasterDeleteCommon(commonId);
			success = "Course Assign Master has been deleted successfully";
		}

		return new ModelAndView("redirect:/courseAssignMaster?success=" + success);
	}

}
