package com.cinfy.mlearning.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cinfy.mlearning.commonmodel.DivisionMasterCommon;
import com.cinfy.mlearning.commonmodel.OfficeMasterCommon;
import com.cinfy.mlearning.commonmodel.CourseCommon;
import com.cinfy.mlearning.commonmodel.CourseModuleCommon;
import com.cinfy.mlearning.commonmodel.DeptMasterCommon;
import com.cinfy.mlearning.model.CompanyMaster;
import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.CourseCategory;
import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.DivisionMaster;
import com.cinfy.mlearning.model.OfficeMaster;
import com.cinfy.mlearning.model.CourseModule;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.CompanyMasterRepository;
import com.cinfy.mlearning.model.repositories.CourseCategoryRepository;
import com.cinfy.mlearning.model.repositories.DeptMasterRepository;
import com.cinfy.mlearning.model.repositories.DesignationMasterRepository;
import com.cinfy.mlearning.model.repositories.DivisionMasterRepository;
import com.cinfy.mlearning.model.repositories.OfficeMasterRepository;
import com.cinfy.mlearning.model.repositories.UserNewRegisterRepository;
import com.cinfy.mlearning.model.repositories.CourseModuleRepository;
import com.cinfy.mlearning.model.repositories.CourseRepository;

@Controller
public class AjaxController {

	@Autowired
	CourseModuleRepository courseModuleRepository;

	@Autowired
	UserNewRegisterRepository userNewRegisterRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	DesignationMasterRepository designationMasterRepository;

	@Autowired
	DeptMasterRepository deptMasterRepository;

	@Autowired
	CourseCategoryRepository courseCategoryRepository;

	@Autowired
	CompanyMasterRepository companyMasterRepository;

	@Autowired
	OfficeMasterRepository officeMasterRepository;

	@Autowired
	DivisionMasterRepository divisionMasterRepository;

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView error404() {
		ModelAndView view = new ModelAndView("errorpages/errorPage404");
		System.out.println("custom error handler");

		return view;
	}

	// Get All courses Modules By course Id Ajax request
	@RequestMapping(value = "/get_coursetype_module_list")
	@ResponseBody
	public List<CourseModule> getAll_CourseTypeModule_By_CourseId(@RequestParam("courseId") Integer courseId,
			HttpServletRequest request) {

		List<CourseModule> courseModules = null;
		try {

			if (courseId != null) {
				courseModules = this.courseModuleRepository.findSubjectBycourseId(courseId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseModules;
	}

	// Get All Course Type By Category Id Ajax request
	// @RequestMapping(value = "/get_coursetype_list")
	// @ResponseBody
	// public List<Course>
	// getAll_CourseType_By_CategoryId(@RequestParam("courseCategoryType") Integer
	// courseCategoryType,
	// HttpServletRequest request) {
	//
	// UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
	// Integer language = (Integer) request.getSession().getAttribute("language");
	// List<Course> courses = null;
	// try {
	//
	// if (loginUser != null && language != null && courseCategoryType != null) {
	//
	// DeptMaster deptMaster =
	// userNewRegisterRepository.findByUserIdAndDeleted(loginUser.getUserId(), 0)
	// .getDeptId();
	//
	// if (courseCategoryType == 0) {// for non compliance
	// courses = deptCourseMasterRepository
	// .findAllByDeptIdCommonIdAndDeptIdLanguageAndIsComplianceForCourseFetch(
	// deptMaster.getCommonId(), language, false);
	// } else if (courseCategoryType == 1) {// for compliance
	// courses = deptCourseMasterRepository
	// .findAllByDeptIdCommonIdAndDeptIdLanguageAndIsComplianceForCourseFetch(
	// deptMaster.getCommonId(), language, true);
	// } else if (courseCategoryType == 2) {// for pending (complete in 7 days)
	//
	// Calendar cal = Calendar.getInstance();
	//
	// cal.add(Calendar.DAY_OF_MONTH, 7);
	// Date expiryDate = cal.getTime();
	// java.sql.Date nextDt = new java.sql.Date(expiryDate.getTime());
	// java.sql.Date currentDt = new java.sql.Date(new Date().getTime());
	//
	// courses = deptCourseMasterRepository
	// .findAllByDeptIdCommonIdAndDeptIdLanguageAndComplianceDatewithin7daysForCourseFetch(
	// deptMaster.getCommonId(), language, currentDt, nextDt);
	//
	// }
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return courses;
	// }

	@RequestMapping(value = "/get_course_module_list")
	@ResponseBody
	public List<CourseModuleCommon> get_course_module_list(@RequestParam("courseCommonId") Long courseCommonId,
			HttpServletRequest request) {

		List<CourseModule> courseModuleLi = new ArrayList<CourseModule>();
		List<CourseModuleCommon> courseModuleCommonLi = new ArrayList<CourseModuleCommon>();
		try {
			courseModuleLi = courseModuleRepository.findByCourseCommonId(courseCommonId);
			for (int i = 0; i < courseModuleLi.size(); i = i + 2) {

				CourseModuleCommon ccm = new CourseModuleCommon();

				if (courseModuleLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(courseModuleLi.get(i));
					ccm.setModelHindi(courseModuleLi.get(i + 1));

				} else {
					ccm.setModelHindi(courseModuleLi.get(i));
					ccm.setModelEnglish(courseModuleLi.get(i + 1));
				}

				courseModuleCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseModuleCommonLi;
	}

	@RequestMapping(value = "/get_course_module_model__list")
	@ResponseBody
	public List<CourseModule> get_course_module_model__list(@RequestParam("courseCommonId") Long courseCommonId,
			HttpServletRequest request) {

		List<CourseModule> courseModuleLi = new ArrayList<CourseModule>();
		// List<CourseModuleCommon> courseModuleCommonLi = new
		// ArrayList<CourseModuleCommon>();
		try {
			courseModuleLi = courseModuleRepository.findByCourseCommonId(courseCommonId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseModuleLi;
	}

	@RequestMapping(value = "/get_course_list")
	@ResponseBody
	public List<CourseCommon> get_course_list(@RequestParam("courseCategoryCommonId") Long courseCategoryCommonId,
			HttpServletRequest request) {
		List<Course> courseLi = new ArrayList<Course>();
		List<CourseCommon> courseCommonLi = new ArrayList<CourseCommon>();
		try {
			courseLi = courseRepository.findAllOrderByCourseCategoryIdCommonId(courseCategoryCommonId);
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
		return courseCommonLi;
	}

	// Get All Sub Department By Common id Department Ajax request
	// @RequestMapping(value = "/get_subdept_list")
	// @ResponseBody
	// public List<SubDeptMasterCommon>
	// get_subdept_list(@RequestParam("deptCommonId") Long deptCommonId,
	// HttpServletRequest request) {
	// System.out.println("deptCommonId:-----" + deptCommonId);
	// List<SubDeptMaster> subDeptMasterLi = new ArrayList<SubDeptMaster>();
	// List<SubDeptMasterCommon> subDeptMasterCommonLi = new
	// ArrayList<SubDeptMasterCommon>();
	// try {
	//
	// subDeptMasterLi =
	// subDeptMasterRepository.findAllByDeptCommonId(deptCommonId);
	// for (int i = 0; i < subDeptMasterLi.size(); i = i + 2) {
	//
	// SubDeptMasterCommon ccm = new SubDeptMasterCommon();
	//
	// if (subDeptMasterLi.get(i).getLanguage() == 1) {
	// ccm.setModelEnglish(subDeptMasterLi.get(i));
	// ccm.setModelHindi(subDeptMasterLi.get(i + 1));
	//
	// } else {
	// ccm.setModelHindi(subDeptMasterLi.get(i));
	// ccm.setModelEnglish(subDeptMasterLi.get(i + 1));
	// }
	//
	// subDeptMasterCommonLi.add(ccm);
	//
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return subDeptMasterCommonLi;
	// }

	// Get All Designation By Common id Department Ajax request
	@RequestMapping(value = "/get_manager_list")
	@ResponseBody
	public List<UserNew> get_manager_list(@RequestParam("deptCommonId") Long deptCommonId, HttpServletRequest request) {

		List<UserNew> managerList = new ArrayList<UserNew>();

		try {
			managerList = userNewRegisterRepository.findAllByDeptCommonId(deptCommonId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return managerList;
	}

	// get Email Uniq Check
	@RequestMapping(value = "/existEmail", method = RequestMethod.GET)
	@ResponseBody
	public Boolean get_Email_UniqCheck(HttpServletRequest request, @RequestParam("email") String email) {

		Boolean b = false;
		try {
			b = userNewRegisterRepository.existsByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// get Email Uniq Check
	@RequestMapping(value = "/existContact", method = RequestMethod.GET)
	@ResponseBody
	public Boolean get_Contact_UniqCheck(HttpServletRequest request, @RequestParam("contact") String contact) {

		Boolean b = false;
		try {
			b = userNewRegisterRepository.existsByMobile(contact);
			System.out.println("sss--" + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// Get All Subject By category common id Ajax request
	@RequestMapping(value = "/get_subject_list")
	@ResponseBody
	public List<CourseModuleCommon> get_subject_list(@RequestParam("categoryCommonId") Long categoryCommonId,
			HttpServletRequest request) {

		List<CourseModule> subjectMasterLi = new ArrayList<CourseModule>();
		List<CourseModuleCommon> subjectMasterCommonLi = new ArrayList<CourseModuleCommon>();
		try {

			subjectMasterLi = courseModuleRepository.findAllByCategoryCommonId(categoryCommonId);
			for (int i = 0; i < subjectMasterLi.size(); i = i + 2) {

				CourseModuleCommon ccm = new CourseModuleCommon();

				if (subjectMasterLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(subjectMasterLi.get(i));
					ccm.setModelHindi(subjectMasterLi.get(i + 1));

				} else {
					ccm.setModelHindi(subjectMasterLi.get(i));
					ccm.setModelEnglish(subjectMasterLi.get(i + 1));
				}

				subjectMasterCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return subjectMasterCommonLi;

	}

	// check mobile Availability for user Registration
	@RequestMapping(value = "/mobileAvailablity")
	@ResponseBody
	public Map<String, String> checkMobileAvailablity(HttpServletRequest request,
			@RequestParam("mobileNo") String mobileNo) {
		UserNew b = null;

		Map<String, String> data = new HashMap<String, String>();

		try {
			b = userNewRegisterRepository.findOneByPhone(mobileNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (b != null) {
			data.put("errorContact", "<spring:message code='label.ContactExist'/>");
		} else {
			data.put("errorContact", "No");
		}
		// System.out.println("mobileNo :"+mobileNo);
		return data;
	}

	// get Sub Dept Unique Check

	// get Course Unique Check
	@RequestMapping(value = "/existCourseCategory", method = RequestMethod.GET)
	@ResponseBody
	public Boolean get_CourseCategory_UniqCheck(HttpServletRequest request,
			@RequestParam("courseTypeName") String courseTypeName) {

		Boolean b = false;
		try {
			b = courseCategoryRepository.existsByCourseTypeName(courseTypeName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// get Course Unique Check
	@RequestMapping(value = "/existCourse", method = RequestMethod.GET)
	@ResponseBody
	public Boolean get_Course_UniqCheck(HttpServletRequest request,
			@RequestParam("courseCategoryCommonId") Long courseCategoryCommonId,
			@RequestParam("course") String course) {

		Course modelEnglish = new Course();
		Course modelHindi = new Course();
		List<CourseCategory> courseCategoryMasterLi = new ArrayList<CourseCategory>();

		courseCategoryMasterLi = courseCategoryRepository.findByCommonId(courseCategoryCommonId);
		if (courseCategoryMasterLi.size() > 0) {
			for (CourseCategory c : courseCategoryMasterLi) {
				if (c.getLanguage() == 1) {
					modelEnglish.setCourseCategoryId(c);
				} else {
					modelHindi.setCourseCategoryId(c);
				}
			}

		}

		Boolean b = false;
		try {
			b = courseRepository.existsByCourseCategoryIdAndCourseName(modelEnglish.getCourseCategoryId().getId(),
					course);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// get Course module Unique Check
	@RequestMapping(value = "/existCourseModule", method = RequestMethod.GET)
	@ResponseBody
	public Boolean get_CourseModule_UniqCheck(HttpServletRequest request,
			@RequestParam("courseCommonId") Long courseCommonId, @RequestParam("module") String moduleName) {

		CourseModule modelEnglish = new CourseModule();
		CourseModule modelHindi = new CourseModule();
		List<Course> courseList = new ArrayList<Course>();

		courseList = courseRepository.findByCommonId(courseCommonId);
		if (courseList.size() > 0) {
			for (Course c : courseList) {
				if (c.getLanguage() == 1) {
					modelEnglish.setCourseId(c);
				} else {
					modelHindi.setCourseId(c);
				}
			}
		}

		Boolean b = false;
		try {
			b = courseModuleRepository.existsByCourseIdAndModuleName(modelEnglish.getCourseId().getId(), moduleName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// get Office Unique Check
	@RequestMapping(value = "/existOffice", method = RequestMethod.GET)
	@ResponseBody
	public Boolean get_Office_UniqCheck(HttpServletRequest request,
			@RequestParam("companyCommonId") Long companyCommonId, @RequestParam("office") String office) {

		OfficeMaster modelEnglish = new OfficeMaster();
		OfficeMaster modelHindi = new OfficeMaster();

		List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();
		companyMasterList = companyMasterRepository.findByCommonId(companyCommonId);

		if (companyMasterList.size() > 0) {
			for (CompanyMaster c : companyMasterList) {
				if (c.getLanguage() == 1) {
					modelEnglish.setCompanyId(c);
				} else {
					modelHindi.setCompanyId(c);
				}
			}

		}

		Boolean b = false;
		try {
			b = officeMasterRepository.existsByCompanyIdAndOfficeName(modelEnglish.getCompanyId().getId(), office);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// Get All Office By Common id Ajax request
	@RequestMapping(value = "/get_office_list")
	@ResponseBody
	public List<OfficeMasterCommon> get_office_list(@RequestParam("companyCommonId") Long companyCommonId,
			HttpServletRequest request) {
		// System.out.println("companyCommonId:-----" + companyCommonId);
		List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();
		List<OfficeMasterCommon> officeMasterCommonList = new ArrayList<OfficeMasterCommon>();
		try {

			officeMasterList = officeMasterRepository.findAllByCompanyCommonId(companyCommonId);
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
		return officeMasterCommonList;
	}

	// Get All Users By Company Common id Ajax request
	@RequestMapping(value = "/get_user_list_by_companyId")
	@ResponseBody
	public List<UserNew> get_user_list_by_companyId(@RequestParam("companyCommonId") Long companyCommonId,
			HttpServletRequest request) {

		CompanyMaster modelEnglish = new CompanyMaster();

		List<UserNew> userList = new ArrayList<UserNew>();
		try {

			List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();
			companyMasterList = companyMasterRepository.findByCommonId(companyCommonId);
			if (companyMasterList.size() > 0) {
				for (CompanyMaster c : companyMasterList) {
					if (c.getLanguage() == 1) {
						modelEnglish.setId(c.getId());
					}
				}
			}

			userList = userNewRegisterRepository.findAllUserByCompanyId(modelEnglish.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	// Get All Users By Company Common id Ajax request
	@RequestMapping(value = "/get_user_list_by_companyIdOfficeId")
	@ResponseBody
	public List<UserNew> get_user_list_by_companyIdOfficeId(@RequestParam("companyCommonId") Long companyCommonId,
			@RequestParam("officeCommonId") Long officeCommonId, HttpServletRequest request) {

		CompanyMaster modelEnglish = new CompanyMaster();

		OfficeMaster oMModelEnglish = new OfficeMaster();
        
		List<UserNew> userList = new ArrayList<UserNew>();
		try {

			List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();
			companyMasterList = companyMasterRepository.findByCommonId(companyCommonId);
			if (companyMasterList.size() > 0) {
				for (CompanyMaster c : companyMasterList) {
					if (c.getLanguage() == 1) {
						modelEnglish.setId(c.getId());
					}
				}
			}

			if (officeCommonId != 0 || officeCommonId != null) {
				List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();
				officeMasterList = officeMasterRepository.findByCommonId(officeCommonId);
				if (officeMasterList.size() > 0) {
					for (OfficeMaster c : officeMasterList) {
						if (c.getLanguage() == 1) {
							oMModelEnglish.setId(c.getId());
						}
					}
				}
			}

			System.out.println("cid-"+modelEnglish.getId()+"oid-"+oMModelEnglish.getId());
			userList = userNewRegisterRepository.findAllUserByCompanyIdOfficeId(modelEnglish.getId(),
					oMModelEnglish.getId());
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	// Get All Users By Company Common id Ajax request
	@RequestMapping(value = "/get_user_list_by_companyIdOfficeIdDeptId")
	@ResponseBody
	public List<UserNew> get_user_list_by_companyIdOfficeIdDeptId(@RequestParam("companyCommonId") Long companyCommonId,
			@RequestParam("officeCommonId") Long officeCommonId, @RequestParam("deptCommonId") Long deptCommonId,
			HttpServletRequest request) {

		CompanyMaster modelEnglish = new CompanyMaster();

		OfficeMaster oMModelEnglish = new OfficeMaster();

		DeptMaster dMModelEnglish = new DeptMaster();

		List<UserNew> userList = new ArrayList<UserNew>();
		try {

			List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();
			companyMasterList = companyMasterRepository.findByCommonId(companyCommonId);
			if (companyMasterList.size() > 0) {
				for (CompanyMaster c : companyMasterList) {
					if (c.getLanguage() == 1) {
						modelEnglish.setId(c.getId());
					}
				}
			}

			if (officeCommonId != 0 || officeCommonId != null) {
				List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();
				officeMasterList = officeMasterRepository.findByCommonId(officeCommonId);
				if (officeMasterList.size() > 0) {
					for (OfficeMaster c : officeMasterList) {
						if (c.getLanguage() == 1) {
							oMModelEnglish.setId(c.getId());
						}
					}
				}
			}

			if (deptCommonId != 0 || deptCommonId != null) {
				List<DeptMaster> deptMasterList = new ArrayList<DeptMaster>();
				deptMasterList = deptMasterRepository.findByCommonId(deptCommonId);
				if (deptMasterList.size() > 0) {
					for (DeptMaster c : deptMasterList) {
						if (c.getLanguage() == 1) {
							dMModelEnglish.setId(c.getId());
						}
					}
				}
			}

			if (deptCommonId != 0 || deptCommonId != null) {
				userList = userNewRegisterRepository.findAllUserByCompanyIdOfficeIdDeptId(modelEnglish.getId(),
						oMModelEnglish.getId(), dMModelEnglish.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	// Get All Users By Company Common id Ajax request
	@RequestMapping(value = "/get_user_list_by_companyIdOfficeIdDeptIdDivisionId")
	@ResponseBody
	public List<UserNew> get_user_list_by_companyIdOfficeIdDeptIdDivisionId(
			@RequestParam("companyCommonId") Long companyCommonId, @RequestParam("officeCommonId") Long officeCommonId,
			@RequestParam("deptCommonId") Long deptCommonId, @RequestParam("divisionCommonId") Long divisionCommonId,
			HttpServletRequest request) {

		System.out.println("officeCommonId--" + officeCommonId + "companyCommonId--" + companyCommonId
				+ "deptCommonId--" + deptCommonId + "divisionCommonId--" + divisionCommonId);
		CompanyMaster modelEnglish = new CompanyMaster();

		OfficeMaster oMModelEnglish = new OfficeMaster();

		DeptMaster dMModelEnglish = new DeptMaster();

		DivisionMaster diMModelEnglish = new DivisionMaster();

		List<UserNew> userList = new ArrayList<UserNew>();
		try {

			List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();
			companyMasterList = companyMasterRepository.findByCommonId(companyCommonId);
			if (companyMasterList.size() > 0) {
				for (CompanyMaster c : companyMasterList) {
					if (c.getLanguage() == 1) {
						modelEnglish.setId(c.getId());
					}
				}
			}

			if (officeCommonId != 0 || officeCommonId != null) {
				List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();
				officeMasterList = officeMasterRepository.findByCommonId(officeCommonId);
				if (officeMasterList.size() > 0) {
					for (OfficeMaster c : officeMasterList) {
						if (c.getLanguage() == 1) {
							oMModelEnglish.setId(c.getId());
						}
					}
				}
			}

			if (deptCommonId != 0 || deptCommonId != null) {
				List<DeptMaster> deptMasterList = new ArrayList<DeptMaster>();
				deptMasterList = deptMasterRepository.findByCommonId(deptCommonId);
				if (deptMasterList.size() > 0) {
					for (DeptMaster c : deptMasterList) {
						if (c.getLanguage() == 1) {
							dMModelEnglish.setId(c.getId());
						}
					}
				}
			}

			if (divisionCommonId != 0 || divisionCommonId != null) {
				List<DivisionMaster> divisionMasterList = new ArrayList<DivisionMaster>();
				divisionMasterList = divisionMasterRepository.findByCommonId(divisionCommonId);
				if (companyMasterList.size() > 0) {
					for (DivisionMaster c : divisionMasterList) {
						if (c.getLanguage() == 1) {
							diMModelEnglish.setId(c.getId());
						}
					}
				}
			}
			userList = userNewRegisterRepository.findAllUserByCompanyIdOfficeIdDeptIdDivisionId(modelEnglish.getId(),
					oMModelEnglish.getId(), dMModelEnglish.getId(), diMModelEnglish.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	// Get All Dept By Common id Ajax request
	@RequestMapping(value = "/get_dept_list")
	@ResponseBody
	public List<DeptMasterCommon> get_dept_list(@RequestParam("officeCommonId") Long officeCommonId,
			HttpServletRequest request) {
		// System.out.println("companyCommonId:-----" + companyCommonId);
		List<DeptMaster> deptMasterList = new ArrayList<DeptMaster>();
		List<DeptMasterCommon> deptMasterCommonList = new ArrayList<DeptMasterCommon>();
		try {

			deptMasterList = deptMasterRepository.findAllByOfficeCommonId(officeCommonId);
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
		return deptMasterCommonList;
	}

	// Get All Division By Common id Ajax request
	@RequestMapping(value = "/get_division_list")
	@ResponseBody
	public List<DivisionMasterCommon> get_division_list(@RequestParam("deptCommonId") Long deptCommonId,
			HttpServletRequest request) {

		List<DivisionMaster> divisionMasterList = new ArrayList<DivisionMaster>();
		List<DivisionMasterCommon> divisionMasterCommonList = new ArrayList<DivisionMasterCommon>();
		try {
System.out.println("deptCommonId123456::"+deptCommonId);
			divisionMasterList = divisionMasterRepository.findAllByDeptCommonId(deptCommonId);
			for (int i = 0; i < divisionMasterList.size(); i = i + 2) {

				DivisionMasterCommon dmc = new DivisionMasterCommon();

				if (divisionMasterList.get(i).getLanguage() == 1) {
					dmc.setModelEnglish(divisionMasterList.get(i));
					dmc.setModelHindi(divisionMasterList.get(i + 1));

				} else {
					dmc.setModelHindi(divisionMasterList.get(i));
					dmc.setModelEnglish(divisionMasterList.get(i + 1));
				}
				System.out.println("divCommonId name::"+dmc.getModelEnglish().getName());
				divisionMasterCommonList.add(dmc);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return divisionMasterCommonList;
	}

	// get Division Unique Check
	@RequestMapping(value = "/existDivision", method = RequestMethod.GET)
	@ResponseBody
	public Boolean get_Division_UniqCheck(HttpServletRequest request,
			@RequestParam("companyCommonId") Long companyCommonId, @RequestParam("officeCommonId") Long officeCommonId,
			@RequestParam("deptCommonId") Long deptCommonId, @RequestParam("division") String division) {

		DivisionMaster modelEnglish = new DivisionMaster();
		DivisionMaster modelHindi = new DivisionMaster();

		List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();
		companyMasterList = companyMasterRepository.findByCommonId(companyCommonId);
		if (companyMasterList.size() > 0) {
			for (CompanyMaster c : companyMasterList) {
				if (c.getLanguage() == 1) {
					modelEnglish.setCompanyId(c);
				} else {
					modelHindi.setCompanyId(c);
				}
			}

		}

		List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();
		officeMasterList = officeMasterRepository.findByCommonId(officeCommonId);
		if (officeMasterList.size() > 0) {
			for (OfficeMaster c : officeMasterList) {
				if (c.getLanguage() == 1) {
					modelEnglish.setOfficeId(c);
				} else {
					modelHindi.setOfficeId(c);
				}
			}

		}

		List<DeptMaster> deptMasterLi = new ArrayList<DeptMaster>();
		deptMasterLi = deptMasterRepository.findByCommonId(deptCommonId);
		if (deptMasterLi.size() > 0) {
			for (DeptMaster c : deptMasterLi) {
				if (c.getLanguage() == 1) {
					modelEnglish.setDeptId(c);
				} else {
					modelHindi.setDeptId(c);
				}
			}
		}

		Boolean b = false;
		try {
			b = divisionMasterRepository.existsByCompanyIdOfficeIdDeptIdAndDivisionName(
					modelEnglish.getCompanyId().getId(), modelEnglish.getOfficeId().getId(),
					modelEnglish.getDeptId().getId(), division);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// get Department Unique Check
	@RequestMapping(value = "/existDept", method = RequestMethod.GET)
	@ResponseBody
	public Boolean get_Dept_UniqCheck(HttpServletRequest request, @RequestParam("companyCommonId") Long companyCommonId,
			@RequestParam("officeCommonId") Long officeCommonId, @RequestParam("deptName") String deptName) {

		DeptMaster modelEnglish = new DeptMaster();
		DeptMaster modelHindi = new DeptMaster();

		List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();
		companyMasterList = companyMasterRepository.findByCommonId(companyCommonId);
		if (companyMasterList.size() > 0) {
			for (CompanyMaster c : companyMasterList) {
				if (c.getLanguage() == 1) {
					modelEnglish.setCompanyId(c);
				} else {
					modelHindi.setCompanyId(c);
				}
			}

		}

		List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();
		officeMasterList = officeMasterRepository.findByCommonId(officeCommonId);
		if (officeMasterList.size() > 0) {
			for (OfficeMaster c : officeMasterList) {
				if (c.getLanguage() == 1) {
					modelEnglish.setOfficeId(c);
				} else {
					modelHindi.setOfficeId(c);
				}
			}

		}

		Boolean b = false;
		try {
			b = deptMasterRepository.existsByCompanyIdOfficeIdAndDeptName(modelEnglish.getCompanyId().getId(),
					modelEnglish.getOfficeId().getId(), deptName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	// get Designation Unique Check
	@RequestMapping(value = "/existDesignation", method = RequestMethod.GET)
	@ResponseBody
	public Boolean get_Designation_UniqCheck(HttpServletRequest request,
			@RequestParam("designation") String designation) {

		Boolean b = false;
		try {
			b = designationMasterRepository.existsByDesignationName(designation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

}
