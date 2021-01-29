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

import com.cinfy.mlearning.commonmodel.CourseCategoryCommon;
import com.cinfy.mlearning.commonmodel.CourseCommon;
import com.cinfy.mlearning.commonmodel.QuestionCommon;
import com.cinfy.mlearning.commonmodel.CourseModuleCommon;
import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.CourseCategory;
import com.cinfy.mlearning.model.Question;
import com.cinfy.mlearning.model.CourseModule;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.repositories.CourseCategoryRepository;
import com.cinfy.mlearning.model.repositories.QuestionRepository;
import com.cinfy.mlearning.model.repositories.CourseModuleRepository;
import com.cinfy.mlearning.model.repositories.CourseRepository;

@Controller
public class QuestionController {
	
	@Autowired
	CourseModuleRepository courseModuleRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseCategoryRepository courseCategoryRepository;

	@Autowired
	QuestionRepository questionRepository;

	private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		Integer language = (Integer) request.getSession().getAttribute("language");
		String success = request.getParameter("success");

		List<Question> questionLi = new ArrayList<Question>();

		QuestionCommon question = new QuestionCommon();

		String commonId = request.getParameter("commonId");

		try {
			if (commonId != null) {
				questionLi = questionRepository.findByCommonId(Long.parseLong(commonId));
				if (questionLi.size() > 0) {
					for (Question c : questionLi) {
						if (c.getLanguage() == 1) {
							question.setModelEnglish(c);
						} else {
							question.setModelHindi(c);
						}
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			question = new QuestionCommon();
		}

		ModelAndView view = new ModelAndView("question");

		view.addObject("question", question);
		view.addObject("success", success);

		List<CourseCategory> courseCategoryLi = new ArrayList<CourseCategory>();
		List<CourseCategoryCommon> courseCategoryCommonLi = new ArrayList<CourseCategoryCommon>();
		try {
			courseCategoryLi = courseCategoryRepository.findAllOrderByCommonId();
			for (int i = 0; i < courseCategoryLi.size(); i = i + 2) {

				CourseCategoryCommon ccm = new CourseCategoryCommon();

				if (courseCategoryLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(courseCategoryLi.get(i));
					ccm.setModelHindi(courseCategoryLi.get(i + 1));

				} else {
					ccm.setModelHindi(courseCategoryLi.get(i));
					ccm.setModelEnglish(courseCategoryLi.get(i + 1));
				}

				courseCategoryCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("courseCategoryList", courseCategoryCommonLi);

		List<Course> courseLi = new ArrayList<Course>();
		List<CourseCommon> courseCommonLi = new ArrayList<CourseCommon>();
		try {
			courseLi = courseRepository.findAllOrderByCommonId();
			for (int i = 0; i < courseLi.size(); i = i + 2) {

				CourseCommon ccm = new CourseCommon();

				if (courseLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(courseLi.get(i));
					ccm.setModelHindi(courseLi.get(i + 1));

				} else {
					ccm.setModelHindi(courseLi.get(i));
					ccm.setModelEnglish(courseLi.get(i + 1));
				}

				courseCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("courseList", courseCommonLi);
		List<CourseModule> courseModule = new ArrayList<CourseModule>();
		List<CourseModuleCommon> courseModuleCommonLi = new ArrayList<CourseModuleCommon>();
		try {
			courseModule = courseModuleRepository.findAllOrderByCommonId();
			for (int i = 0; i < courseModule.size(); i = i + 2) {

				CourseModuleCommon ccm = new CourseModuleCommon();

				if (courseModule.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(courseModule.get(i));
					ccm.setModelHindi(courseModule.get(i + 1));

				} else {
					ccm.setModelHindi(courseModule.get(i));
					ccm.setModelEnglish(courseModule.get(i + 1));
				}

				courseModuleCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("courseModuleList", courseModuleCommonLi);

		List<QuestionCommon> questionCommonLi = new ArrayList<QuestionCommon>();
		try {
			System.out.println("");
			questionLi = questionRepository.findAllOrderByCommonId();

			for (int i = 0; i < questionLi.size(); i = i + 2) {

				QuestionCommon ccm = new QuestionCommon();

				if (questionLi.get(i).getLanguage() == 1) {
					ccm.setModelEnglish(questionLi.get(i));
					ccm.setModelHindi(questionLi.get(i + 1));

				} else {
					ccm.setModelHindi(questionLi.get(i));
					ccm.setModelEnglish(questionLi.get(i + 1));
				}

				questionCommonLi.add(ccm);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		view.addObject("questionList", questionCommonLi);

		return view;
	}

	// Save And Update File
	@RequestMapping(value = "/saveOrUpdateQuestion", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateQuestion(HttpServletRequest request,
			@ModelAttribute("question") QuestionCommon questionCommon) {
		logger.info("## questionCommon..");
		Question res = new Question();
		Integer language = (Integer) request.getSession().getAttribute("language");
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String success = "";

		Long newCommonId = System.currentTimeMillis();

		try {
			Question modelHindi = questionCommon.getModelHindi();
			Question modelEnglish = questionCommon.getModelEnglish();

			modelHindi.setAnswer(modelEnglish.getAnswer());
			modelHindi.setLevel(modelEnglish.getLevel());
			modelEnglish.setUpdatedBy(loginUser.getUserId());
			modelHindi.setUpdatedBy(loginUser.getUserId());

			List<CourseModule> courseModule = new ArrayList<CourseModule>();
			if (modelEnglish.getCourseModuleId().getCommonId() != null) {
				courseModule = courseModuleRepository.findByCommonId(modelEnglish.getCourseModuleId().getCommonId());
				if (courseModule.size() > 0) {
					for (CourseModule c : courseModule) {
						if (c.getLanguage() == 1) {

							modelEnglish.setCourseModuleId(c);
						} else {
							modelHindi.setCourseModuleId(c);
						}
					}

				}
			}

			modelEnglish.setUpdatedDate(new Date());
			modelEnglish.setLanguage(1);
			modelEnglish.setDeleted(0);

			modelHindi.setUpdatedDate(new Date());
			modelHindi.setLanguage(2);
			modelHindi.setDeleted(0);

			if (modelEnglish.getId() != null && modelHindi.getId() != null) {

				res = questionRepository.save(modelEnglish);
				res = questionRepository.save(modelHindi);
				success = "Question has been updated successfully";
			} else {
				modelEnglish.setCreatedBy(loginUser.getUserId());
				modelHindi.setCreatedBy(loginUser.getUserId());
				modelEnglish.setCreatedDate(new Date());
				modelEnglish.setCommonId(newCommonId);
				modelHindi.setCreatedDate(new Date());
				modelHindi.setCommonId(newCommonId);

				res = questionRepository.save(modelEnglish);
				res = questionRepository.save(modelHindi);
				success = "Question has been created successfully";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			success = "Please try again !";
		}

		return new ModelAndView("redirect:/question?success=" + success);
	}

	@RequestMapping(value = "/deleteQuestionr", method = RequestMethod.GET)
	public ModelAndView deleteQuestion(HttpServletRequest request, @RequestParam("commonId") Long commonId)
			throws Exception {

		String success = "";

		if (commonId != null) {
			questionRepository.questionDeleteCommon(commonId);
			success = "Question has been deleted successfully";
		}

		return new ModelAndView("redirect:/question?success=" + success);
	}

}
