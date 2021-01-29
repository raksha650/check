package com.cinfy.mlearning.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.cinfy.mlearning.api.service.CourseContentService;
import com.cinfy.mlearning.model.common.CourseContentPayload;
import com.cinfy.mlearning.model.request.CourseContentDeleteRequest;
import com.cinfy.mlearning.model.request.GenericRequest;
import com.cinfy.mlearning.model.response.CourseContentDeleteResponse;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableAutoConfiguration
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/courseContent")
public class CourseContentAPIController {
	public static final Logger logger = LoggerFactory.getLogger(CourseContentAPIController.class);

	@Autowired
	private CourseContentService courseContentService;
	
	/*
	 * This is for status of the application.
	 */
	@RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
	public ResponseEntity<String> createUserGet(HttpServletRequest request) {
		logger.info("Requested operation : {}", request.getRequestURI());
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}



	@RequestMapping(path = "/search", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GenericResponse> getCustomers(HttpServletRequest request,
			@RequestParam(value = "courseModuleId", required = false) Integer courseModuleId,
			@RequestParam(value = "language", required = false) Integer language,
			@RequestParam(value = "id", required = false) Integer id,
			UriComponentsBuilder ucBuilder) {
		logger.info("Into Course Content Details fetch details:subjectId-{}",	courseModuleId);
		GenericResponse res = null;
	
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("courseModuleId", courseModuleId);
			params.put("language", language);
			params.put("id", id);
			res = this.courseContentService.getCourseContentList(params);

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			if (res == null) {
				res = new GenericResponse();
				res.setStatus(0);
			}
			res.setError(new ErrorMessage(0001, "Unable to process the request.", ex.getMessage()));
		}
		return new ResponseEntity<GenericResponse>(res, CommonUtils.getHeaders(), HttpStatus.OK);
	}

	


}
