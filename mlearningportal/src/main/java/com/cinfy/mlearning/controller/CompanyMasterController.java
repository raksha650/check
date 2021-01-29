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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.cinfy.mlearning.commonmodel.CompanyMasterCommon;
import com.cinfy.mlearning.model.CompanyMaster;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.CompanyMasterRepository;
import com.cinfy.mlearning.utils.FTPUtil;

@Controller
public class CompanyMasterController {
	@Autowired
	CompanyMasterRepository companyMasterRepository;

	@Value("${UPLOAD_PATH_USER_IMG}")
	private String UPLOAD_PATH_USER_IMG;

	private static final Logger logger = LoggerFactory.getLogger(CompanyMasterController.class);

	@RequestMapping(value = "/companyMaster", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = request.getParameter("success");

		List<CompanyMaster> companyMasterLi = new ArrayList<CompanyMaster>();
		List<CompanyMasterCommon> companyMasterCommonLi = new ArrayList<CompanyMasterCommon>();

		CompanyMasterCommon companyMaster = new CompanyMasterCommon();

		String commonId = request.getParameter("commonId");

		try {
			if (commonId != null) {
				companyMasterLi = companyMasterRepository.findByCommonId(Long.parseLong(commonId));
				if (companyMasterLi.size() > 0) {
					for (CompanyMaster c : companyMasterLi) {
						if (c.getLanguage() == 1) {
							companyMaster.setModelEnglish(c);
						} else {
							companyMaster.setModelHindi(c);
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			companyMaster = new CompanyMasterCommon();
		}

		ModelAndView view = new ModelAndView("company_master");

		view.addObject("companyMaster", companyMaster);
		view.addObject("success", success);
		try {
			companyMasterLi = companyMasterRepository.findAllOrderByCommonId();
			for (int i = 0; i < companyMasterLi.size(); i = i + 2) {

				CompanyMasterCommon ccm = new CompanyMasterCommon();

				if (companyMasterLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(companyMasterLi.get(i));
					ccm.setModelHindi(companyMasterLi.get(i + 1));

				} else {
					ccm.setModelHindi(companyMasterLi.get(i));
					ccm.setModelEnglish(companyMasterLi.get(i + 1));
				}

				companyMasterCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("companyMasterList", companyMasterCommonLi);

		return view;
	}
	
	public String fileUploadInDirectory(MultipartFile aFile, String storeDir) {
		String fileNewPath = "";

		try {

			fileNewPath = FTPUtil.uplo(aFile, storeDir);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return fileNewPath;
	}

	@RequestMapping(value = "/saveOrUpdateCompanyMaster", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateFileProcess(HttpServletRequest request,
			@ModelAttribute("companyMaster") CompanyMasterCommon companyMasterCommon) {
		logger.info("## Course Content...");
		CompanyMaster res = new CompanyMaster();
		Integer language = (Integer) request.getSession().getAttribute("language");
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String success = "";

		
		Long newCommonId = System.currentTimeMillis();

		try {
			CompanyMaster modelHindi = companyMasterCommon.getModelHindi();
			CompanyMaster modelEnglish = companyMasterCommon.getModelEnglish();

			modelEnglish.setUpdatedDate(new Date());
			modelEnglish.setLanguage(1);
			modelEnglish.setDeleted(0);
			modelHindi.setUpdatedDate(new Date());
			modelHindi.setLanguage(2);
			modelHindi.setDeleted(0);
			modelEnglish.setUserId(loginUser.getUserId());
			modelHindi.setUserId(loginUser.getUserId());
			
			
			if (modelEnglish.getMultipartLogoFile() != null	&& !modelEnglish.getMultipartLogoFile().getName().isEmpty()) {
			String uploadPath= fileUploadInDirectory(modelEnglish.getMultipartLogoFile(), UPLOAD_PATH_USER_IMG);
			modelEnglish.setLogoPath(uploadPath);
			modelHindi.setLogoPath(uploadPath);
			}
			if (modelEnglish.getId() != null && modelHindi.getId() != null) {
                modelEnglish.setEntryDate(companyMasterRepository.findById(modelEnglish.getId()).getEntryDate());
                modelHindi.setEntryDate(companyMasterRepository.findById(modelHindi.getId()).getEntryDate());
				res = companyMasterRepository.save(modelEnglish);
				res = companyMasterRepository.save(modelHindi);
				success = "Company Master has been updated successfully";
			} else {
				modelEnglish.setEntryDate(new Date());
				modelEnglish.setCommonId(newCommonId);
				modelHindi.setEntryDate(new Date());
				modelHindi.setCommonId(newCommonId);

				res = companyMasterRepository.save(modelEnglish);
				res = companyMasterRepository.save(modelHindi);
				success = "Company Master has been created successfully";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			success = "Please try again !";
		}

		return new ModelAndView("redirect:/companyMaster?success=" + success);
	}



	@RequestMapping(value = "/deleteCompanyMaster", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam("commonId") Long commonId) throws Exception {

		String success = "";
	
		if (commonId != null) {
			companyMasterRepository.companyMasterDeleteCommon(commonId);
			success = "Company Master has been deleted successfully";
		}

		return new ModelAndView("redirect:/companyMaster?success=" + success);
	}

}
