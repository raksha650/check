package com.cinfy.mlearning.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinfy.mlearning.commonmodel.CompanyMasterCommon;
import com.cinfy.mlearning.model.CompanyMaster;
import com.cinfy.mlearning.model.CourseViewLogDetails;
import com.cinfy.mlearning.model.repositories.CompanyMasterRepository;
import com.cinfy.mlearning.model.repositories.CourseModuleRepository;
import com.cinfy.mlearning.model.repositories.CourseViewLogDetailsRepository;
import com.cinfy.mlearning.model.repositories.UserNewRegisterRepository;

@Controller
public class CourseViewLogDetailsController {
	@Autowired
	CourseViewLogDetailsRepository courseViewLogDetailsRepository;

	@Autowired
	CourseModuleRepository courseModuleRepository;
	
	@Autowired
	UserNewRegisterRepository userNewRegisterRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	// To get a list
	@RequestMapping(value = "/courseViewLogDetails", method = RequestMethod.GET)
	public ModelAndView course_view_log_details(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("courseViewLogDetails_list");
		
		mav.addObject("courseModuleList", courseModuleRepository.findAll());
		//mav.addObject("userList", userNewRegisterRepository.findAll());

		List<CourseViewLogDetails> courseViewLogDetailsList = null;
		try {
			courseViewLogDetailsList = courseViewLogDetailsRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		mav.addObject("companyMasterList", companyMasterCommonList);
		
		mav.addObject("courseViewLogDetailsList", courseViewLogDetailsList);
		//mav.addObject("courseViewLogDetails", new CourseViewLogDetails());
		return mav;
		
	}
	
	@RequestMapping(value = "/searchCourseViewLogDetails", method = RequestMethod.POST)
	public ModelAndView search_course_view_log_details(HttpServletRequest request) {
		
	    String userId = request.getParameter("userId");
		String courseModuleId = request.getParameter("courseModuleId");
		System.out.println("cmid--"+courseModuleId+"uid---"+userId);
		List<CourseViewLogDetails> courseViewLogDetailsList = null;
//		List<UserNew> userList = null;
//		List<CourseModule> courseModuleList = null;
		if(userId != null) {
			courseViewLogDetailsList = courseViewLogDetailsRepository.findByUserId(Integer.parseInt(userId));
		}
		
		if(courseModuleId != null) {
			courseViewLogDetailsList = courseViewLogDetailsRepository.findByCourseModuleId(Integer.parseInt(courseModuleId));
		}
		
		if(userId == null && courseModuleId == null) {
			courseViewLogDetailsList = courseViewLogDetailsRepository.findAll();
		}
		
		ModelAndView mav = new ModelAndView("courseViewLogDetails_list");
		
				
		mav.addObject("courseViewLogDetailsList", courseViewLogDetailsList);
		mav.addObject("courseModuleList", courseModuleRepository.findAll());
		mav.addObject("userList", userNewRegisterRepository.findAll());
		return mav;
	}
}
