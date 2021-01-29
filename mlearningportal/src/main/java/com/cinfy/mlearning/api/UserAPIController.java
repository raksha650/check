package com.cinfy.mlearning.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cinfy.mlearning.api.service.LoginService;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.common.UserImgEditPayload;
import com.cinfy.mlearning.model.common.UserNewPayload;
import com.cinfy.mlearning.model.mapper.UserNewMapper;
import com.cinfy.mlearning.model.repositories.UserNewRegisterRepository;
import com.cinfy.mlearning.model.request.GenericRequest;
import com.cinfy.mlearning.model.request.LoginUser;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.CommonUtils;
import com.cinfy.mlearning.utils.FTPUtil;


@EnableAutoConfiguration
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserAPIController {

	public static final Logger logger = LoggerFactory.getLogger(UserAPIController.class);

	@Autowired
	private LoginService loginService;
	
	/*@Autowired
	private GenericCityRepository genericCityRepository;*/
	// new
	@Autowired
	private UserNewRegisterRepository registerRepository;

	/*
	 * @Autowired private CompanyRegistration companyRegistrationService;
	 */
	
	@Value("${UPLOAD_PATH_USER_IMG}")
	private String UPLOAD_PATH_USER_IMG;
	
	@RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
	public ResponseEntity<String> createUserGet(HttpServletRequest request) {
		logger.info("Requested operation : {}", request.getRequestURI());
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<GenericResponse> login(@RequestBody GenericRequest request, UriComponentsBuilder ucBuilder) {
		logger.info("#################Into Login Controller################");
		
		ObjectMapper mapper = new ObjectMapper();
		GenericResponse res = null;
		LoginUser user;
		try {
			user = mapper.readValue(mapper.writeValueAsString(request.getData()), LoginUser.class);
			res = loginService.login(user);
			logger.info("#################Into Login Controller################");
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			if (res == null) {
				res = new GenericResponse();
				res.setStatus(0);
			}
			res.setError(new ErrorMessage(0001, "Unable to process the request.", ex.getMessage()));
		}
		return new ResponseEntity<GenericResponse>(res, CommonUtils.getHeaders(), HttpStatus.OK);
	}

	
	@RequestMapping(value = "/userImgEdit", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<GenericResponse> userImgEdit(@RequestBody GenericRequest request, UriComponentsBuilder ucBuilder) {
        logger.info("## Employee Attendance  ...");
        GenericResponse res = new GenericResponse();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        res.setStatus(1);
		try {
			 // TODO Should be debug mode
			logger.info("Requested operation : {}", mapper.writeValueAsString(request.getData()));
			UserImgEditPayload  userImgEditPayload = mapper.readValue(mapper.writeValueAsString(request.getData()),UserImgEditPayload.class);
			
        
			if(userImgEditPayload.getUserId()!=null) {
			
			if(userImgEditPayload.getImgPath()!=null && !userImgEditPayload.getImgPath().isEmpty() ) {
				System.out.println("profileImage ");
				String profileImage= FTPUtil.uploadFileByte(userImgEditPayload.getImgPath(), userImgEditPayload.getImgName(), UPLOAD_PATH_USER_IMG);
		
				userImgEditPayload.setImgPath(profileImage);
				}
			
			
			res = this.loginService.userEditImage(userImgEditPayload);
		
			}else {				
				res.setStatus(0);
				res.setError("Employee Id Missing ! Please Send Employee Id With Request");			
			}
        } catch (Exception ex) {
        	ex.printStackTrace();
			logger.error("Exception occurred123:"+ex);
			res.setStatus(0);
			res.setError(new ErrorMessage(0001, " Unable to process the request123.",ex.getMessage()));
		} 
        return new ResponseEntity<GenericResponse>(res,CommonUtils.getHeaders(),HttpStatus.OK);
    }
	
	// user registration
//	@RequestMapping(value = "/create",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<GenericResponse> create(@RequestBody GenericRequest request, UriComponentsBuilder ucBuilder) {
		//System.out.println("jitendra1234====");
		//logger.info("Requested operation : {}", request.getCmd());

		// COMMAND action = COMMAND.getCommand(request.getCmd());

		ObjectMapper mapper = new ObjectMapper();
		GenericResponse res = null;
		
		try {
			logger.info("Requested operation : {}", mapper.writeValueAsString(request.getData()));
			
				logger.debug("Request data object:" + request.getData());
			
			UserNewPayload register = mapper.readValue(mapper.writeValueAsString(request.getData()),
					UserNewPayload.class);
			
			if(!register.getProfileImagePath().isEmpty() && register.getProfileImagePath()!=null ) {
			String profileImage= FTPUtil.uploadFileByte(register.getProfileImagePath(), register.getProfileImageName(), UPLOAD_PATH_USER_IMG);
           register.setProfileImagePath(profileImage);
			}
			// register.setGenericCityId(genericCityId);
		//	System.out.println("register.getEmail()======"+register.getEmail());
			res = loginService.userRegistration(register);

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

	@RequestMapping(value = "/reset", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<GenericResponse> changePassword(@RequestBody GenericRequest request,
			UriComponentsBuilder ucBuilder) {
		logger.info("#################Into Change Password Method ################");
		ObjectMapper mapper = new ObjectMapper();
		GenericResponse res = null;

		try {
			// userResetRequest =
			// mapper.readValue(mapper.writeValueAsString(request.getData()),
			// UserVerificationRequest.class);
			UserNewPayload resetPassword = mapper.readValue(mapper.writeValueAsString(request.getData()), UserNewPayload.class);
			logger.info("****** user detail ****{}", resetPassword);
			UserNew user = UserNewMapper.INSTANCE.userNewPayloadToUserNewEntity(resetPassword);
			res = loginService.userReset(user);

		} catch (IOException ex) {
			// TODO Auto-generated catch block
			if (res == null) {
				res = new GenericResponse();
				res.setStatus(0);
			}
			res.setError(new ErrorMessage(0001, "Unable to process the request.", ex.getMessage()));
		}
		return new ResponseEntity<GenericResponse>(res, CommonUtils.getHeaders(), HttpStatus.OK);
	}


	@RequestMapping(value = "/updateUser",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<GenericResponse> updateUser(@RequestBody GenericRequest request, UriComponentsBuilder ucBuilder) {
		

		ObjectMapper mapper = new ObjectMapper();
		GenericResponse res = null;
		
		try {
			logger.info("Requested operation : {}", mapper.writeValueAsString(request.getData()));
			
				logger.debug("Request data object:" + request.getData());
			
			UserNewPayload register = mapper.readValue(mapper.writeValueAsString(request.getData()),
					UserNewPayload.class);
			if(register.getUserId()!=null) {
			if(!register.getProfileImagePath().isEmpty() && register.getProfileImagePath()!=null ) {
			String profileImage= FTPUtil.uploadFileByte(register.getProfileImagePath(), register.getProfileImageName(), UPLOAD_PATH_USER_IMG);
           register.setProfileImagePath(profileImage);
			}
			// register.setGenericCityId(genericCityId);
		//	System.out.println("register.getEmail()======"+register.getEmail());
			res = loginService.userRegistration(register);
			}
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
	
	@RequestMapping(path = "/search",method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<GenericResponse> getEntityMaster( 
			 @RequestParam(value="id",required=false) Integer id,
			 @RequestParam(value="email",required=false) String email,			
			 UriComponentsBuilder ucBuilder) {
		
		logger.info("Question search");
		GenericResponse res = null;
		
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			params.put("email", email);			
			res = loginService.getUserList(params);
             
		} catch (Exception ex) {
			if (res == null) {
				res = new GenericResponse();
				res.setStatus(0);
			}
			res.setError(new ErrorMessage(0001, "Unable to process the request.", ex.getMessage()));
		}
		return new ResponseEntity<GenericResponse>(res, CommonUtils.getHeaders(), HttpStatus.OK);
	}

}
