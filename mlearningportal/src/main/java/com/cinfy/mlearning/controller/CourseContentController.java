package com.cinfy.mlearning.controller;

import java.io.IOException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cinfy.mlearning.commonmodel.CourseCategoryCommon;
import com.cinfy.mlearning.commonmodel.CourseCommon;
import com.cinfy.mlearning.commonmodel.CourseModuleCommon;
import com.cinfy.mlearning.model.Course;
import com.cinfy.mlearning.model.CourseCategory;
import com.cinfy.mlearning.model.CourseContent;
import com.cinfy.mlearning.model.CourseUpload;
import com.cinfy.mlearning.model.CourseUploadDTO;
import com.cinfy.mlearning.model.CourseUploadForm;
import com.cinfy.mlearning.model.Login;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.CourseModule;
import com.cinfy.mlearning.model.repositories.CourseAudioUploadRepository;
import com.cinfy.mlearning.model.repositories.CourseCategoryRepository;
import com.cinfy.mlearning.model.repositories.CourseContentRepository;
import com.cinfy.mlearning.model.repositories.CourseUploadRepository;
import com.cinfy.mlearning.model.repositories.CourseModuleRepository;
import com.cinfy.mlearning.model.repositories.CourseRepository;
import com.cinfy.mlearning.utils.FTPUtil;

@Controller
public class CourseContentController {
	@Autowired
	CourseContentRepository courseContentRepository;

	@Autowired
	CourseModuleRepository courseModuleRepository;

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	CourseUploadRepository courseUploadRepository;

	@Autowired
	CourseCategoryRepository courseCategoryRepository;

	@Autowired
	CourseAudioUploadRepository courseAudioUploadRepository;

	@Value("${UPLOAD_PATH_CONTENT_SLIDES}")
	private String UPLOAD_PATH_CONTENT_SLIDES;

	@Value("${UPLOAD_PATH_CONTENT_AUDIO}")
	private String UPLOAD_PATH_CONTENT_AUDIO;

	private static final Logger logger = LoggerFactory.getLogger(CourseContentController.class);

	// to create new Designation page load method
	@RequestMapping(value = "/courseContent", method = RequestMethod.GET)
	public ModelAndView get_Page(HttpServletRequest request, HttpSession session, HttpServletResponse response) {

		Integer language = (Integer) request.getSession().getAttribute("language");

		CourseContent courseContent = new CourseContent();

		String courseContentId = request.getParameter("id");
		try {
			if (courseContentId != null) {
				courseContent = courseContentRepository.findById(Integer.parseInt(courseContentId));
			}

		} catch (Exception e) {
			e.printStackTrace();
			courseContent = new CourseContent();
		}

		ModelAndView view = new ModelAndView("courseContent");

		view.addObject("courseContent", courseContent);
		view.addObject("courseContentList", courseContentRepository.findAll());

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
		/*
		 * List<Course> courseLi = new ArrayList<Course>(); List<CourseCommon>
		 * courseCommonLi = new ArrayList<CourseCommon>(); try { courseLi =
		 * courseRepository.findAllOrderByCommonId(); for (int i = 0; i <
		 * courseLi.size(); i = i + 2) {
		 * 
		 * CourseCommon ccm = new CourseCommon();
		 * 
		 * if (courseLi.get(i).getLanguage() == 1) {
		 * ccm.setModelEnglish(courseLi.get(i)); ccm.setModelHindi(courseLi.get(i + 1));
		 * 
		 * } else { ccm.setModelHindi(courseLi.get(i));
		 * ccm.setModelEnglish(courseLi.get(i + 1)); }
		 * 
		 * courseCommonLi.add(ccm);
		 * 
		 * }
		 * 
		 * } catch (Exception e) { e.printStackTrace(); } view.addObject("courseList",
		 * courseCommonLi);
		 */
		/*
		 * List<CourseModule> courseModule = new ArrayList<CourseModule>();
		 * 
		 * try { courseModule = courseModuleRepository.findAllOrderByCommonId();
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 * view.addObject("courseModuleList", courseModule);
		 */

		return view;
	}

	// to create new Designation page load method
	@RequestMapping(value = "/courseContentUpload", method = RequestMethod.GET)
	public ModelAndView get_Page_courseContentUpload(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		String error = request.getParameter("error");
		String success = request.getParameter("success");
		Integer language = (Integer) request.getSession().getAttribute("language");

		ModelAndView view = new ModelAndView();

		Integer id = Integer.parseInt(request.getParameter("id") != null ? request.getParameter("id") : "0");
		if (id > 0) {
			CourseUpload courseUpload = courseUploadRepository.findById(id);

			view.addObject("courseUpload", courseUpload);

		} else {
			view.addObject("courseUpload", new CourseUpload());
		}

		view.addObject("categoryList", courseCategoryRepository.findAll());

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

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("courseModuleList", courseModule);
		view.addObject("error", error);
		view.addObject("success", success);

		return view;
	}

	@RequestMapping(value = "/showNoOfSlide", method = RequestMethod.GET)
	public ModelAndView get_Page_NoOfSlide(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {

		CourseModule courseModule = new CourseModule();

		ModelAndView view = new ModelAndView("courseContentUpload");
		String courseModuleId = request.getParameter("courseModuleId");

		courseModule.setId(Integer.parseInt(courseModuleId));
		CourseContent courseContent = courseContentRepository.findByCourseModuleId(courseModule);

		view.addObject("courseContent", courseContent);
		view.addObject("courseModuleId", courseModuleId);

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
		List<CourseModule> courseModulecombow = new ArrayList<CourseModule>();

		try {
			courseModulecombow = courseModuleRepository.findAllOrderByCommonId();

		} catch (Exception e) {
			e.printStackTrace();
		}

		view.addObject("courseModuleList", courseModulecombow);

		List<CourseUpload> courseUploads = courseUploadRepository.findByCourseModuleId(courseModule);
		CourseUploadForm courseUploadForm = new CourseUploadForm();
		List<CourseUploadDTO> courseUploadDTOs = new ArrayList<CourseUploadDTO>();

		if (courseUploads != null && !courseUploads.isEmpty()) {
			for (CourseUpload courseUpload : courseUploads) {

				CourseUploadDTO courseUploadDTO = new CourseUploadDTO(String.valueOf(courseUpload.getId()),
						courseUpload.getSlidePath(), courseUpload.getAudioPath(), courseUpload.getIsImgOrVideo(),
						courseUpload.getDeleted(), courseUpload.getSlidePathMultipart(),
						courseUpload.getAudioPathMultipart(), String.valueOf(courseUpload.getCourseModuleId().getId()),
						String.valueOf(courseUpload.getCreatedBy()), String.valueOf(courseUpload.getUpdatedBy()));
				courseUploadDTOs.add(courseUploadDTO);
			}
			courseUploadForm.setCourseUploads(courseUploadDTOs);

		} else {
			if (courseContent.getTotalSlide() > 0) {
				for (int i = 0; i < courseContent.getTotalSlide(); i++) {
					CourseUploadDTO courseUploadDto = new CourseUploadDTO();
					courseUploadDto.setCourseModuleId(courseModuleId);
					courseUploadDTOs.add(courseUploadDto);
				}

				courseUploadForm.setCourseUploads(courseUploadDTOs);
			} else {
				return new ModelAndView("redirect:/courseContentUpload?error=No any 'Course Content' Found");
			}
		}

		view.addObject("courseUploadForm", courseUploadForm);

		return view;
	}

	/*
	 * @RequestMapping(value = "/saveOrUpdateCourseContentUpload", method =
	 * RequestMethod.POST) public ModelAndView
	 * saveOrUpdateCourseContentUpload(HttpServletRequest request,
	 * 
	 * @ModelAttribute("courseUploadForm") CourseUploadForm courseUploadForm) throws
	 * IOException { User loginUser = (User)
	 * request.getSession().getAttribute("loginUser");
	 * 
	 * List<CourseUploadDTO> courseUploads = courseUploadForm.getCourseUploads();
	 * 
	 * // ---
	 * 
	 * if (null != courseUploads && courseUploads.size() > 0) {
	 * 
	 * for (CourseUploadDTO courseUploadDTO : courseUploads) {
	 * 
	 * CourseUpload coUpload = new CourseUpload();
	 * coUpload.setId(courseUploadDTO.getId().isEmpty() ? null :
	 * Integer.parseInt(courseUploadDTO.getId()));
	 * coUpload.setAudioPath(courseUploadDTO.getAudioPath().isEmpty() ? null :
	 * courseUploadDTO.getAudioPath()); coUpload.setCourseModuleId(new
	 * CourseModule());
	 * coUpload.getCourseModuleId().setId(Integer.parseInt(courseUploadDTO.
	 * getCourseModuleId()));
	 * coUpload.setCreatedBy(courseUploadDTO.getCreatedBy().isEmpty() ? null :
	 * Integer.parseInt(courseUploadDTO.getCreatedBy()));
	 * coUpload.setDeleted(courseUploadDTO.getDeleted());
	 * coUpload.setIsImgOrVideo(courseUploadDTO.getIsImgOrVideo());
	 * coUpload.setSlidePath(courseUploadDTO.getSlidePath().isEmpty() ? null :
	 * courseUploadDTO.getSlidePath()); coUpload.setSlidePathMultipart(
	 * courseUploadDTO.getSlidePathMultipart().getOriginalFilename().isEmpty() ?
	 * null : courseUploadDTO.getSlidePathMultipart());
	 * coUpload.setAudioPathMultipart(
	 * courseUploadDTO.getAudioPathMultipart().getOriginalFilename().isEmpty() ?
	 * null : courseUploadDTO.getAudioPathMultipart());
	 * 
	 * coUpload.setUpdatedDate(new Date());
	 * coUpload.setUpdatedBy(loginUser.getUserId());
	 * 
	 * if (coUpload.getId() == null) { coUpload.setCreatedDate(new Date());
	 * coUpload.setCreatedBy(loginUser.getUserId()); } if
	 * (coUpload.getSlidePathMultipart() != null &&
	 * !coUpload.getSlidePathMultipart().getOriginalFilename().equals("")) { String
	 * fileNewPath = FTPUtil.uplo(coUpload.getSlidePathMultipart(),
	 * UPLOAD_PATH_CONTENT_SLIDES); coUpload.setSlidePath(fileNewPath); } if
	 * (coUpload.getAudioPathMultipart() != null &&
	 * !coUpload.getAudioPathMultipart().getOriginalFilename().equals("")) { String
	 * fileNewPath = FTPUtil.uplo(coUpload.getAudioPathMultipart(),
	 * UPLOAD_PATH_CONTENT_AUDIO); coUpload.setAudioPath(fileNewPath); }
	 * System.out.println("courseUpload_+_" + coUpload.toString());
	 * courseUploadRepository.save(coUpload);
	 * 
	 * } }
	 * 
	 * String success = "successfully updated"; return new
	 * ModelAndView("redirect:/courseContent?success=" + success);
	 * 
	 * }
	 */

	@RequestMapping(value = "/saveOrUpdateCourseContentUpload", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateCourseContentUpload(HttpServletRequest request,
			@ModelAttribute("courseUpload") CourseUpload courseUpload) throws IOException {

		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		String success = "";
		String error="";
		try {
              Boolean isAllContentUploded=false;
			if (loginUser != null) {
				
				if(courseUpload.getId()==null) {
					Integer courseContentTotalSlids = courseContentRepository.findByCourseModuleIdId(courseUpload.getCourseModuleId().getId()).getTotalSlide();
					Integer CourseUploadedTotalSlids= courseUploadRepository.findByCourseModuleId1(courseUpload.getCourseModuleId().getId()).size();
					if(CourseUploadedTotalSlids >= courseContentTotalSlids) {
						isAllContentUploded=true;	
					}
					
				}
             if(!isAllContentUploded) {
				if (courseUpload.getSlidePathMultipart() != null
						&& !courseUpload.getSlidePathMultipart().getOriginalFilename().equals("")) {

					String uploadSlideFilePath = fileUploadInDirectory(courseUpload.getSlidePathMultipart(),
							UPLOAD_PATH_CONTENT_SLIDES);

					courseUpload.setSlidePath(uploadSlideFilePath);

					if (courseUpload.getAudioPathMultipart() != null
							&& !courseUpload.getAudioPathMultipart().getOriginalFilename().equals("")) {

						String uploadAudioFilePath = fileUploadInDirectory(courseUpload.getAudioPathMultipart(),
								UPLOAD_PATH_CONTENT_AUDIO);
						courseUpload.setAudioPath(uploadAudioFilePath);
					}

					if (courseUpload.getId() != null) {

						courseUpload.setCreatedDate(new Date());
						courseUpload.setCreatedBy(loginUser.getUserId());
					}
					courseUpload.setUpdatedDate(new Date());
					courseUpload.setUpdatedBy(loginUser.getUserId());

					courseUploadRepository.save(courseUpload);
					success = "successfully updated";
				} else {
					error = " No Slides Found";
					return new ModelAndView("redirect:/courseContentUpload?error=" + error);
				}
			}else {
				error = " Total Number Of Slids Already Uploaded";
				return new ModelAndView("redirect:/courseContentUpload?error=" + error);
			}
			}else{
				ModelAndView mav = new ModelAndView("login");
				mav.addObject("login", new Login());
				mav.addObject("error", "Do Login First");
				return mav;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/courseContentUpload?success=" + success);

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

	@RequestMapping(value = "/showCourseContentUploadForm", method = RequestMethod.GET)
	public ModelAndView get_Page_showCourseContentUploadForm(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {

		Integer language = (Integer) request.getSession().getAttribute("language");

		ModelAndView view = new ModelAndView("showCourseContentUpload");

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
		List<CourseModule> courseModulecombow = new ArrayList<CourseModule>();
		List<CourseModuleCommon> courseModuleCommonLi = new ArrayList<CourseModuleCommon>();
		try {
			courseModulecombow = courseModuleRepository.findAllOrderByCommonId();

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("courseModuleList", courseModulecombow);

		return view;
	}

	@RequestMapping(value = "/showCourseContentUpload", method = RequestMethod.GET)
	public ModelAndView get_Page_showCourseContentUpload(HttpServletRequest request, HttpSession session,
			HttpServletResponse response) {
		CourseModule courseModule = new CourseModule();
		String courseModuleId = request.getParameter("courseModuleId");
		courseModule.setId(Integer.parseInt(courseModuleId));
		// ModelAndView view = new ModelAndView("showCourseContentUpload");
		System.out.println("courseModuleId----" + courseModuleId);
		CourseContent courseContent = courseContentRepository.findByCourseModuleId(courseModule);

		Integer language = (Integer) request.getSession().getAttribute("language");
		CourseUpload courseUpload = new CourseUpload();
		ModelAndView view = new ModelAndView("showCourseContentUpload");

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
		List<CourseModule> courseModulecombow = new ArrayList<CourseModule>();

		try {
			courseModulecombow = courseModuleRepository.findAllOrderByCommonId();

		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("courseModuleList", courseModulecombow);

		if (courseContent != null) {
			view.addObject("subjectId", courseContent.getCourseModuleId());
			view.addObject("courseUploadList",
					courseUploadRepository.findByCourseModuleId(courseContent.getCourseModuleId()));
		}
		view.addObject("courseUpload", courseUpload);
		view.addObject("categoryList", courseCategoryRepository.findAll());
		view.addObject("subjectMasterList", courseModuleRepository.findAll());
		return view;
	}

	@RequestMapping(value = "/saveOrUpdateCourseContent", method = RequestMethod.POST)
	public ModelAndView uploadingPost(HttpServletRequest request,
			@ModelAttribute("courseContent") CourseContent courseContent) throws IOException {
		UserNew loginUser = (UserNew) request.getSession().getAttribute("loginUser");
		ModelAndView view = new ModelAndView("courseContent");

		String success = "";

		courseContent.setUpdatedDate(new Date());
		courseContent.setUpdatedBy(loginUser.getUserId());

		if (courseContent.getId() != null) {
			courseContent.setCreatedDate(courseContentRepository.findById(courseContent.getId()).getCreatedDate());
			courseContentRepository.save(courseContent);

			success = "Course Content data has been updated successfully";
		} else {
			courseContent.setCreatedDate(new Date());
			courseContent.setCreatedBy(loginUser.getUserId());
			courseContentRepository.save(courseContent);

			success = "Course Content data has been created successfully";
		}

		try {
			view.addObject("courseContentList", courseContentRepository.findAll());
			view.addObject("subjectMasterList", courseModuleRepository.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.addObject("courseContent", new CourseContent());

		return new ModelAndView("redirect:/courseContent?success=" + success);
	}

	@RequestMapping(value = "/deleteCourseContent", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, @RequestParam("id") Integer id, RedirectAttributes ra)
			throws Exception {

		ModelAndView view = new ModelAndView("courseContent");

		Integer language = (Integer) request.getSession().getAttribute("language");

		String success = "";
		if (id != null) {

			courseContentRepository.courseContentDelete(id);
			// view.addObject("success", "Course Content has been deleted successfully");
			success = "Course Content has been deleted successfully ";
		} else {
			
			return new ModelAndView("redirect:/courseContent?error=Please Try Again");
		}

		return new ModelAndView("redirect:/courseContent?success=" + success);
	}
	
	@RequestMapping(value = "/deleteCourseContentUploads", method = RequestMethod.GET)
	public ModelAndView deleteCourseContentUploads(HttpServletRequest request, @RequestParam("id") Integer id, RedirectAttributes ra)
			throws Exception {

		ModelAndView view = new ModelAndView("courseContent");

		Integer language = (Integer) request.getSession().getAttribute("language");

		String success = "";
		if (id != null) {

			courseUploadRepository.courseUploadDelete(id);
			// view.addObject("success", "Course Content has been deleted successfully");
			success = "Course Upload has been deleted successfully ";
		} else {
		
			return new ModelAndView("redirect:/courseContentUpload?error=Please Try Again");
		}

		return new ModelAndView("redirect:/courseContentUpload?success=" + success);
	}

}
