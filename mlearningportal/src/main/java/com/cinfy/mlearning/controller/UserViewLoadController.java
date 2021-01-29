package com.cinfy.mlearning.controller;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.cinfy.mlearning.api.service.CourseService;
import com.cinfy.mlearning.model.AssessmentLogDetails;
import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.CourseAssignMaster;
import com.cinfy.mlearning.model.CourseAttempt;
import com.cinfy.mlearning.model.CourseContent;
import com.cinfy.mlearning.model.CourseModule;
import com.cinfy.mlearning.model.CourseUpload;
import com.cinfy.mlearning.model.CourseViewLogDetails;
import com.cinfy.mlearning.model.Login;
import com.cinfy.mlearning.model.Question;
import com.cinfy.mlearning.model.QuestionListAccess;
import com.cinfy.mlearning.model.UserDashboardLogs;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.AssessmentLogDetailsRepository;
import com.cinfy.mlearning.model.repositories.CourseAssignMasterRepository;
import com.cinfy.mlearning.model.repositories.CourseAttemptRepository;
import com.cinfy.mlearning.model.repositories.CourseContentRepository;
import com.cinfy.mlearning.model.repositories.CourseModuleRepository;
import com.cinfy.mlearning.model.repositories.CourseUploadRepository;
import com.cinfy.mlearning.model.repositories.CourseViewLogDetailsRepository;
import com.cinfy.mlearning.model.repositories.QuestionRepository;
import com.cinfy.mlearning.model.repositories.UserNewRegisterRepository;
import com.cinfy.mlearning.utils.InvoiceConstants;

@Controller
public class UserViewLoadController {

	@Autowired
	private CourseAssignMasterRepository courseAssignMasterRepository;

	@Autowired
	CourseService courseService;

	@Autowired
	private AssessmentLogDetailsRepository assessmentLogDetailsRepository;

	@Autowired
	UserNewRegisterRepository userNewRegisterRepository;

	@Autowired
	private CourseModuleRepository courseModuleRepository;

	@Autowired
	CourseContentRepository courseContentRepository;

	@Autowired
	CourseUploadRepository courseUploadRepository;

	@Autowired
	private CourseViewLogDetailsRepository courseViewLogDetailsRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private CourseAttemptRepository courseAttemptRepository;

	private static final Logger logger = LoggerFactory.getLogger(UserViewLoadController.class);

	@RequestMapping(value = "/user_login", method = RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request, @ModelAttribute("login") Login login,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("user_dashboard");
		mav.addObject("login", new Login());

		try {
			

			String securePassword = Base64.getEncoder()
					.encodeToString(login.getPassword().getBytes(InvoiceConstants.UTF8));

			UserNew loggedInUser = userNewRegisterRepository.findByPasswordAndEmailOrPhoneAndRoleAllIgnoreCase(
					securePassword, login.getEmailId(), login.getPhone(), login.getRole());
			Integer language = 1;

			if (loggedInUser != null) {

				if (language == 1 && loggedInUser.getRole().equals("User")) {
					session.setAttribute("loginUser", loggedInUser);
					session.setAttribute("language", language);

					if (!loggedInUser.getIsResetPwd()) {
						ModelAndView setpwd = new ModelAndView("user_first_setpassword");
						loggedInUser.setPassword(null);
						setpwd.addObject("user", loggedInUser);
						return setpwd;
					}

					return new ModelAndView("redirect:user_dashboard");

				} else {
					mav = new ModelAndView("login");
					mav.addObject("error", "Not valid user! Try again.");

					return mav;
				}
			} else {
				mav = new ModelAndView("login");
				mav.addObject("login", new Login());
				mav.addObject("error", "Not valid user! Try again.");

				return mav;
			}

		} catch (Exception ex) {
			System.out.println("in error user view load Controll /loginProcess");
			ex.printStackTrace();

			mav = new ModelAndView("login");

			mav.addObject("login", new Login());
			mav.addObject("error", "Error in Login");
			return mav;

		}

	}

	@RequestMapping(value = "/set_first_password", method = RequestMethod.POST)
	public ModelAndView set_first_password(HttpServletRequest request, @ModelAttribute("user") UserNew user,
			HttpSession session) {
		ModelAndView mav = new ModelAndView("login");

		try {

			String securePassword = Base64.getEncoder()
					.encodeToString(user.getPassword().getBytes(InvoiceConstants.UTF8));

			UserNew loggedInUser = userNewRegisterRepository.findByUserIdAndDeleted(user.getUserId(), 0);
			System.out.println("loggedInUser " + loggedInUser.getEmail() + +loggedInUser.getUserId());

			Integer language = 1;

			if (loggedInUser != null) {
				loggedInUser.setIsResetPwd(true);
				loggedInUser.setPassword(securePassword);
				userNewRegisterRepository.save(loggedInUser);

				mav.addObject("login", new Login());
				mav.addObject("success", "You have changed password successfully.Please login again.");
				session.invalidate();

			}

		} catch (Exception ex) {
			System.out.println("in error user view load Controll /loginProcess");
			ex.printStackTrace();

			mav = new ModelAndView("login");

			mav.addObject("login", new Login());
			mav.addObject("error", "Error in Login");
			return mav;

		}
		return mav;

	}

	@RequestMapping(value = "/user_dashboard", method = RequestMethod.GET)
	public ModelAndView userDashboard(HttpServletRequest request, HttpSession session) {
		UserNew loggedInUser = (UserNew) request.getSession().getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new Login());
		Integer language = 1;
		UserDashboardLogs userDashboardLogs = new UserDashboardLogs();

		try {
			if (loggedInUser != null) {

				if (language == 1 && loggedInUser.getRole().equals("User")) {

					mav.setViewName("user_dashboard");

					List<Course> courseList = new ArrayList<Course>();
					List<Course> courseListTemp = new ArrayList<Course>();

					List<CourseAssignMaster> courseAssignList = new ArrayList<CourseAssignMaster>();

					// not compulsory
					Integer isCompliance = 0;

					try {
						if (loggedInUser.getCompanyId() != null) {
							// System.out.println("11--");
							courseAssignList = courseAssignMasterRepository
									.findAllByCompanyId(loggedInUser.getCompanyId().getId());
							for (CourseAssignMaster courseAssignMaster : courseAssignList) {
								if (courseAssignMaster.getCompanyId() != null
										&& courseAssignMaster.getOfficeId() == null
										&& courseAssignMaster.getDeptId() == null
										&& courseAssignMaster.getDivisionId() == null) {
									// System.out.println("1--");
									courseListTemp = courseAssignMasterRepository
											.findAllByCompanyIdAndCompanyIdLanguageAndIsComplianceForCourseFetch(
													loggedInUser.getCompanyId().getId(), language, false);
								}
							}
						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}
						if (loggedInUser.getOfficeId() != null) {
							// System.out.println("12--");
							courseAssignList = courseAssignMasterRepository
									.findAllByOfficeId(loggedInUser.getOfficeId().getId());
							if (courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if (courseAssignMaster.getCompanyId() != null
											&& courseAssignMaster.getOfficeId() != null
											&& courseAssignMaster.getDeptId() == null
											&& courseAssignMaster.getDivisionId() == null) {
										// System.out.println("2--");
										courseList = courseAssignMasterRepository
												.findAllByOfficeIdAndOfficeIdLanguageAndIsComplianceForCourseFetch(
														loggedInUser.getOfficeId().getId(), language, false);
									}
								}
							}

						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}

						if (loggedInUser.getDeptId() != null) {
							System.out.println("13--");
							courseAssignList = courseAssignMasterRepository
									.findAllByDeptId(loggedInUser.getDeptId().getId());
							if (courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {

									if (courseAssignMaster.getCompanyId() != null
											&& courseAssignMaster.getOfficeId() != null
											&& courseAssignMaster.getDeptId() != null
											&& courseAssignMaster.getDivisionId() == null) {
										System.out.println("3--");
										courseList = courseAssignMasterRepository
												.findAllByDeptIdAndDeptIdLanguageAndIsComplianceForCourseFetch(
														loggedInUser.getDeptId().getId(), language, false);
									}
								}
							}

						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}
						if (loggedInUser.getDivisionId() != null) {

							courseAssignList = courseAssignMasterRepository
									.findAllByDivisionId(loggedInUser.getDivisionId().getId());
							if (courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if (courseAssignMaster.getCompanyId() != null
											&& courseAssignMaster.getOfficeId() != null
											&& courseAssignMaster.getDeptId() != null
											&& courseAssignMaster.getDivisionId() != null) {

										courseList = courseAssignMasterRepository
												.findAllByDivisionIdAndDivisionIdLanguageAndIsComplianceForCourseFetch(
														loggedInUser.getDivisionId().getId(), language, false);
									}
								}
							}

						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					List<Course> deduped0 = courseList.stream().distinct().collect(Collectors.toList());
					mav.addObject("NonCompliance", deduped0);

					Integer totalCourseModeulesInDB = this.courseModuleRepository.findAllCount();
					Integer totalCourseModeulesAssignedToUser = 0;

					List<CourseModule> totalIncompleteModulesNonCompliance = new ArrayList<CourseModule>();
					List<CourseModule> totalCompleteModulesNonCompliance = new ArrayList<CourseModule>();
					Integer totalIncompleteModulesNonComplianceCount = 0;
					Integer totalCompleteModulesNonComplianceCount = 0;
					List<CourseModule> totalPassNonCompliance = new ArrayList<CourseModule>();
					List<CourseModule> totalFailNonCompliance = new ArrayList<CourseModule>();
					Integer totalFailNonComplianceCount = 0;
					Integer totalPassNonComplianceCount = 0;
					List<CourseModule> courseModulePendingNonCompliance = new ArrayList<CourseModule>();
					for (Course course : deduped0) {

						List<CourseModule> courseModules = this.courseModuleRepository
								.findSubjectBycourseId(course.getId());
						totalCourseModeulesAssignedToUser = totalCourseModeulesAssignedToUser + this.courseModuleRepository.findCountBycourseId(course.getId());
		               
						for (CourseModule courseModule : courseModules) {

							CourseAttempt courseAttempt = courseAttemptRepository
									.findByUserIdCourseModuleId(loggedInUser.getUserId(), courseModule.getId());
							if (courseAttempt != null) {
								if (courseAttempt.getIsCourseCompleted()) {
									totalCompleteModulesNonCompliance.add(courseAttempt.getCourseModuleId());
									totalCompleteModulesNonComplianceCount++;
								} else {
									totalIncompleteModulesNonCompliance.add(courseAttempt.getCourseModuleId());
									totalIncompleteModulesNonComplianceCount++;
								}

								if (courseAttempt.getIsAssessmentPass() != null) {
									if (courseAttempt.getIsAssessmentPass()) {
										totalPassNonCompliance.add(courseAttempt.getCourseModuleId());
										totalPassNonComplianceCount++;
									} else {
										totalFailNonCompliance.add(courseAttempt.getCourseModuleId());
										totalFailNonComplianceCount++;
									}
								}

							} else {
								courseModulePendingNonCompliance.add(courseModule);
							}

						}

					}
					userDashboardLogs.setCourseModulePendingNonCompliance(courseModulePendingNonCompliance);
					userDashboardLogs.setTotalCourseModulePendingNonCompliance(courseModulePendingNonCompliance.size());

					userDashboardLogs.setTotalCompleteModulesNonCompliance(totalCompleteModulesNonCompliance);
					userDashboardLogs
							.setTotalCompleteModulesNonComplianceCount(totalCompleteModulesNonCompliance.size());
					userDashboardLogs.setTotalIncompleteModulesNonCompliance(totalIncompleteModulesNonCompliance);
					userDashboardLogs
							.setTotalIncompleteModulesNonComplianceCount(totalIncompleteModulesNonCompliance.size());

					userDashboardLogs.setTotalPassNonCompliance(totalPassNonCompliance);
					userDashboardLogs.setTotalPassNonComplianceCount(totalPassNonCompliance.size());
					userDashboardLogs.setTotalFailNonCompliance(totalFailNonCompliance);
					userDashboardLogs.setTotalFailNonComplianceCount(totalFailNonCompliance.size());

					// for compliance
					isCompliance = 1;
					courseList = new ArrayList<Course>();
					try {
						if (loggedInUser.getCompanyId() != null) {
							courseAssignList = courseAssignMasterRepository
									.findAllByCompanyId(loggedInUser.getCompanyId().getId());
							for (CourseAssignMaster courseAssignMaster : courseAssignList) {
								if (courseAssignMaster.getCompanyId().getId() != null
										&& courseAssignMaster.getOfficeId() == null
										&& courseAssignMaster.getDeptId() == null
										&& courseAssignMaster.getDivisionId() == null) {

									courseListTemp = courseAssignMasterRepository
											.findAllByCompanyIdAndCompanyIdLanguageAndIsComplianceForCourseFetch(
													loggedInUser.getCompanyId().getId(), language, true);
								}
							}
						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}
						if (loggedInUser.getOfficeId() != null) {
							courseAssignList = courseAssignMasterRepository
									.findAllByOfficeId(loggedInUser.getOfficeId().getId());
							if (courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if (courseAssignMaster.getCompanyId().getId() != null
											&& courseAssignMaster.getOfficeId().getId() != null
											&& courseAssignMaster.getDeptId() == null
											&& courseAssignMaster.getDivisionId() == null) {

										courseListTemp = courseAssignMasterRepository
												.findAllByOfficeIdAndOfficeIdLanguageAndIsComplianceForCourseFetch(
														loggedInUser.getOfficeId().getId(), language, true);
									}
								}
							}

						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}
						if (loggedInUser.getDeptId() != null) {
							courseAssignList = courseAssignMasterRepository
									.findAllByDeptId(loggedInUser.getDeptId().getId());
							if (courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									//
									if (courseAssignMaster.getCompanyId().getId() != null
											&& courseAssignMaster.getOfficeId().getId() != null
											&& courseAssignMaster.getDeptId().getId() != null
											&& courseAssignMaster.getDivisionId() == null) {
										System.out.println("3--");
										courseListTemp = courseAssignMasterRepository
												.findAllByDeptIdAndDeptIdLanguageAndIsComplianceForCourseFetch(
														loggedInUser.getDeptId().getId(), language, true);
									}
								}
							}

						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}
						if (loggedInUser.getDivisionId() != null) {
							courseAssignList = courseAssignMasterRepository
									.findAllByDivisionId(loggedInUser.getDivisionId().getId());
							if (courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if (courseAssignMaster.getCompanyId().getId() != null
											&& courseAssignMaster.getOfficeId().getId() != null
											&& courseAssignMaster.getDeptId().getId() != null
											&& courseAssignMaster.getDivisionId().getId() != null) {

										courseListTemp = courseAssignMasterRepository
												.findAllByDivisionIdAndDivisionIdLanguageAndIsComplianceForCourseFetch(
														loggedInUser.getDivisionId().getId(), language, true);
									}
								}
							}

						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					List<Course> deduped1 = courseList.stream().distinct().collect(Collectors.toList());
					mav.addObject("Compliance", deduped1);

					List<CourseModule> totalIncompleteModulesCompliance = new ArrayList<CourseModule>();
					List<CourseModule> totalCompleteModulesCompliance = new ArrayList<CourseModule>();
					Integer totalIncompleteModulesComplianceCount = 0;
					Integer totalCompleteModulesComplianceCount = 0;
					List<CourseModule> totalPassCompliance = new ArrayList<CourseModule>();
					List<CourseModule> totalFailCompliance = new ArrayList<CourseModule>();
					Integer totalFailComplianceCount = 0;
					Integer totalPassComplianceCount = 0;
					List<CourseModule> courseModulePendingCompliance = new ArrayList<CourseModule>();

					for (Course course : deduped1) {
						System.out.println("courses---non compliance::" + course.getName());
						List<CourseModule> courseModules = this.courseModuleRepository
								.findSubjectBycourseId(course.getId());
						totalCourseModeulesAssignedToUser = totalCourseModeulesAssignedToUser + this.courseModuleRepository.findCountBycourseId(course.getId());
		               
						for (CourseModule courseModule : courseModules) {
							CourseAttempt courseAttempt = courseAttemptRepository
									.findByUserIdCourseModuleId(loggedInUser.getUserId(), courseModule.getId());
							if (courseAttempt != null) {
								if (courseAttempt.getIsCourseCompleted()) {
									totalCompleteModulesCompliance.add(courseAttempt.getCourseModuleId());
									totalCompleteModulesComplianceCount++;
								} else {
									totalIncompleteModulesCompliance.add(courseAttempt.getCourseModuleId());
									totalIncompleteModulesComplianceCount++;
								}

								if (courseAttempt.getIsAssessmentPass() != null) {
									if (courseAttempt.getIsAssessmentPass()) {
										totalPassCompliance.add(courseAttempt.getCourseModuleId());
										totalPassComplianceCount++;
									} else {
										totalFailCompliance.add(courseAttempt.getCourseModuleId());
										totalFailComplianceCount++;
									}
								}

							} else {
								courseModulePendingCompliance.add(courseModule);
							}

						}

					}
                  
					userDashboardLogs.setCourseModulePendingCompliance(courseModulePendingCompliance);
					userDashboardLogs.setTotalCourseModulePendingCompliance(courseModulePendingCompliance.size());

					userDashboardLogs.setTotalCompleteModulesCompliance(totalCompleteModulesCompliance);
					userDashboardLogs.setTotalCompleteModulesComplianceCount(totalCompleteModulesCompliance.size());
					userDashboardLogs.setTotalIncompleteModulesCompliance(totalIncompleteModulesCompliance);
					userDashboardLogs.setTotalIncompleteModulesComplianceCount(totalIncompleteModulesCompliance.size());

					userDashboardLogs.setTotalPassCompliance(totalPassCompliance);
					userDashboardLogs.setTotalPassComplianceCount(totalPassCompliance.size());
					userDashboardLogs.setTotalFailCompliance(totalFailCompliance);
					userDashboardLogs.setTotalFailComplianceCount(totalFailCompliance.size());

					// for pending (complete date in 7 days)
					isCompliance = 2;
					courseList = new ArrayList<Course>();
					Calendar cal = Calendar.getInstance();
					Date today = cal.getTime();
					cal.add(Calendar.DAY_OF_MONTH, 7);
					Date expiryDate = cal.getTime();
					java.sql.Date nextDt = new java.sql.Date(expiryDate.getTime());
					java.sql.Date currentDt = new java.sql.Date(new Date().getTime());

					try {
						if (loggedInUser.getCompanyId() != null) {
							courseAssignList = courseAssignMasterRepository
									.findAllByCompanyId(loggedInUser.getCompanyId().getId());
							for (CourseAssignMaster courseAssignMaster : courseAssignList) {
								if (courseAssignMaster.getCompanyId().getId() != null
										&& courseAssignMaster.getOfficeId() == null
										&& courseAssignMaster.getDeptId() == null
										&& courseAssignMaster.getDivisionId() == null) {

									courseListTemp = courseAssignMasterRepository
											.findAllByCompanyIdAndCompanyIdLanguageAndComplianceDatewithin7daysForCourseFetch(
													loggedInUser.getCompanyId().getId(), language, currentDt, nextDt);
								}
							}
						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}

						if (loggedInUser.getOfficeId() != null) {
							courseAssignList = courseAssignMasterRepository
									.findAllByOfficeId(loggedInUser.getOfficeId().getId());
							if (courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if (courseAssignMaster.getCompanyId().getId() != null
											&& courseAssignMaster.getOfficeId().getId() != null
											&& courseAssignMaster.getDeptId() == null
											&& courseAssignMaster.getDivisionId() == null) {

										courseListTemp = courseAssignMasterRepository
												.findAllByOfficeIdAndOfficeIdLanguageAndComplianceDatewithin7daysForCourseFetch(
														loggedInUser.getOfficeId().getId(), language, currentDt,
														nextDt);
									}
								}
							}

						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}

						if (loggedInUser.getDeptId() != null) {
							courseAssignList = courseAssignMasterRepository
									.findAllByDeptId(loggedInUser.getDeptId().getId());
							if (courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {

									if (courseAssignMaster.getCompanyId().getId() != null
											&& courseAssignMaster.getOfficeId().getId() != null
											&& courseAssignMaster.getDeptId().getId() != null
											&& courseAssignMaster.getDivisionId() == null) {

										courseListTemp = courseAssignMasterRepository
												.findAllByDeptIdAndDeptIdLanguageAndComplianceDatewithin7daysForCourseFetch(
														loggedInUser.getDeptId().getId(), language, currentDt, nextDt);
									}
								}
							}

						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}
						if (loggedInUser.getDivisionId() != null) {
							courseAssignList = courseAssignMasterRepository
									.findAllByDivisionId(loggedInUser.getDivisionId().getId());
							if (courseAssignList != null && !courseAssignList.isEmpty()) {
								for (CourseAssignMaster courseAssignMaster : courseAssignList) {
									if (courseAssignMaster.getCompanyId().getId() != null
											&& courseAssignMaster.getOfficeId().getId() != null
											&& courseAssignMaster.getDeptId().getId() != null
											&& courseAssignMaster.getDivisionId().getId() != null) {

										courseListTemp = courseAssignMasterRepository
												.findAllByDivisionIdAndDivisionIdLanguageAndComplianceDatewithin7daysForCourseFetch(
														loggedInUser.getDivisionId().getId(), language, currentDt,
														nextDt);
									}
								}
							}

						}
						if (!courseListTemp.isEmpty()) {
							courseList.addAll(courseListTemp);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					List<Course> deduped2 = courseList.stream().distinct().collect(Collectors.toList());

					mav.addObject("Others", deduped2);

					List<CourseModule> totalIncompleteModulesOthers = new ArrayList<CourseModule>();
					List<CourseModule> totalCompleteModulesOthers = new ArrayList<CourseModule>();
					Integer totalIncompleteModulesOthersCount = 0;
					Integer totalCompleteModulesOthersCount = 0;
					List<CourseModule> totalPassOthersCompliance = new ArrayList<CourseModule>();
					List<CourseModule> totalFailOthersCompliance = new ArrayList<CourseModule>();
					Integer totalFailOthersComplianceCount = 0;
					Integer totalPassOthersComplianceCount = 0;
					for (Course course : deduped1) {

						List<CourseModule> courseModules = this.courseModuleRepository
								.findSubjectBycourseId(course.getId());
					
						for (CourseModule courseModule : courseModules) {

							CourseAttempt courseAttempt = courseAttemptRepository
									.findByUserIdCourseModuleId(loggedInUser.getUserId(), courseModule.getId());
							if (courseAttempt != null) {
								if (courseAttempt.getIsCourseCompleted()) {
									totalCompleteModulesOthers.add(courseAttempt.getCourseModuleId());
									totalCompleteModulesOthersCount++;
								} else {
									totalIncompleteModulesOthers.add(courseAttempt.getCourseModuleId());
									totalIncompleteModulesOthersCount++;
								}

								if (courseAttempt.getIsAssessmentPass() != null) {
									if (courseAttempt.getIsAssessmentPass()) {
										totalPassOthersCompliance.add(courseAttempt.getCourseModuleId());
										totalPassOthersComplianceCount++;
									} else {
										totalFailOthersCompliance.add(courseAttempt.getCourseModuleId());
										totalFailOthersComplianceCount++;
									}
								}

							}
						}

					}

					userDashboardLogs.setTotalCompleteModulesOthers(totalCompleteModulesOthers);
					userDashboardLogs.setTotalCompleteModulesOthersCount(totalCompleteModulesOthers.size());
					userDashboardLogs.setTotalIncompleteModulesOthers(totalIncompleteModulesOthers);
					userDashboardLogs.setTotalFailOthersComplianceCount(totalIncompleteModulesOthers.size());

					userDashboardLogs.setTotalPassOthersCompliance(totalPassOthersCompliance);
					userDashboardLogs.setTotalPassOthersComplianceCount(totalPassOthersCompliance.size());
					userDashboardLogs.setTotalFailOthersCompliance(totalFailOthersCompliance);
					userDashboardLogs.setTotalFailOthersComplianceCount(totalFailOthersCompliance.size());

					userDashboardLogs.setTotalCourseModeulesInDB(totalCourseModeulesInDB);
					userDashboardLogs.setTotalCourseModeulesAssignedToUser(totalCourseModeulesAssignedToUser);

					mav.addObject("userDashboardLogs", userDashboardLogs);

					mav.addObject("courseLoad", true);

					// Modules wise Pai Chart
					Map<Object, Object> map = null;
					List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
					List<Map<Object, Object>> dataPointsPai = new ArrayList<Map<Object, Object>>();

					map = new HashMap<Object, Object>();
					map.put("label", "Total Assigned Modules");
					map.put("y", totalCourseModeulesAssignedToUser);
					dataPointsPai.add(map);

					map = new HashMap<Object, Object>();
					map.put("label", "Total Modules Passed");
					map.put("y", totalPassCompliance.size() + totalPassNonCompliance.size());
					dataPointsPai.add(map);

					map = new HashMap<Object, Object>();
					map.put("label", "Total Modules Failed");
					map.put("y", totalFailCompliance.size() + totalFailNonCompliance.size());
					dataPointsPai.add(map);

					map = new HashMap<Object, Object>();
					map.put("label", "Total Pending Modules");
					map.put("y", courseModulePendingCompliance.size() + courseModulePendingNonCompliance.size());
					dataPointsPai.add(map);

					list.add(dataPointsPai);
					mav.addObject("dataPointsList", list);
					// --------------------------------------------------------------------------------------------

					return mav;

				} else {

					mav.addObject("error", "Not valid user! Try again.");

					return mav;
				}
			} else {

				mav.addObject("error", "Not valid user! Try again.");

				return mav;
			}
		} catch (Exception e) {
			mav.setViewName("login");
			mav.addObject("login", new Login());
			mav.addObject("error", "Please Login First.");
			return mav;
		}

	}

	@RequestMapping(value = "/user_modules", method = RequestMethod.GET)
	public ModelAndView user_modules(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "courseId", required = false) Integer courseId) {
		UserNew loggedInUser = (UserNew) request.getSession().getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("user_modules");
		List<CourseModule> courseModules = null;

		try {
			if (loggedInUser != null) {

				courseModules = this.courseModuleRepository.findSubjectBycourseId(courseId);

				mav.addObject("moduleList", courseModules);
				if (!courseModules.isEmpty()) {
					mav.addObject("course", courseModules.get(0).getCourseId());
				}
				mav.addObject("moduleLoad", true);
			}

		} catch (Exception e) {
			mav.setViewName("login");
			mav.addObject("login", new Login());
			mav.addObject("error", "Please Login First.");
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/user_modules_detail", method = RequestMethod.GET)
	public ModelAndView user_modules_detail(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "courseModuleId", required = false) Integer courseModuleId) {
		UserNew loggedInUser = (UserNew) request.getSession().getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("user_modules_detail");
		CourseModule courseModule = null;

		try {
			if (loggedInUser != null) {

				courseModule = this.courseModuleRepository.findByIntegerId(courseModuleId);

				mav.addObject("courseModule", courseModule);

				CourseContent courseContent = courseContentRepository.findByCourseModuleIdId(courseModuleId);

				mav.addObject("courseContent", courseContent);

				/*
				 * mav.addObject("courseAssign",courseAssignMasterRepository.findByCourseIdId(
				 * courseModule.getCourseId().getId()));
				 */

				CourseAttempt courseAttempt = new CourseAttempt();

				// check isCourseFinished for reading
				courseAttempt = courseAttemptRepository.findByUserIdCourseModuleId(loggedInUser.getUserId(),
						courseModuleId);
				if (courseAttempt != null) {
					if (courseAttempt.getIsCourseCompleted()) {
						mav.addObject("isCourseFinished", courseAttempt.getIsCourseCompleted());
					} else {
						mav.addObject("isCourseFinished", false);
					}
				}
				Calendar cal = Calendar.getInstance();
				String currentTime = cal.getTime().getHours() + ":" + cal.getTime().getMinutes() + ":"
						+ cal.getTime().getSeconds();
				mav.addObject("startTime", currentTime);

			}

		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("login");
			mav.addObject("login", new Login());
			mav.addObject("error", "Please Login First.");
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/user_modules_slides", method = RequestMethod.POST)
	public ModelAndView user_modules_slides(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "courseModuleId", required = false) Integer courseModuleId) {
		UserNew loggedInUser = (UserNew) request.getSession().getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("user_modules_slides");
		List<CourseUpload> courseUploads = null;

		try {
			if (loggedInUser != null) {

				courseUploads = this.courseUploadRepository.findByCourseModuleId1(courseModuleId);

				mav.addObject("courseUploads", courseUploads);

				JSONArray courseUploadJson = new JSONArray();
				JSONObject tmp;
				int i = 0;
				for (CourseUpload object : courseUploads) {
					i++;
					tmp = new JSONObject();
					tmp.put("id", object.getId());
					tmp.put("srno", i);
					tmp.put("isImgOrVideo", object.getIsImgOrVideo());
					tmp.put("slidePath", object.getSlidePath());
					tmp.put("audioPath", object.getAudioPath());
					courseUploadJson.put(tmp);

				}

				mav.addObject("courseUploadJson", courseUploadJson);
				mav.addObject("courseModuleId", courseModuleId);
				mav.addObject("slide", true);

				Calendar cal = Calendar.getInstance();
				String startTime = cal.getTime().getHours() + ":" + cal.getTime().getMinutes() + ":"
						+ cal.getTime().getSeconds();
				mav.addObject("startTime", startTime);

			}

		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("login");
			mav.addObject("login", new Login());
			mav.addObject("error", "Please Login First.");
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/user_modules_slides_reading_finish", method = RequestMethod.POST)
	public ModelAndView user_modules_slides_reading_finish(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "courseModuleId", required = false) Integer courseModuleId,
			@RequestParam(value = "isCourseFinished", required = false) Boolean isCourseFinished,
			@RequestParam(value = "startTime", required = false) String startTime) {
		UserNew loggedInUser = (UserNew) request.getSession().getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("user_dashboard");
		try {
			Calendar cal = Calendar.getInstance();
			String endTime = cal.getTime().getHours() + ":" + cal.getTime().getMinutes() + ":"
					+ cal.getTime().getSeconds();

			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			Date date1 = format.parse(startTime);
			Date date2 = format.parse(endTime);
			long difference = date2.getTime() - date1.getTime();

			long diffSeconds = difference / 1000 % 60;
			long diffMinutes = difference / (60 * 1000) % 60;
			long diffHours = difference / (60 * 60 * 1000) % 24;

			CourseViewLogDetails courseViewLogDetails = new CourseViewLogDetails();
			CourseModule courseModule = new CourseModule();
			courseModule.setId(courseModuleId);
			courseViewLogDetails.setCourseModuleId(courseModule);
			courseViewLogDetails.setStartTime(startTime);
			courseViewLogDetails.setEndTime(endTime);
			courseViewLogDetails.setIsCourseFinished(isCourseFinished);
			courseViewLogDetails.setTotalSpendTime(diffHours + ":" + diffMinutes + ":" + diffSeconds);
			courseViewLogDetails.setUserId(loggedInUser);
			courseViewLogDetails.setViewDate(new Date());

			courseViewLogDetailsRepository.save(courseViewLogDetails);

			// to update course attempt
			CourseAttempt courseAttempt = new CourseAttempt();
			courseAttempt = courseAttemptRepository.findByUserIdCourseModuleId(
					courseViewLogDetails.getUserId().getUserId(), courseViewLogDetails.getCourseModuleId().getId());
			if (courseAttempt != null) {
				if (!courseAttempt.getIsCourseCompleted()) {
					courseAttempt.setIsCourseCompleted(courseViewLogDetails.getIsCourseFinished());
					courseAttempt.setUpdatedDate(new Date());
				}

			} else if (courseAttempt == null) {
				courseAttempt = new CourseAttempt(courseViewLogDetails.getUserId(),
						courseViewLogDetails.getCourseModuleId(), courseViewLogDetails.getIsCourseFinished(), null,
						null, new Date(), new Date());
			}
			courseAttemptRepository.save(courseAttempt);
			// to update course attempt end

		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("login");
			mav.addObject("login", new Login());
			mav.addObject("error", "Please Login First.");
			return mav;
		}
		return new ModelAndView("redirect:/user_dashboard");
	}

	@RequestMapping(value = "/user_modules_slides_cancel", method = RequestMethod.GET)
	public String user_modules_slides_cancel(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "courseModuleId", required = false) Integer courseModuleId) {
		UserNew loggedInUser = (UserNew) request.getSession().getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("user_dashboard");

		return "redirect:/user_dashboard";
	}

	@RequestMapping(value = "/user_question_paper", method = RequestMethod.POST)
	public ModelAndView user_question_paper(HttpServletRequest request, HttpSession session,
			@RequestParam(value = "courseModuleId", required = false) Integer courseModuleId,
			@RequestParam(value = "isCourseFinished", required = false) Boolean isCourseFinished,
			@RequestParam(value = "startTime", required = false) String startTime) {
		UserNew loggedInUser = (UserNew) request.getSession().getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("user_question_paper");

		try {
			List<Question> quesList = null;
			if (loggedInUser != null) {

				CourseContent courseContent = courseContentRepository.findByCourseModuleIdId(courseModuleId);

				quesList = this.questionRepository.findQuestionBycourseModuleId(courseModuleId);
				quesList = getRandomQuestions(quesList, courseContent.getTotalAssessmentQuestion());
				QuestionListAccess questionListAccess = new QuestionListAccess();
				questionListAccess.setQuestions(quesList);

				Calendar cal = Calendar.getInstance();
				String currentTime = cal.getTime().getHours() + ":" + cal.getTime().getMinutes() + ":"
						+ cal.getTime().getSeconds();

				questionListAccess.setStartTime(currentTime);

				// questionListAccess.setTotalNoOfQuestion(String.valueOf(courseContent.getTotalAssessmentQuestion()));

				mav.addObject("courseContent", courseContent);
				mav.addObject("questionListAccess", questionListAccess);
				mav.addObject("courseModuleId", courseModuleId);
			}

			// add course view log details
			Calendar cal = Calendar.getInstance();
			String endTime = cal.getTime().getHours() + ":" + cal.getTime().getMinutes() + ":"
					+ cal.getTime().getSeconds();

			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			Date date1 = format.parse(startTime);
			Date date2 = format.parse(endTime);
			long difference = date2.getTime() - date1.getTime();

			long diffSeconds = difference / 1000 % 60;
			long diffMinutes = difference / (60 * 1000) % 60;
			long diffHours = difference / (60 * 60 * 1000) % 24;

			CourseViewLogDetails courseViewLogDetails = new CourseViewLogDetails();
			CourseModule courseModule = new CourseModule();
			courseModule.setId(courseModuleId);
			courseViewLogDetails.setCourseModuleId(courseModule);
			courseViewLogDetails.setStartTime(startTime);
			courseViewLogDetails.setEndTime(endTime);
			courseViewLogDetails.setIsCourseFinished(isCourseFinished);
			courseViewLogDetails.setTotalSpendTime(diffHours + ":" + diffMinutes + ":" + diffSeconds);
			courseViewLogDetails.setUserId(loggedInUser);
			courseViewLogDetails.setViewDate(new Date());

			courseViewLogDetailsRepository.save(courseViewLogDetails);
			// add course view log details end

			// to update course attempt
			CourseAttempt courseAttempt = new CourseAttempt();
			courseAttempt = courseAttemptRepository.findByUserIdCourseModuleId(
					courseViewLogDetails.getUserId().getUserId(), courseViewLogDetails.getCourseModuleId().getId());
			if (courseAttempt != null) {
				if (!courseAttempt.getIsCourseCompleted()) {
					courseAttempt.setIsCourseCompleted(courseViewLogDetails.getIsCourseFinished());
					courseAttempt.setUpdatedDate(new Date());
				}

			} else if (courseAttempt == null) {
				courseAttempt = new CourseAttempt(courseViewLogDetails.getUserId(),
						courseViewLogDetails.getCourseModuleId(), courseViewLogDetails.getIsCourseFinished(), null,
						null, new Date(), new Date());
			}
			courseAttemptRepository.save(courseAttempt);
			// to update course attempt end

		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("login");
			mav.addObject("login", new Login());
			mav.addObject("error", "Please Login First.");
			return mav;
		}
		return mav;
		// reference
		// code::::http://viralpatel.net/blogs/spring-mvc-multi-row-submit-java-list/
	}

	public List<Question> getRandomQuestions(List<Question> questions, int numberOfQuestions) {
		List<Question> randomQuestions = new ArrayList<>();
		List<Question> copy = new ArrayList<>(questions);

		SecureRandom rand = new SecureRandom();
		for (int i = 0; i < Math.min(numberOfQuestions, questions.size()); i++) {
			randomQuestions.add(copy.remove(rand.nextInt(copy.size())));
		}

		return randomQuestions;
	}

	@RequestMapping(value = "/user_question_paper_save", method = RequestMethod.POST)
	public ModelAndView user_question_paper_save(HttpServletRequest request, HttpSession session,
			@ModelAttribute(value = "questionListAccess") QuestionListAccess questionListAccess) {
		UserNew loggedInUser = (UserNew) request.getSession().getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("user_question_paper_review");

		try {
			List<Question> quesListSubmitted = null;
			Integer countCorrect = 0;
			Integer countAttempted = 0;
			List<Question> quesList = null;
			List<Question> quesListFilter = new ArrayList<Question>();
			quesListSubmitted = questionListAccess.getQuestions();
			quesList = this.questionRepository.findQuestionBycourseModuleId(questionListAccess.getCourseModuleId());

			for (Question questionDbFetched : quesList) {
				for (Question questionSubmitted : quesListSubmitted) {
					if (questionSubmitted.getId() == questionDbFetched.getId()) {
						quesListFilter.add(questionDbFetched);
					}
				}

			}
			
			if(quesListSubmitted.size()>0) {
			for (int i = 0; i < quesListSubmitted.size(); i++) {

				if (quesListSubmitted.get(i).getAnswer() != null && !quesListSubmitted.get(i).getAnswer().isEmpty()) {
					countAttempted++;
					if(quesListFilter.size()>0) {
					if (quesListSubmitted.get(i).getAnswer().equals(quesListFilter.get(i).getAnswer())) {
						countCorrect++;
					}
					}
				}

			}
			}

			CourseContent courseContent = courseContentRepository
					.findByCourseModuleIdId(questionListAccess.getCourseModuleId());
			Calendar cal = Calendar.getInstance();
			String currentTime = cal.getTime().getHours() + ":" + cal.getTime().getMinutes() + ":"
					+ cal.getTime().getSeconds();

			SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
			Date date1 = format.parse(questionListAccess.getStartTime());
			Date date2 = format.parse(currentTime);
			long difference = date2.getTime() - date1.getTime();

			long diffSeconds = difference / 1000 % 60;
			long diffMinutes = difference / (60 * 1000) % 60;
			long diffHours = difference / (60 * 60 * 1000) % 24;

			AssessmentLogDetails aslds = new AssessmentLogDetails();
			aslds.setStartTime(questionListAccess.getStartTime());
			aslds.setEndTime(currentTime);
			aslds.setTotalSpendTime(diffHours + ":" + diffMinutes + ":" + diffSeconds);
			aslds.setAssessmentDate(new Date());
			aslds.setTotalNoOfQuestion(String.valueOf(quesListSubmitted.size()));
			aslds.setQuestionAttempted(String.valueOf(countAttempted));
			CourseModule courseModuleId = new CourseModule();
			courseModuleId.setId(questionListAccess.getCourseModuleId());
			aslds.setCourseModuleId(courseModuleId);
			aslds.setQuestionCorrected(String.valueOf(countCorrect));
			if (courseContent.getMinPassNo() <= countCorrect) {
				aslds.setStatus("pass");
			} else {
				aslds.setStatus("fail");
			}
			aslds.setUserId(loggedInUser);

			assessmentLogDetailsRepository.save(aslds);

			mav.addObject("assessmentLogDetails", aslds);
			mav.addObject("quesListDb", quesListFilter);
			mav.addObject("quesListUser", questionListAccess.getQuestions());

			// to update course attempt
			CourseAttempt courseAttempt = new CourseAttempt();
			courseAttempt = courseAttemptRepository.findByUserIdCourseModuleId(aslds.getUserId().getUserId(),
					aslds.getCourseModuleId().getId());
			if (courseAttempt != null) {
				if (courseAttempt.getIsAssessmentPass() == null) {

					courseAttempt.setIsAssessmentPass(aslds.getStatus().equals("pass") ? true : false);
					courseAttempt.setAssessmentMarks(aslds.getQuestionCorrected() + "/" + aslds.getTotalNoOfQuestion());
					courseAttempt.setUpdatedDate(new Date());

				} else if (!courseAttempt.getIsAssessmentPass()) {

					courseAttempt.setIsAssessmentPass(aslds.getStatus().equals("pass") ? true : false);
					courseAttempt.setAssessmentMarks(aslds.getQuestionCorrected() + "/" + aslds.getTotalNoOfQuestion());
					courseAttempt.setUpdatedDate(new Date());

				}

			}
			courseAttemptRepository.save(courseAttempt);
			// to update course attempt end
			mav.addObject("courseModuleId", questionListAccess.getCourseModuleId());
		} catch (Exception e) {
			e.printStackTrace();
			mav.setViewName("login");
			mav.addObject("login", new Login());
			mav.addObject("error", "Please Login First.");
			return mav;
		}
		return mav;
		// reference
		// code::::http://viralpatel.net/blogs/spring-mvc-multi-row-submit-java-list/
	}

	@RequestMapping(value = "/certificate", method = RequestMethod.POST)
	public ModelAndView certificate(HttpServletRequest request, HttpSession session,
			@ModelAttribute(value = "assessmentLogDetails") AssessmentLogDetails assessmentLogDetails) {

		ModelAndView mav = new ModelAndView("certificate");

		return mav;
	}

}
