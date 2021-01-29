package com.cinfy.mlearning.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinfy.mlearning.model.AssessmentLogDetails;
import com.cinfy.mlearning.model.repositories.AssessmentLogDetailsRepository;
import com.cinfy.mlearning.model.repositories.CourseModuleRepository;
import com.cinfy.mlearning.model.repositories.UserNewRegisterRepository;

@Controller
public class AssessmentLogDetailsController {
	@Autowired
	AssessmentLogDetailsRepository assessmentLogDetailsRepository;

	@Autowired
	CourseModuleRepository courseModuleRepository;

	@Autowired
	UserNewRegisterRepository userNewRegisterRepository;

	// To get a list
	@RequestMapping(value = "/assessmentLogDetails", method = RequestMethod.GET)
	public ModelAndView assessment_log_details(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("assessmentLogDetails_list");

		mav.addObject("courseModuleList", courseModuleRepository.findAll());
		mav.addObject("userList", userNewRegisterRepository.findAll());

		List<AssessmentLogDetails> assessmentLogDetailsList = null;
		try {
			assessmentLogDetailsList = assessmentLogDetailsRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("assessmentLogDetailsList", assessmentLogDetailsList);
		return mav;
	}

	@RequestMapping(value = "/searchAssessmentLogDetails", method = RequestMethod.POST)
	public ModelAndView search_course_view_log_details(HttpServletRequest request) {

		String userId = request.getParameter("userId");
		String courseModuleId = request.getParameter("courseModuleId");
		System.out.println("cmid--" + courseModuleId + "uid---" + userId);
		List<AssessmentLogDetails> assessmentLogDetailsList = null;

		if (userId != null) {
			assessmentLogDetailsList = assessmentLogDetailsRepository.
					findByUserId(Integer.parseInt(userId));
		}

		if (courseModuleId != null) {
			assessmentLogDetailsList = assessmentLogDetailsRepository
					.findByCourseModuleId(Integer.parseInt(courseModuleId));
		}

		if (userId == null && courseModuleId == null) {
			assessmentLogDetailsList = assessmentLogDetailsRepository.findAll();
		}

		ModelAndView mav = new ModelAndView("courseViewLogDetails_list");

		mav.addObject("assessmentLogDetailsList", assessmentLogDetailsList);
		mav.addObject("courseModuleList", courseModuleRepository.findAll());
		mav.addObject("userList", userNewRegisterRepository.findAll());
		return mav;
	}
}
