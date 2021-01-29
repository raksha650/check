package com.cinfy.mlearning.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cinfy.mlearning.commonmodel.CourseCategoryCommon;
import com.cinfy.mlearning.commonmodel.CourseCommon;
import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.CourseCategory;
import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.CourseRepository;
import com.cinfy.mlearning.model.repositories.UserNewRegisterRepository;
import com.cinfy.mlearning.utils.FTPUtil;
import com.cinfy.mlearning.model.repositories.CourseCategoryRepository;

@Controller
public class CourseController {
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseCategoryRepository courseCategoryRepository;

	@Autowired
	UserNewRegisterRepository userNewRegisterRepository;


	@Value("${UPLOAD_PATH_COURSE_IMG}")
	private String UPLOAD_PATH_COURSE_IMG;

	private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		String success = request.getParameter("success");

		List<Course> courseLi = new ArrayList<Course>();
		List<CourseCommon> courseCommonLi = new ArrayList<CourseCommon>();

		CourseCommon course = new CourseCommon();

		String commonId = request.getParameter("commonId");

		try {
			if (commonId != null) {
				courseLi = courseRepository.findByCommonId(Long.parseLong(commonId));
				if (courseLi.size() > 0) {
					for (Course c : courseLi) {
						if (c.getLanguage() == 1) {
							course.setModelEnglish(c);
						} else {
							course.setModelHindi(c);
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			course = new CourseCommon();
		}

		ModelAndView view = new ModelAndView("course");

		view.addObject("course", course);
		view.addObject("success", success);
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
		view.addObject("courseList", courseCommonLi);
		view.addObject("courseCategoryList", courseCategoryCommonLi);

		return view;
	}

	// Save And Update File
	@RequestMapping(value = "/saveOrUpdateCourse", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateCourse(HttpServletRequest request,
			@ModelAttribute("course") CourseCommon courseCommon,
			@RequestParam("courseImage") MultipartFile courseImage) {
		logger.info("## course..");
		Course res = new Course();
		Integer language = (Integer) request.getSession().getAttribute("language");
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String success = "";

		Long newCommonId = System.currentTimeMillis();

		try {
			Course modelHindi = courseCommon.getModelHindi();
			Course modelEnglish = courseCommon.getModelEnglish();

			List<CourseCategory> CourseCategoryLi = new ArrayList<CourseCategory>();
			if (modelEnglish.getCourseCategoryId().getCommonId() != null) {
				CourseCategoryLi = courseCategoryRepository
						.findByCommonId(modelEnglish.getCourseCategoryId().getCommonId());
				if (CourseCategoryLi.size() > 0) {
					for (CourseCategory c : CourseCategoryLi) {
						if (c.getLanguage() == 1) {
							modelEnglish.setCourseCategoryId(c);
						} else {
							modelHindi.setCourseCategoryId(c);
						}
					}

				}
			}

			modelHindi.setIsCompliance(modelEnglish.getIsCompliance());
			modelEnglish.setUpdatedDate(new Date());
			modelEnglish.setLanguage(1);
			modelEnglish.setDeleted(0);
			modelHindi.setUpdatedDate(new Date());
			modelHindi.setLanguage(2);
			modelHindi.setDeleted(0);
			modelEnglish.setUpdatedBy(loginUser.getUserId());
			modelHindi.setUpdatedBy(loginUser.getUserId());
			
			if (courseImage != null && !courseImage.getOriginalFilename().equals("")) {
				String uploadCourseImageFilePath = fileUploadInDirectory(courseImage, UPLOAD_PATH_COURSE_IMG);
				modelEnglish.setCourseImagePath(uploadCourseImageFilePath);
				modelHindi.setCourseImagePath(uploadCourseImageFilePath);
			}

			if (modelEnglish.getId() != null && modelHindi.getId() != null) {
				res = courseRepository.save(modelEnglish);
				res = courseRepository.save(modelHindi);
				success = "course has been updated successfully";
			} else {
				modelEnglish.setCreatedBy(loginUser.getUserId());
				modelHindi.setCreatedBy(loginUser.getUserId());
				modelEnglish.setCreatedDate(new Date());
				modelEnglish.setCommonId(newCommonId);
				modelHindi.setCreatedDate(new Date());
				modelHindi.setCommonId(newCommonId);

				res = courseRepository.save(modelEnglish);
				res = courseRepository.save(modelHindi);
				success = "course has been created successfully";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			success = "Please try again !";
		}

		return new ModelAndView("redirect:/course?success=" + success);
	}

	@RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam("commonId") Long commonId) throws Exception {

		String success = "";

		if (commonId != null) {
			courseRepository.courseDeleteCommon(commonId);
			success = "course has been deleted successfully";
		}

		return new ModelAndView("redirect:/course?success=" + success);
	}

//	@RequestMapping(value = "/courseType", method = RequestMethod.GET)
//	public ModelAndView getCourseType(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
//
//		Integer courseCategoryType = Integer.parseInt(request.getParameter("courseCategoryType"));
//
//		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
//		Integer language = (Integer) request.getSession().getAttribute("language");
//		ModelAndView view = new ModelAndView("ShowStaticCategory");
//		List<Course> courses = null;
//		try {
//			// List<Course> courses = new ArrayList<Course>();
//
//			if (loginUser != null && language != null && courseCategoryType != null) {
//
//				DeptMaster deptMaster = userNewRegisterRepository.findByUserIdAndDeleted(loginUser.getUserId(), 0)
//						.getDeptId();
//
//				if (courseCategoryType == 0) {// for non compliance
//					courses = deptCourseMasterRepository
//							.findAllByDeptIdCommonIdAndDeptIdLanguageAndIsComplianceForCourseFetch(
//									deptMaster.getCommonId(), language, false);
//				} else if (courseCategoryType == 1) {// for compliance
//					
//					courses = deptCourseMasterRepository
//							.findAllByDeptIdCommonIdAndDeptIdLanguageAndIsComplianceForCourseFetch(
//									deptMaster.getCommonId(), language, true);
//					for (Course course : courses) {
//						System.out.println("coursename--" + course.getName());
//					}
//				} else if (courseCategoryType == 2) {// for pending (complete in 7 days)
//
//					Calendar cal = Calendar.getInstance();
//					Date today = cal.getTime();
//					cal.add(Calendar.DAY_OF_MONTH, 7);
//					Date expiryDate = cal.getTime();
//					java.sql.Date nextDt = new java.sql.Date(expiryDate.getTime());
//					java.sql.Date currentDt = new java.sql.Date(new Date().getTime());
//
//					courses = deptCourseMasterRepository
//							.findAllByDeptIdCommonIdAndDeptIdLanguageAndComplianceDatewithin7daysForCourseFetch(
//									deptMaster.getCommonId(), language, currentDt, nextDt);
//
//				}
//				view.addObject("courseList", courses);
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return view;
//	}

	public String fileUploadInDirectory(MultipartFile aFile, String storeDir) {
		String fileNewPath = "";

		try {
			fileNewPath = FTPUtil.uplo(aFile, storeDir);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileNewPath;
	}

}
