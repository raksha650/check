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
import com.cinfy.mlearning.model.CourseCategory;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.CourseCategoryRepository;



@Controller
public class CourseCategoryController {
	
	@Autowired
	CourseCategoryRepository courseCategoryRepository;

	private static final Logger logger = LoggerFactory.getLogger(CourseCategoryController.class);

	

	@RequestMapping(value = "/courseCategory", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = request.getParameter("success");

		List<CourseCategory> courseCategoryLi = new ArrayList<CourseCategory>();
		List<CourseCategoryCommon> courseCategoryCommonLi = new ArrayList<CourseCategoryCommon>();

		CourseCategoryCommon courseCategory = new CourseCategoryCommon();

		String commonId = request.getParameter("commonId");

		try {
			if (commonId != null) {
				courseCategoryLi = courseCategoryRepository.findByCommonId(Long.parseLong(commonId));
				if (courseCategoryLi.size() > 0) {
					for (CourseCategory c : courseCategoryLi) {
						if (c.getLanguage() == 1) {
							courseCategory.setModelEnglish(c);
						} else {
							courseCategory.setModelHindi(c);
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			courseCategory = new CourseCategoryCommon();
		}

		ModelAndView view = new ModelAndView("courseCategory");

		view.addObject("courseCategory", courseCategory);
		view.addObject("success", success);
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

		return view;
	}

	@RequestMapping(value = "/saveOrUpdateCourseCategory", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateFileProcess(HttpServletRequest request,
			@ModelAttribute("courseCategory") CourseCategoryCommon courseCategoryCommon) {
		logger.info("## Course Content...");
		CourseCategory res = new CourseCategory();
		Integer language = (Integer) request.getSession().getAttribute("language");
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String success = "";

		List<CourseCategory> courseCategoryLi = new ArrayList<CourseCategory>();
		List<CourseCategoryCommon> courseCategoryCommonLi = new ArrayList<CourseCategoryCommon>();
		Long newCommonId = System.currentTimeMillis();

		try {
			CourseCategory modelHindi = courseCategoryCommon.getModelHindi();
			CourseCategory modelEnglish = courseCategoryCommon.getModelEnglish();

			modelEnglish.setUpdatedDate(new Date());
			modelEnglish.setLanguage(1);
			modelEnglish.setDeleted(0);
			modelHindi.setUpdatedDate(new Date());
			modelHindi.setLanguage(2);
			modelHindi.setDeleted(0);
			modelEnglish.setUpdatedBy(loginUser.getUserId());
			modelHindi.setUpdatedBy(loginUser.getUserId());

			if (modelEnglish.getId() != null && modelHindi.getId() != null) {
                modelEnglish.setCreatedDate(courseCategoryRepository.findById(modelEnglish.getId()).getCreatedDate());
                modelHindi.setCreatedDate(courseCategoryRepository.findById(modelHindi.getId()).getCreatedDate());
              
				res = courseCategoryRepository.save(modelEnglish);
				res = courseCategoryRepository.save(modelHindi);
				success = "Course Category has been updated successfully";
			} else {
				  modelEnglish.setCreatedBy(loginUser.getUserId());
	                modelHindi.setCreatedBy(loginUser.getUserId());
				modelEnglish.setCreatedDate(new Date());
				modelEnglish.setCommonId(newCommonId);
				modelHindi.setCreatedDate(new Date());
				modelHindi.setCommonId(newCommonId);

				res = courseCategoryRepository.save(modelEnglish);
				res = courseCategoryRepository.save(modelHindi);
				success = "Course Category has been created successfully";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			success = "Please try again !";
		}

		return new ModelAndView("redirect:/courseCategory?success=" + success);
	}

	

	@RequestMapping(value = "/deleteCourseCategory", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam("commonId") Long commonId) throws Exception {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = "";
		CourseCategory courseCategory = new CourseCategory();

		if (commonId != null) {
			courseCategoryRepository.courseCategoryDeleteCommon(commonId);
			success = "Course Category has been deleted successfully";
		}

		return new ModelAndView("redirect:/courseCategory?success=" + success);
	}

}
