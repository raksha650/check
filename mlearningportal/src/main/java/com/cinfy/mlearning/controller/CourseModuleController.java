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

import com.cinfy.mlearning.commonmodel.CourseCategoryCommon;
import com.cinfy.mlearning.commonmodel.CourseCommon;
import com.cinfy.mlearning.commonmodel.CourseModuleCommon;
import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.CourseCategory;
import com.cinfy.mlearning.model.CourseModule;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.CourseRepository;
import com.cinfy.mlearning.model.repositories.CourseCategoryRepository;
import com.cinfy.mlearning.model.repositories.CourseModuleRepository;

@Controller
public class CourseModuleController {
	@Autowired
	CourseModuleRepository courseModuleRepository;
	
	@Autowired
	CourseCategoryRepository courseCategoryRepository;

	@Autowired
	CourseRepository courseRepository;

	private static final Logger logger = LoggerFactory.getLogger(CourseModuleController.class);

	@RequestMapping(value = "/courseModule", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = request.getParameter("success");

		List<CourseModule> courseModuleLi = new ArrayList<CourseModule>();
		List<CourseModuleCommon> courseModuleCommonLi = new ArrayList<CourseModuleCommon>();

		CourseModuleCommon courseModule = new CourseModuleCommon();

		String commonId = request.getParameter("commonId");

		try {
			if (commonId != null) {
				courseModuleLi = courseModuleRepository.findByCommonId(Long.parseLong(commonId));
				if (courseModuleLi.size() > 0) {
					for (CourseModule c : courseModuleLi) {
						if (c.getLanguage() == 1) {
							courseModule.setModelEnglish(c);
						} else {
							courseModule.setModelHindi(c);
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			courseModule = new CourseModuleCommon();
		}

		ModelAndView view = new ModelAndView("courseModule");

		view.addObject("courseModule", courseModule);
		view.addObject("success", success);
		try {
			courseModuleLi = courseModuleRepository.findAllOrderByCommonId();
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
		
		List<Course> CourseLi = new ArrayList<Course>();
		List<CourseCommon> CourseCommonLi = new ArrayList<CourseCommon>();
		try {
			CourseLi = courseRepository.findAllOrderByCommonId();
			for (int i = 0; i < CourseLi.size(); i = i + 2) {

				CourseCommon ccm = new CourseCommon();

				if (CourseLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(CourseLi.get(i));
					ccm.setModelHindi(CourseLi.get(i + 1));

				} else {
					ccm.setModelHindi(CourseLi.get(i));
					ccm.setModelEnglish(CourseLi.get(i + 1));
				}

				CourseCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("courseList", CourseCommonLi);
		view.addObject("courseModuleList", courseModuleCommonLi);

		return view;
	}

	// Save And Update File
	@RequestMapping(value = "/saveOrUpdateCourseModule", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateFileProcess(HttpServletRequest request,
			@ModelAttribute("courseModule") CourseModuleCommon courseModuleCommon) {
		logger.info("## courseModule..");
		CourseModule res = new CourseModule();
		Integer language = (Integer) request.getSession().getAttribute("language");
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String success = "";

		Long newCommonId = System.currentTimeMillis();

		try {
			CourseModule modelHindi = courseModuleCommon.getModelHindi();
			CourseModule modelEnglish = courseModuleCommon.getModelEnglish();

			List<Course> CourseLi = new ArrayList<Course>();
			if (modelEnglish.getCourseId().getCommonId() != null) {
				CourseLi = courseRepository.findByCommonId(modelEnglish.getCourseId().getCommonId());
				if (CourseLi.size() > 0) {
					for (Course c : CourseLi) {
						if (c.getLanguage() == 1) {
							modelEnglish.setCourseId(c);
						} else {
							modelHindi.setCourseId(c);
						}
					}

				}
			}

			modelEnglish.setUpdatedDate(new Date());
			modelEnglish.setLanguage(1);
			modelEnglish.setDeleted(0);
			modelHindi.setUpdatedDate(new Date());
			modelHindi.setLanguage(2);
			modelHindi.setDeleted(0);
			modelEnglish.setUpdatedBy(loginUser.getUserId());
			modelHindi.setUpdatedBy(loginUser.getUserId());

			if (modelEnglish.getId() != null && modelHindi.getId() != null) {

				res = courseModuleRepository.save(modelEnglish);
				res = courseModuleRepository.save(modelHindi);
				success = "Course Module has been updated successfully";
			} else {
				modelEnglish.setCreatedBy(loginUser.getUserId());
				modelHindi.setCreatedBy(loginUser.getUserId());
				modelEnglish.setCreatedDate(new Date());
				modelEnglish.setCommonId(newCommonId);
				modelHindi.setCreatedDate(new Date());
				modelHindi.setCommonId(newCommonId);

				res = courseModuleRepository.save(modelEnglish);
				res = courseModuleRepository.save(modelHindi);
				success = "Course Module has been created successfully";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			success = "Please try again !";
		}

		return new ModelAndView("redirect:/courseModule?success=" + success);
	}

	@RequestMapping(value = "/deleteCourseModule", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam("commonId") Long commonId) throws Exception {

		String success = "";

		if (commonId != null) {
			courseModuleRepository.courseDeleteCommon(commonId);
			success = "Course Module has been deleted successfully";
		}

		return new ModelAndView("redirect:/courseModule?success=" + success);
	}

	@RequestMapping(value = "/courseModuleList", method = RequestMethod.GET)
	public ModelAndView getCourseModuleList(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {

		Integer courseId = Integer.parseInt(request.getParameter("courseId"));

		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		Integer language = (Integer) request.getSession().getAttribute("language");
		ModelAndView view = new ModelAndView("ShowStaticCategory");
		List<CourseModule> courseModules = null;
		try {

			if (courseId != null) {
				courseModules = this.courseModuleRepository.findSubjectBycourseId(courseId);
			}
			view.addObject("courseModulesList", courseModules);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

}
