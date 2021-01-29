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

import com.cinfy.mlearning.commonmodel.CompanyMasterCommon;
import com.cinfy.mlearning.commonmodel.OfficeMasterCommon;
import com.cinfy.mlearning.model.CompanyMaster;
import com.cinfy.mlearning.model.OfficeMaster;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.CompanyMasterRepository;
import com.cinfy.mlearning.model.repositories.OfficeMasterRepository;

@Controller
public class OfficeMasterController {

	@Autowired
	OfficeMasterRepository officeMasterRepository;

	@Autowired
	CompanyMasterRepository companyMasterRepository;

	private static final Logger logger = LoggerFactory.getLogger(OfficeMasterController.class);

	@RequestMapping(value = "/officeMaster", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = request.getParameter("success");

		List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();
		List<OfficeMasterCommon> officeMasterCommonList = new ArrayList<OfficeMasterCommon>();

		OfficeMasterCommon officeMasterCommon = new OfficeMasterCommon();

		String commonId = request.getParameter("commonId");

		try {
			if (commonId != null) {
				officeMasterList = officeMasterRepository.findByCommonId(Long.parseLong(commonId));
				if (officeMasterList.size() > 0) {
					for (OfficeMaster officeMaster : officeMasterList) {
						if (officeMaster.getLanguage() == 1) {
							officeMasterCommon.setModelEnglish(officeMaster);
						} else {
							officeMasterCommon.setModelHindi(officeMaster);
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			officeMasterCommon = new OfficeMasterCommon();
		}

		ModelAndView view = new ModelAndView("office_master");
		view.addObject("officeMaster", officeMasterCommon);
		view.addObject("success", success);
		try {
			officeMasterList = officeMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < officeMasterList.size(); i = i + 2) {

				OfficeMasterCommon oMC = new OfficeMasterCommon();

				if (officeMasterList.get(i).getLanguage() == 1) {
					oMC.setModelEnglish(officeMasterList.get(i));
					oMC.setModelHindi(officeMasterList.get(i + 1));

				} else {
					oMC.setModelHindi(officeMasterList.get(i));
					oMC.setModelEnglish(officeMasterList.get(i + 1));
				}

				officeMasterCommonList.add(oMC);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("officeMasterList", officeMasterCommonList);

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
		return view;
	}

	@RequestMapping(value = "/saveOrUpdateOfficeMaster", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateOfficeMaster(HttpServletRequest request,
			@ModelAttribute("officeMaster") OfficeMasterCommon officeMasterCommon) {
		logger.info("## saveOrUpdate Office Master Content...");
		OfficeMaster officeMaster = new OfficeMaster();
		List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();

		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String success = "";

		Long newCommonId = System.currentTimeMillis();

		try {
			OfficeMaster modelHindi = officeMasterCommon.getModelHindi();
			OfficeMaster modelEnglish = officeMasterCommon.getModelEnglish();

			modelEnglish.setUpdatedDate(new Date());
			modelEnglish.setLanguage(1);
			modelEnglish.setDeleted(0);
			modelEnglish.setCreatedBy(loginUser.getUserId());
			modelHindi.setUpdatedDate(new Date());
			modelHindi.setLanguage(2);
			modelHindi.setDeleted(0);

			companyMasterList = companyMasterRepository.findByCommonId(modelEnglish.getCompanyId().getCommonId());

			if (companyMasterList.size() > 0) {
				for (CompanyMaster cM : companyMasterList) {
					if (cM.getLanguage() == 1) {
						modelEnglish.setCompanyId(cM);
					} else {
						modelHindi.setCompanyId(cM);
					}
				}

			}


			if (modelEnglish.getId() != null && modelHindi.getId() != null) {
				modelEnglish.setEntryDate(new Date());
				modelHindi.setEntryDate(new Date());
				officeMaster = officeMasterRepository.save(modelEnglish);
				officeMaster = officeMasterRepository.save(modelHindi);
				success = "Office Master has been updated successfully";
			} else {
				modelEnglish.setEntryDate(new Date());
				modelEnglish.setCommonId(newCommonId);
				modelHindi.setEntryDate(new Date());
				modelHindi.setCommonId(newCommonId);

				officeMaster = officeMasterRepository.save(modelEnglish);
				officeMaster = officeMasterRepository.save(modelHindi);
				success = "Office Master has been created successfully";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			success = "Please try again !";
		}

		return new ModelAndView("redirect:/officeMaster?success=" + success);
	}

	@RequestMapping(value = "/deleteOfficeMaster", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam("commonId") Long commonId) throws Exception {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = "";
		
		if (commonId != null) {
			officeMasterRepository.officeMasterDeleteCommon(commonId);
			success = "Office Master has been deleted successfully";
		}

		return new ModelAndView("redirect:/officeMaster?success=" + success);
	}

}
