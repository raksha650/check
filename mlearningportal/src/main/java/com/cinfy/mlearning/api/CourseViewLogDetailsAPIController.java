package com.cinfy.mlearning.api;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cinfy.mlearning.api.service.CourseViewLogDetailsService;
import com.cinfy.mlearning.model.common.CourseViewLogDetailsPayload;
import com.cinfy.mlearning.model.request.GenericRequest;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.CommonUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableAutoConfiguration
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/courseViewLogDetails")
public class CourseViewLogDetailsAPIController {
	public static final Logger logger = LoggerFactory.getLogger(CourseViewLogDetailsAPIController.class);

	@Autowired
	private CourseViewLogDetailsService courseViewLogDetailsService;
	
	/*
	 * This is for status of the application.
	 */
	@RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
	public ResponseEntity<String> heartBeat(HttpServletRequest request) {
		logger.info("Requested operation : {}", request.getRequestURI());
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	
	@RequestMapping(path = "/search", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GenericResponse> getDept(
			@RequestParam(value = "userId", required = false) Integer userId,
			@RequestParam(value = "courseModuleId", required = false) Integer courseModuleId,
			@RequestParam(value = "viewDate", required = false) Date viewDate,
			UriComponentsBuilder ucBuilder) {
		logger.info("Into Course View Details Log fetch details:UserId-{},courseModuleId-{},viewDate--{}",userId, courseModuleId,viewDate);
		GenericResponse res = null;
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("userId", userId);
			params.put("courseModuleId", courseModuleId);
			params.put("viewDate", viewDate);
			res = this.courseViewLogDetailsService.getList(params);

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

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<GenericResponse> create(@RequestBody GenericRequest request, UriComponentsBuilder ucBuilder) {
		
		ObjectMapper mapper = new ObjectMapper();
		GenericResponse res = new GenericResponse();
		
		try {
			logger.info("Requested operation : {}", mapper.writeValueAsString(request.getData()));
			
				logger.debug("Request data object:" + request.getData());
			
				CourseViewLogDetailsPayload courseViewLogDetailsPayload = mapper.readValue(mapper.writeValueAsString(request.getData()),
						CourseViewLogDetailsPayload.class);

			res = courseViewLogDetailsService.saveAndUpdate(courseViewLogDetailsPayload);

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("Exception occurred:" + ex);
			if (res == null) {
				res = new GenericResponse();
				res.setStatus(0);
			}
			res.setError(new ErrorMessage(0001, "Unable to process the request.", ex.getMessage()));
		}
		return new ResponseEntity<GenericResponse>(res, CommonUtils.getHeaders(), HttpStatus.OK);
	}


}
