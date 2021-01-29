package com.cinfy.mlearning.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

//@EnableWebMvc
@Controller
public class StaticController {
	private static final Logger logger = LoggerFactory.getLogger(StaticController.class);


	@RequestMapping(value = "/email-security-awareness", method = RequestMethod.GET)
	public ModelAndView emailSecurityAwareness(Model model) {
    logger.info("into email security");
		//model.addAttribute("login", new Login());
		return new ModelAndView("email-security-awareness");
	}
	
	@RequestMapping(value = "/email-security-awareness-course-english", method = RequestMethod.GET)
	public ModelAndView emailSecurityAwarenessCourseEnglish(Model model) {
    logger.info("into email-security-awareness-course-english");
		//model.addAttribute("login", new Login());
		return new ModelAndView("email-security-awareness-course-english");
	}
	
	@RequestMapping(value = "/email-security-awareness-course-hindi", method = RequestMethod.GET)
	public ModelAndView emailSecurityAwarenessCourseHindi(Model model) {
    logger.info("into email-security-awareness-course-hindi");
		//model.addAttribute("login", new Login());
		return new ModelAndView("email-security-awareness-course-hindi");
	}
	
	@RequestMapping(value = "/staticCategory", method = RequestMethod.GET)
	public ModelAndView staticCategory(Model model) {
    logger.info("into email-security-awareness-course-hindi");
		//model.addAttribute("login", new Login());
		return new ModelAndView("ShowStaticCategory");
	}

}
