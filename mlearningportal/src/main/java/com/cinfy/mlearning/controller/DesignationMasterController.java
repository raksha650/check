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

import com.cinfy.mlearning.commonmodel.DesignationMasterCommon;
import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.DesignationMaster;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.DeptMasterRepository;
import com.cinfy.mlearning.model.repositories.DesignationMasterRepository;

@Controller
public class DesignationMasterController {

	@Autowired
	DesignationMasterRepository designationMasterRepository;

	@Autowired
	DeptMasterRepository deptMasterRepository;

	private static final Logger logger = LoggerFactory.getLogger(DesignationMasterController.class);

	@RequestMapping(value = "/designationMaster", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = request.getParameter("success");

		List<DesignationMaster> designationMasterLi = new ArrayList<DesignationMaster>();
		List<DesignationMasterCommon> designationMasterCommonLi = new ArrayList<DesignationMasterCommon>();

		DesignationMasterCommon designationMaster = new DesignationMasterCommon();

		String commonId = request.getParameter("commonId");

		try {
			if (commonId != null) {
				designationMasterLi = designationMasterRepository.findByCommonId(Long.parseLong(commonId));
				if (designationMasterLi.size() > 0) {
					for (DesignationMaster c : designationMasterLi) {
						if (c.getLanguage() == 1) {
							designationMaster.setModelEnglish(c);
						} else {
							designationMaster.setModelHindi(c);
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			designationMaster = new DesignationMasterCommon();
		}

		ModelAndView view = new ModelAndView("designation_master_new");
		view.addObject("designationMaster", designationMaster);
		view.addObject("success", success);
		try {
			designationMasterLi = designationMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < designationMasterLi.size(); i = i + 2) {

				DesignationMasterCommon ccm = new DesignationMasterCommon();

				if (designationMasterLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(designationMasterLi.get(i));
					ccm.setModelHindi(designationMasterLi.get(i + 1));

				} else {
					ccm.setModelHindi(designationMasterLi.get(i));
					ccm.setModelEnglish(designationMasterLi.get(i + 1));
				}

				designationMasterCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("designationMasterList", designationMasterCommonLi);

		return view;
	}

	@RequestMapping(value = "/saveOrUpdateDesignationMaster", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateDesignationMaster(HttpServletRequest request,
			@ModelAttribute("designationMaster") DesignationMasterCommon designationMasterCommon) {
		logger.info("## saveOrUpdateDesignationMaster Content...");

		DesignationMaster res = new DesignationMaster();
		List<DeptMaster> deptMasterLi = new ArrayList<DeptMaster>();

		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String success = "";

		Long newCommonId = System.currentTimeMillis();

		try {
			DesignationMaster modelHindi = designationMasterCommon.getModelHindi();
			DesignationMaster modelEnglish = designationMasterCommon.getModelEnglish();

			modelEnglish.setUpdatedDate(new Date());
			modelEnglish.setLanguage(1);
			modelEnglish.setDeleted(0);
			modelEnglish.setCompanyId(loginUser.getCompanyId().getId());
			modelEnglish.setUserId(loginUser.getUserId());
			modelHindi.setUpdatedDate(new Date());
			modelHindi.setLanguage(2);
			modelHindi.setDeleted(0);
			modelHindi.setCompanyId(loginUser.getCompanyId().getId());
			modelHindi.setUserId(loginUser.getUserId());

			if (modelEnglish.getId() != null && modelHindi.getId() != null) {
				res = designationMasterRepository.save(modelEnglish);
				res = designationMasterRepository.save(modelHindi);
				success = "Designation Master has been updated successfully";
			} else {
				modelEnglish.setEntryDate(new Date());
				modelEnglish.setCommonId(newCommonId);
				modelHindi.setEntryDate(new Date());
				modelHindi.setCommonId(newCommonId);

				res = designationMasterRepository.save(modelEnglish);
				res = designationMasterRepository.save(modelHindi);
				success = "Designation Master has been created successfully";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			success = "Please try again !";
		}

		return new ModelAndView("redirect:/designationMaster?success=" + success);
	}

	@RequestMapping(value = "/deleteDesignationMaster", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam("commonId") Long commonId) throws Exception {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = "";

		if (commonId != null) {
			designationMasterRepository.designationMasterDeleteCommon(commonId);
			success = "Designation Master has been deleted successfully";
		}

		return new ModelAndView("redirect:/designationMaster?success=" + success);
	}

}
