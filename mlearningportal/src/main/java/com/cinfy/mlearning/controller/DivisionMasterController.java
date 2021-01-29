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
import com.cinfy.mlearning.commonmodel.DivisionMasterCommon;
import com.cinfy.mlearning.commonmodel.OfficeMasterCommon;
import com.cinfy.mlearning.model.CompanyMaster;
import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.DivisionMaster;
import com.cinfy.mlearning.model.OfficeMaster;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.CompanyMasterRepository;
import com.cinfy.mlearning.model.repositories.DeptMasterRepository;
import com.cinfy.mlearning.model.repositories.DivisionMasterRepository;
import com.cinfy.mlearning.model.repositories.OfficeMasterRepository;

@Controller
public class DivisionMasterController {
	@Autowired
	DeptMasterRepository deptMasterRepository;
	
	@Autowired
	CompanyMasterRepository companyMasterRepository;
	
	@Autowired
	OfficeMasterRepository officeMasterRepository;	
	
	@Autowired
	DivisionMasterRepository divisionMasterRepository;	

	private static final Logger logger = LoggerFactory.getLogger(DivisionMasterController.class);

	@RequestMapping(value = "/divisionMaster", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request,
			HttpSession session, HttpServletResponse response) {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = request.getParameter("success");

		List<DivisionMaster> divisionMasterList = new ArrayList<DivisionMaster>();
		List<DivisionMasterCommon> divisionMasterCommonList = new ArrayList<DivisionMasterCommon>();

		DivisionMasterCommon divisionMasterCommon = new DivisionMasterCommon();

		String commonId = request.getParameter("commonId");

		try {
			if (commonId != null) {
				divisionMasterList = divisionMasterRepository.findByCommonId(Long.parseLong(commonId));
				if (divisionMasterList.size() > 0) {
					for (DivisionMaster dm : divisionMasterList) {
						if (dm.getLanguage() == 1) {
							divisionMasterCommon.setModelEnglish(dm);
						} else {
							divisionMasterCommon.setModelHindi(dm);
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			divisionMasterCommon = new DivisionMasterCommon();
		}

		ModelAndView view = new ModelAndView("division_master");

		view.addObject("divisionMaster", divisionMasterCommon);
		view.addObject("success", success);
		
		try {
			divisionMasterList = divisionMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < divisionMasterList.size(); i = i + 2) {

				DivisionMasterCommon dmc = new DivisionMasterCommon();

				if (divisionMasterList.get(i).getLanguage() == 1) {
					dmc.setModelEnglish(divisionMasterList.get(i));
					dmc.setModelHindi(divisionMasterList.get(i + 1));

				} else {
					dmc.setModelHindi(divisionMasterList.get(i));
					dmc.setModelEnglish(divisionMasterList.get(i + 1));
				}

				divisionMasterCommonList.add(dmc);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("divisionMasterList", divisionMasterCommonList);
		
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
		
		List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();
		List<OfficeMasterCommon> officeMasterCommonList = new ArrayList<OfficeMasterCommon>();		
		try {
			officeMasterList = officeMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < officeMasterList.size(); i = i + 2) {

				OfficeMasterCommon omc = new OfficeMasterCommon();

				if (officeMasterList.get(i).getLanguage() == 1) {
					omc.setModelEnglish(officeMasterList.get(i));
					omc.setModelHindi(officeMasterList.get(i + 1));

				} else {
					omc.setModelHindi(officeMasterList.get(i));
					omc.setModelEnglish(officeMasterList.get(i + 1));
				}

				officeMasterCommonList.add(omc);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("officeMasterList", officeMasterCommonList);
		
		List<DeptMaster> deptMasterList = new ArrayList<DeptMaster>();
		List<DeptMasterCommon> deptMasterCommonList = new ArrayList<DeptMasterCommon>();

		
		try {
			deptMasterList = deptMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < deptMasterList.size(); i = i + 2) {

				DeptMasterCommon dmc = new DeptMasterCommon();

				if (deptMasterList.get(i).getLanguage() == 1) {
					dmc.setModelEnglish(deptMasterList.get(i));
					dmc.setModelHindi(deptMasterList.get(i + 1));

				} else {
					dmc.setModelHindi(deptMasterList.get(i));
					dmc.setModelEnglish(deptMasterList.get(i + 1));
				}

				deptMasterCommonList.add(dmc);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("deptMasterList", deptMasterCommonList);

		return view;
	}
	


	@RequestMapping(value = "/saveOrUpdateDivisionMaster", method = RequestMethod.POST)
	public ModelAndView saveOrUpdate(HttpServletRequest request,
			@ModelAttribute("divisionMaster") DivisionMasterCommon divisionMasterCommon) {
		logger.info("## saveOrUpdate Division Master Content...");
		
		DivisionMaster divisionMaster = new DivisionMaster();
	
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String success = "";
		
		List<CompanyMaster> companyMasterList = new ArrayList<CompanyMaster>();
		List<OfficeMaster> officeMasterList = new ArrayList<OfficeMaster>();
		List<DeptMaster> deptMasterList = new ArrayList<DeptMaster>();

		
		Long newCommonId = System.currentTimeMillis();

		try {
			DivisionMaster modelHindi = divisionMasterCommon.getModelHindi();
			DivisionMaster modelEnglish = divisionMasterCommon.getModelEnglish();

			modelEnglish.setUpdatedDate(new Date());
			modelEnglish.setLanguage(1);
			modelEnglish.setDeleted(0);			
			modelEnglish.setCreatedBy(loginUser.getUserId());
			modelHindi.setUpdatedDate(new Date());
			modelHindi.setLanguage(2);
			modelHindi.setDeleted(0);
			modelHindi.setCreatedBy(loginUser.getUserId());
			
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
			
			deptMasterList = deptMasterRepository.findByCommonId(modelEnglish.getDeptId().getCommonId());

			if (deptMasterList.size() > 0) {
				for (DeptMaster dM : deptMasterList) {
					if (dM.getLanguage() == 1) {
						modelEnglish.setDeptId(dM);
					} else {
						modelHindi.setDeptId(dM);
					}
				}

			}
			
			if (modelEnglish.getId() != null && modelHindi.getId() != null) {
				System.out.println("1--"+modelEnglish.getEntryDate());
                //modelEnglish.setEntryDate(divisionMasterRepository.findById(modelEnglish.getId()).getEntryDate());
                //modelHindi.setEntryDate(divisionMasterRepository.findById(modelHindi.getId()).getEntryDate());
                divisionMaster = divisionMasterRepository.save(modelEnglish);
                divisionMaster = divisionMasterRepository.save(modelHindi);
				success = "Division Master has been updated successfully";
			} else {
				modelEnglish.setEntryDate(new Date());
				modelEnglish.setCommonId(newCommonId);
				modelHindi.setEntryDate(new Date());
				modelHindi.setCommonId(newCommonId);

				divisionMaster = divisionMasterRepository.save(modelEnglish);
				divisionMaster = divisionMasterRepository.save(modelHindi);
				success = "Division Master has been created successfully";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			success = "Please try again !";
		}

		return new ModelAndView("redirect:/divisionMaster?success=" + success);
	}



	@RequestMapping(value = "/deleteDivisionMaster", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request,
			@RequestParam("commonId") Long commonId) throws Exception {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = "";
	
		if (commonId != null) {
			divisionMasterRepository.deptMasterDeleteCommon(commonId);
			success = "Division Master has been deleted successfully";
		}

		return new ModelAndView("redirect:/divisionMaster?success=" + success);
	}

}
