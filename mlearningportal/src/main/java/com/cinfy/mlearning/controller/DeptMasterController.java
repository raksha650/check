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
import com.cinfy.mlearning.commonmodel.DeptMasterCommon;
import com.cinfy.mlearning.commonmodel.OfficeMasterCommon;
import com.cinfy.mlearning.model.CompanyMaster;
import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.OfficeMaster;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.CompanyMasterRepository;
import com.cinfy.mlearning.model.repositories.DeptMasterRepository;
import com.cinfy.mlearning.model.repositories.OfficeMasterRepository;

@Controller
public class DeptMasterController {
	@Autowired
	DeptMasterRepository deptMasterRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	OfficeMasterRepository officeMasterRepository;	

	private static final Logger logger = LoggerFactory.getLogger(DeptMasterController.class);

	@RequestMapping(value = "/deptMaster", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = request.getParameter("success");

		List<DeptMaster> deptMasterLi = new ArrayList<DeptMaster>();
		List<DeptMasterCommon> deptMasterCommonLi = new ArrayList<DeptMasterCommon>();

		DeptMasterCommon deptMaster = new DeptMasterCommon();

		String commonId = request.getParameter("commonId");

		try {
			if (commonId != null) {
				deptMasterLi = deptMasterRepository.findByCommonId(Long.parseLong(commonId));
				if (deptMasterLi.size() > 0) {
					for (DeptMaster c : deptMasterLi) {
						if (c.getLanguage() == 1) {
							deptMaster.setModelEnglish(c);
						} else {
							deptMaster.setModelHindi(c);
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			deptMaster = new DeptMasterCommon();
		}

		ModelAndView view = new ModelAndView("department_master");

		view.addObject("deptMaster", deptMaster);
		view.addObject("success", success);
		try {
			deptMasterLi = deptMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < deptMasterLi.size(); i = i + 2) {

				DeptMasterCommon ccm = new DeptMasterCommon();

				if (deptMasterLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(deptMasterLi.get(i));
					ccm.setModelHindi(deptMasterLi.get(i + 1));

				} else {
					ccm.setModelHindi(deptMasterLi.get(i));
					ccm.setModelEnglish(deptMasterLi.get(i + 1));
				}

				deptMasterCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("deptMasterList", deptMasterCommonLi);
		
		List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();
		List<CompanyMasterCommon> companyMasterCommonList = new ArrayList<CompanyMasterCommon>();
		
		List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();
		List<OfficeMasterCommon> officeMasterCommonList = new ArrayList<OfficeMasterCommon>();

		
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
		view.addObject("companyMasterList", companyMasterCommonList);
		view.addObject("officeMasterList", officeMasterCommonList);

		return view;
	}
	


	@RequestMapping(value = "/saveOrUpdateDeptMaster", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateFileProcess(HttpServletRequest request,
			@ModelAttribute("deptMaster") DeptMasterCommon deptMasterCommon) {
		logger.info("## saveOrUpdateDeptMaster Content...");
		DeptMaster res = new DeptMaster();
	
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String success = "";
		
		List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();
		List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();

		
		Long newCommonId = System.currentTimeMillis();

		try {
			DeptMaster modelHindi = deptMasterCommon.getModelHindi();
			DeptMaster modelEnglish = deptMasterCommon.getModelEnglish();

			modelEnglish.setUpdatedDate(new Date());
			modelEnglish.setLanguage(1);
			modelEnglish.setDeleted(0);			
			modelEnglish.setUserId(loginUser.getUserId());
			modelHindi.setUpdatedDate(new Date());
			modelHindi.setLanguage(2);
			modelHindi.setDeleted(0);
			modelHindi.setUserId(loginUser.getUserId());
			
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
			
			officeMasterList = officeMasterRepository.findByCommonId(modelEnglish.getOfficeId().getCommonId());

			if (officeMasterList.size() > 0) {
				for (OfficeMaster oM : officeMasterList) {
					if (oM.getLanguage() == 1) {
						modelEnglish.setOfficeId(oM);
					} else {
						modelHindi.setOfficeId(oM);
					}
				}

			}
			
			if (modelEnglish.getId() != null && modelHindi.getId() != null) {
                modelEnglish.setEntryDate(deptMasterRepository.findById(modelEnglish.getId()).getEntryDate());
                modelHindi.setEntryDate(deptMasterRepository.findById(modelHindi.getId()).getEntryDate());
				res = deptMasterRepository.save(modelEnglish);
				res = deptMasterRepository.save(modelHindi);
				success = "Department Master has been updated successfully";
			} else {
				modelEnglish.setEntryDate(new Date());
				modelEnglish.setCommonId(newCommonId);
				modelHindi.setEntryDate(new Date());
				modelHindi.setCommonId(newCommonId);

				res = deptMasterRepository.save(modelEnglish);
				res = deptMasterRepository.save(modelHindi);
				success = "Department Master has been created successfully";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			success = "Please try again !";
		}

		return new ModelAndView("redirect:/deptMaster?success=" + success);
	}



	@RequestMapping(value = "/deleteDeptMaster", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam("commonId") Long commonId) throws Exception {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = "";
	//	DeptMaster deptMaster = new DeptMaster();

		if (commonId != null) {
			deptMasterRepository.deptMasterDeleteCommon(commonId);
			success = "Department Master has been deleted successfully";
		}

		return new ModelAndView("redirect:/deptMaster?success=" + success);
	}

}
