package com.cinfy.mlearning.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinfy.mlearning.model.CourseAttempt;
import com.cinfy.mlearning.model.repositories.CourseAttemptRepository;

@Controller
public class CourseAttemptController {

	
	@Autowired
	CourseAttemptRepository courseAttemptRepository;

	private static final Logger logger = LoggerFactory.getLogger(CourseAttemptController.class);

	@RequestMapping(value = "/course_attempt_list", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		Integer language = (Integer) request.getSession().getAttribute("language");
	
		List<CourseAttempt> courseAttLi = new ArrayList<CourseAttempt>();
		
		try {
			
			courseAttLi = courseAttemptRepository.findAll();
			
		} catch (Exception e) {
				e.printStackTrace();
		}

		ModelAndView view = new ModelAndView("course_attempt");

		view.addObject("courseAttemptList", courseAttLi);

		return view;
	}

	

}
