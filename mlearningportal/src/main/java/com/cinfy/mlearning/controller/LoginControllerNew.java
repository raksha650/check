package com.cinfy.mlearning.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.cinfy.mlearning.api.service.LoginService;
import com.cinfy.mlearning.commonmodel.CompanyMasterCommon;
import com.cinfy.mlearning.commonmodel.DeptMasterCommon;
import com.cinfy.mlearning.commonmodel.DesignationMasterCommon;
import com.cinfy.mlearning.commonmodel.DivisionMasterCommon;
import com.cinfy.mlearning.commonmodel.OfficeMasterCommon;
import com.cinfy.mlearning.model.CompanyMaster;
import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.DesignationMaster;
import com.cinfy.mlearning.model.DivisionMaster;
import com.cinfy.mlearning.model.Login;
import com.cinfy.mlearning.model.OfficeMaster;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.CompanyMasterRepository;
import com.cinfy.mlearning.model.repositories.DeptMasterRepository;
import com.cinfy.mlearning.model.repositories.DesignationMasterRepository;
import com.cinfy.mlearning.model.repositories.DistrictRepository;
import com.cinfy.mlearning.model.repositories.DivisionMasterRepository;
import com.cinfy.mlearning.model.repositories.OfficeMasterRepository;
import com.cinfy.mlearning.model.repositories.UserNewRegisterRepository;
import com.cinfy.mlearning.utils.FTPUtil;
import com.cinfy.mlearning.utils.InvoiceConstants;

//@EnableWebMvc
@Controller
public class LoginControllerNew {
	private static final Logger logger = LoggerFactory.getLogger(LoginControllerNew.class);

	@Autowired
	UserNewRegisterRepository userNewRegisterRepository;

	@Autowired
	DeptMasterRepository deptRepository;

	@Autowired
	DesignationMasterRepository designationMasterRepository;

	@Autowired
	DistrictRepository districtRepository;

	@Autowired
	CompanyMasterRepository companyMasterRepository;

	@Autowired
	OfficeMasterRepository officeMasterRepository;

	@Autowired
	DivisionMasterRepository divisionMasterRepository;

	@Value("${UPLOAD_PATH_USER_IMG}")
	private String UPLOAD_PATH_USER_IMG;

	// private static final String uploadingProfileImageDir =
	// "D://MLearning//profileImage//";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Model model) {
		logger.info("into index");
		model.addAttribute("login", new Login());
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView userLogin(Model model) {
		logger.info("into index");
		model.addAttribute("login", new Login());
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(Model model) {
		logger.info("into login");
		model.addAttribute("login", new Login());
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest request) {
		logger.info("Welcome indexPage! The client locale is 111");
		//ModelAndView mav = new ModelAndView("dashboard");
		ModelAndView mav = new ModelAndView("home");

		return mav;
	}

	// @RequestMapping(value = "/user_dashboard", method = RequestMethod.GET)
	// public ModelAndView userDashboard(HttpServletRequest request) {
	// logger.info("Welcome indexPage! The client locale is 111");
	// ModelAndView mav = new ModelAndView("user_dashboard");
	//
	// return mav;
	// }

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signUp(HttpServletRequest request) {
		logger.info("Welcome indexPage! The client locale is 111");
		ModelAndView mav = new ModelAndView("signup");

		return mav;
	}

	@RequestMapping(value = "/email-security-awareness2-detail", method = RequestMethod.GET)
	public ModelAndView emailsecurityawareness2detail(HttpServletRequest request) {
		logger.info("Welcome indexPage! The client locale is 111");
		ModelAndView mav = new ModelAndView("email-security-awareness2-detail");

		return mav;
	}

	@RequestMapping(value = "/email-security-awareness-course-english2-exam", method = RequestMethod.GET)
	public ModelAndView emailsecurityawarenesscourseenglish2exam(HttpServletRequest request) {
		logger.info("Welcome indexPage! The client locale is 111");
		ModelAndView mav = new ModelAndView("email-security-awareness-course-english2-exam");

		return mav;
	}

	@RequestMapping(value = "/loginProcessNew", method = RequestMethod.POST)
	public ModelAndView processFormNew(@Valid @ModelAttribute("login") Login login, BindingResult result, Map model,
			HttpSession session) {
		
		ModelAndView mav = new ModelAndView("login");
		try {

			String securePassword = Base64.getEncoder()
					.encodeToString(login.getPassword().getBytes(InvoiceConstants.UTF8));

			UserNew loggedInUser = userNewRegisterRepository.findByPasswordAndEmailOrPhoneAndRoleAllIgnoreCase(
					securePassword, login.getEmailId(), login.getPhone(), login.getRole());
			int lang = 1;
			
			

			if (loggedInUser != null) {

				if (lang == 1 && loggedInUser.getRole().equals("Admin")) {
					session.setAttribute("loginUser", loggedInUser);
					session.setAttribute("language", lang);
					return new ModelAndView("redirect:/dashboard?lang=en");

				}

			} else {

				mav = new ModelAndView("login");
				mav.addObject("error", "Not valid user! Try again.");

				return mav;
			}
		} catch (Exception ex) {
			System.out.println("in error Login Controll /loginProcess");
			ex.printStackTrace();

			mav = new ModelAndView("login");
			mav.addObject("Message", "Error in Login");
			return mav;

		}
		return mav;
	}

	@RequestMapping(value = "/create_user_new", method = RequestMethod.GET)
	public ModelAndView create_user_new(HttpServletRequest request) throws Exception {
		Integer language = (Integer) request.getSession().getAttribute("language");

		ModelAndView mav = new ModelAndView("create_user_new");

		List<UserNew> userList = null;
		try {
			userList = userNewRegisterRepository.findAllUser();
		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("managerList", userList);

		mav.addObject("districtList", districtRepository.findAll());
		UserNew user = null;

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
		mav.addObject("officeMasterList", officeMasterCommonList);

		List<DeptMaster> deptListLi = new ArrayList<DeptMaster>();
		List<DeptMasterCommon> deptListCommonLi = new ArrayList<DeptMasterCommon>();

		try {
			deptListLi = deptRepository.findAllOrderByCommonId();
			for (int i = 0; i < deptListLi.size(); i = i + 2) {

				DeptMasterCommon ccm = new DeptMasterCommon();

				if (deptListLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(deptListLi.get(i));
					ccm.setModelHindi(deptListLi.get(i + 1));

				} else {
					ccm.setModelHindi(deptListLi.get(i));
					ccm.setModelEnglish(deptListLi.get(i + 1));
				}

				deptListCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		mav.addObject("deptList", deptListCommonLi);

		List<DivisionMaster> divisionMasterList = new ArrayList<DivisionMaster>();
		List<DivisionMasterCommon> divisionMasterCommonList = new ArrayList<DivisionMasterCommon>();
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
		mav.addObject("divisionMasterList", divisionMasterCommonList);

		List<DesignationMaster> designationMasterLi = new ArrayList<DesignationMaster>();
		List<DesignationMasterCommon> designationMasterCommonLi = new ArrayList<DesignationMasterCommon>();
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
		mav.addObject("designationList", designationMasterCommonLi);

		String Message = request.getParameter("Message");
		mav.addObject("Message", Message);

		if (request.getParameter("id") != null) {
			try {

				Integer userId = Integer.parseInt(request.getParameter("id"));
				if (userId != null) {
					user = userNewRegisterRepository.findById(userId);
					// System.out.println("password securePassword_+_+" + user.getPassword());
					String securePassword = new String(
							Base64.getDecoder().decode(user.getPassword().getBytes(InvoiceConstants.UTF8)));
					user.setPassword(securePassword);
					// System.out.println("password +_+" + user.getPassword());

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// if (user.getCountryId() != null)
			// mav.addObject("stateList",
			// stateRepository.findAllByCountryId(user.getCountryId().getId()));
			//
			// if (user.getStateId() != null)
			// mav.addObject("cityList",
			// cityRepository.findAllByStateId(user.getStateId().getId()));

			mav.addObject("registration", user);
		} else {
			mav.addObject("registration", new UserNew());
		}

		return mav;
	}

	@RequestMapping(value = "/save_update_user_new", method = RequestMethod.POST)
	public ModelAndView save_update_user_new(HttpServletRequest request, @ModelAttribute("registration") UserNew user,
			@RequestParam("uploadProfileImage") MultipartFile uploadProfileImage, HttpSession session)
			throws Exception {
		logger.info("in controller user save");
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String Message = "";

		String securePassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes(InvoiceConstants.UTF8));

		user.setLanguageId(1);
		user.setPassword(securePassword);
		logger.error("!password--" + securePassword);

		List<CompanyMaster> companyMasterList = companyMasterRepository
				.findByCommonId(user.getCompanyId().getCommonId());
		for (CompanyMaster companyMaster : companyMasterList) {
			if (companyMaster.getLanguage() == 1) {
				user.setCompanyId(companyMaster);
			}
		}

		if (user.getOfficeId().getCommonId() != 0) {
			List<OfficeMaster> officeMasterList = officeMasterRepository
					.findByCommonId(user.getOfficeId().getCommonId());
			for (OfficeMaster officeMaster : officeMasterList) {
				if (officeMaster.getLanguage() == 1) {
					user.setOfficeId(officeMaster);
				}
			}
		} else {
			user.setOfficeId(null);
		}

		if (user.getDeptId().getCommonId() != 0) {
			List<DeptMaster> de = deptRepository.findByCommonId(user.getDeptId().getCommonId());
			for (DeptMaster deptMaster : de) {
				if (deptMaster.getLanguage() == 1) {
					user.setDeptId(deptMaster);
				}
			}
		} else {
			user.setDeptId(null);
		}

		if (user.getDivisionId().getCommonId() != 0) {
			List<DivisionMaster> divisionMasterList = divisionMasterRepository
					.findByCommonId(user.getDivisionId().getCommonId());
			for (DivisionMaster divisionMaster : divisionMasterList) {
				if (divisionMaster.getLanguage() == 1) {
					user.setDivisionId(divisionMaster);
				}
			}
		} else {
			user.setDivisionId(null);
		}

		// List<SubDeptMaster> subDeptMasters =
		// subDeptMasterRepository.findByCommonId(user.getSubDeptId().getCommonId());
		// for (SubDeptMaster subDeptMaster : subDeptMasters) {
		// if (subDeptMaster.getLanguage() == 1) {
		// user.setSubDeptId(subDeptMaster);
		// }
		// }
		List<DesignationMaster> designationMasters = designationMasterRepository
				.findByCommonId(user.getDesignationId().getCommonId());
		for (DesignationMaster designationMaster : designationMasters) {
			if (designationMaster.getLanguage() == 1) {
				user.setDesignationId(designationMaster);
			}
		}

		user.setCompanyId(loginUser.getCompanyId());

		if (user.getUserId() != null) {
			logger.info("user.getUserId()");
			user.setAddedDate(new Date());
			if (uploadProfileImage != null && !uploadProfileImage.getOriginalFilename().equals("")) {
				String uploadprofileImageFilePath = fileUploadInDirectory(uploadProfileImage, UPLOAD_PATH_USER_IMG);
				user.setProfileImagePath(uploadprofileImageFilePath);
			} else {
				user.setAddedDate(new Date());
				user.setProfileImagePath(userNewRegisterRepository.getOne(user.getUserId()).getProfileImagePath());
			}

			user.setPassword(securePassword);
			logger.error("!password1111--" + securePassword);
			userNewRegisterRepository.save(user);
			Message = "User Updated successfully";
		} else {

			logger.info("!user.getId()");
			UserNew isExist = userNewRegisterRepository.findByEmailOrPhoneAllIgnoreCase(user.getEmail(),
					user.getPhone());

			if (isExist == null) {
				user.setAddedDate(new Date());

				if (uploadProfileImage != null && !uploadProfileImage.getOriginalFilename().equals("")) {
					String uploadprofileImageFilePath = fileUploadInDirectory(uploadProfileImage, UPLOAD_PATH_USER_IMG);
					user.setProfileImagePath(uploadprofileImageFilePath);
				}
				user.setPassword(securePassword);
				logger.error("!password2222--" + securePassword);
				userNewRegisterRepository.save(user);

				Message = "User created successfully";
			} else {
				logger.info("isExist");
				Message = "User exist with same email or mobile";

			}
		}

		return new ModelAndView("redirect:/create_user_new?Message=" + Message);
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

	// To get a list
	@RequestMapping(value = "/user_list_new", method = RequestMethod.GET)
	public ModelAndView user_list_new(HttpServletRequest request) {

		// Integer language = (Integer) request.getSession().getAttribute("language");
		List<UserNew> userList = null;
		try {
			userList = userNewRegisterRepository.findAllUser();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("user_list");
		mav.addObject("userList", userList);
		return mav;
	}

	// To get User
	@RequestMapping(value = "/user_profile_new", method = RequestMethod.GET)
	public ModelAndView user_profile(HttpServletRequest request) {

		// Integer language = (Integer) request.getSession().getAttribute("language");
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		List<UserNew> userList = null;
		try {
			loginUser = userNewRegisterRepository.findById(loginUser.getUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("userProfile");
		mav.addObject("user", loginUser);
		return mav;
	}

	// For Log-Out
	@RequestMapping(value = "/logout")
	public ModelAndView doLogout(HttpSession session) {

		session.invalidate();
		return new ModelAndView("login", "login", new Login());
	}

}
