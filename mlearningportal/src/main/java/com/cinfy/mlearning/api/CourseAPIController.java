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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cinfy.mlearning.api.service.CourseService;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.CommonUtils;

@EnableAutoConfiguration
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/course")
public class CourseAPIController {
	public static final Logger logger = LoggerFactory.getLogger(CourseAPIController.class);

	@Autowired
	private CourseService courseService;
	
	/*
	 * This is for status of the application.
	 */
	@RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
	public ResponseEntity<String> createUserGet(HttpServletRequest request) {
		logger.info("Requested operation : {}", request.getRequestURI());
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
	

	@RequestMapping(path = "/search", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GenericResponse> getCustomers(
			@RequestParam(value = "isCompliance", required = false) Integer isCompliance,
			@RequestParam(value = "language", required = false) Integer language,
			@RequestParam(value = "userId", required = false) Integer userId,
			UriComponentsBuilder ucBuilder) {
		logger.info("Into Course fetch details:userId-{},isCompliance-{},language-{}",userId, isCompliance,language);
		GenericResponse res = null;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("isCompliance", isCompliance);
			params.put("language", language);
			params.put("userId", userId);
			res = this.courseService.getCourseList(params);

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
