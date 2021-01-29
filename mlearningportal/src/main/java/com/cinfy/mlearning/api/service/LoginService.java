package com.cinfy.mlearning.api.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.repositories.UserNewRegisterRepository;
import com.cinfy.mlearning.model.request.LoginUser;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.model.response.UserProfileGetResponse;

import com.cinfy.mlearning.utils.InvoiceConstants;
import com.cinfy.mlearning.utils.Messages;
import com.cinfy.mlearning.model.UserImgEdit;
import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.common.UserImgEditPayload;
import com.cinfy.mlearning.model.common.UserNewPayload;
import com.cinfy.mlearning.model.mapper.UserImgEditMapper;
import com.cinfy.mlearning.model.mapper.UserNewMapper;


@Service
public class LoginService {

	public static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	private static final int ERROR_CODE_CLASS = 1000;

	@Autowired
	UserNewRegisterRepository userNewRegisterRepository;	
	
	// reset password

	public GenericResponse userReset(UserNew request){
		GenericResponse response = new GenericResponse();
		String securePassword = null;
		UserNew account = null;
				
	try{
		
		if (request != null) {

			securePassword = Base64.getEncoder().encodeToString(request.getPassword().getBytes(InvoiceConstants.UTF8));

			account = this.userNewRegisterRepository.findByEmailOrPhoneAllIgnoreCase(request.getEmail(),request.getPhone());

			if (account != null) {
				account.setPassword(securePassword);
				account.setIsResetPwd(true);
				
				this.userNewRegisterRepository.save(account);
				response.setStatus(1);
				
		      }
			
			else{
			// failure response
			ErrorMessage errorMessage = new ErrorMessage(503,
					"No mobile/email found for the user, Please try again later.", "");
			response.setStatus(0);
			response.setError(errorMessage);
		}
		}else{
			// failure response
						ErrorMessage errorMessage = new ErrorMessage(503,
								"Unable to Update the User Credentials, Null value Found For user.", "");
						response.setStatus(0);
						response.setError(errorMessage);
		}
		}catch(Exception ex){
			// failure response
						ErrorMessage errorMessage = new ErrorMessage(503,
								"Unable to Update the User Credentials, Please try again later.", "");
						response.setStatus(0);
						response.setError(errorMessage);
		}
		return response;
	}

	public GenericResponse userEditImage(UserImgEditPayload userImgEditPayload) {
		GenericResponse response = new GenericResponse();
		try {	
			
			UserImgEdit userImgEdit = UserImgEditMapper.INSTANCE.userImgEditPayloadToUserImgEditEntity(userImgEditPayload);
		
		
			 this.userNewRegisterRepository.updateUserImage(userImgEdit.getImgPath(),userImgEdit.getUserId());		
				 
				

				response.setStatus(1);
				response.setData(userImgEditPayload);

			
		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, "Unable to process the registration request.", ex.getMessage()));
		}

		return response;

	}

	
	public GenericResponse userRegistration(UserNewPayload registration) {
		GenericResponse response = new GenericResponse();
		try {	
			UserProfileGetResponse dataResponse = new UserProfileGetResponse();
			

			String securePassword = Base64.getEncoder()
					.encodeToString(registration.getPassword().getBytes(InvoiceConstants.UTF8));
		
			
			registration.setPassword(securePassword);
			registration.setAddedDate(new Date());
			
			UserNew user = UserNewMapper.INSTANCE.userNewPayloadToUserNewEntity(registration);
		
		
			user = this.userNewRegisterRepository.save(user);
					
			UserNewPayload userPayload =UserNewMapper.INSTANCE.userNewEntityToUserNewPayload(user);
					dataResponse.setUser(userPayload);
					
				 
				

				response.setStatus(1);
				response.setData(dataResponse);

			
		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, "Unable to process the registration request.", ex.getMessage()));
		}

		return response;

	}

	
	/*public GenericResponse loginCMS(LoginUserCmsPayload loginUserCmsPayload) {
		logger.info("User loging by ID: {}", loginUserCmsPayload.getUserId());
		logger.info("User loging by getPassword: {}", loginUserCmsPayload.getPassword());
		logger.info("User loging by getRole: {}", loginUserCmsPayload.getRole());
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		UserProfileGetResponse dataResponse = null;
		try {
           if(loginUserCmsPayload.getUserId()!=null && loginUserCmsPayload.getPassword()!=null){
			String securePassword = Base64.getEncoder()
					.encodeToString(loginUserCmsPayload.getPassword().getBytes(InvoiceConstants.UTF8));
			logger.info("Errror111");
			
			Admin account = this.adminRepository.findByPasswordAndEmailOrMobileRoleAllIgnoreCase(securePassword,
					loginUserCmsPayload.getUserId(), loginUserCmsPayload.getUserId(),loginUserCmsPayload.getRole());

			if (account != null) {
				
				dataResponse = new UserProfileGetResponse();
				AccountPayload accountPayload = new AccountPayload(account.getId().toString(), account.getEmail(),
						account.getMobile(),account.getName(),account.getRole());
				dataResponse.setAccount(accountPayload);

				if (account.getGenericCityId() > 0) {

					GenericCity genericCity = genericCityRepository.findById(1);
					GenericCityPayload genericCityPayload = new GenericCityPayload(genericCity.getId(),
							genericCity.getName(), genericCity.getShortDescription(), genericCity.getLatitude(),
							genericCity.getLongitude(), genericCity.getState(),genericCity.getLongDescription());
					dataResponse.setGenericCity(genericCityPayload);

				}

				// Get JWT token
				String jwt = JWTUtils.createJWT(account.getId().toString(), account.getEmail(), account.getMobile(),
						1000 * 60 * 10);
				dataResponse.setJwt(jwt);

				response.setData(dataResponse);
				logger.info("AAAAAAA: {}", response);
			} else {
				logger.info("User not found for the userId:" + loginUserCmsPayload.getUserId());
				response.setStatus(0);
				// response.setError(new ErrorMessage(ERROR_CODE_CLASS,
				// Messages.get("User not found for the userId :"+
				// user.getUserId())));
				response.setError(new ErrorMessage(ERROR_CODE_CLASS, "User not found for the userId :" + loginUserCmsPayload.getUserId()));
			}
           } else {
//				logger.info("User not found for the userId:" + user.getUserId());
				response.setStatus(0);
				// response.setError(new ErrorMessage(ERROR_CODE_CLASS,
				// Messages.get("User not found for the userId :"+
				// user.getUserId())));
				response.setError(new ErrorMessage(ERROR_CODE_CLASS, "Null Values"));
			}

			// if ( account != null) {
			// dataResponse = new UserProfileGetResponse();
			// AccountPayload accountPayload = new
			// AccountPayload(account.getId().toString(), account.getEmail(),
			// account.getMobile(), account.getRole());
			//
			// dataResponse.setAccount(accountPayload);
			// if (account.getUserProfile() != null) {
			// UserProfile profile = account.getUserProfile();
			// UserPayload userPayload = new
			// UserPayload(profile.getId().toString(), profile.getFirstName(),
			// profile.getLastName(),
			// profile.getPan(), profile.getGender());
			// dataResponse.setProfile(userPayload);
			// }
			// if (account.getCompanyId() != null) {
			// Company company = account.getCompanyId();
			// CompanyPayload companyPayload = new
			// CompanyPayload(company.getId(), company.getCompanyCode(),
			// company.getName(),
			// company.getType(),company.getAddressId());
			// dataResponse.setCompany(companyPayload);
			// }
			// // Get JWT token
			// String jwt = JWTUtils.createJWT(account.getId().toString(),
			// account.getEmail(), account.getMobile(), 1000*60*10);
			// dataResponse.setJwt(jwt);
			//
			// response.setData(dataResponse);
			// } else {

		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("user.login.failed"), ex.getMessage()));
		}
		logger.info("BBBBBB: {}", response);
		return response;

	}*/
	
	
	/*
	 * Login check
	 */
	public GenericResponse login(LoginUser user) {
		logger.info("User loging by ID: {}", user.getUserId());
		logger.info("User loging by getPassword: {}", user.getPassword());
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		UserProfileGetResponse dataResponse = null;
		try {
           if(user.getUserId()!=null && user.getPassword()!=null){
			String securePassword = Base64.getEncoder()
					.encodeToString(user.getPassword().getBytes(InvoiceConstants.UTF8));
			logger.info("Errror111");
			UserNew account = this.userNewRegisterRepository.findByPasswordAndEmailOrPhoneAndRoleAllIgnoreCase(securePassword,
					user.getUserId(), user.getMobile(), user.getRole());

			if (account != null) {
				logger.info("Errror222");
				dataResponse = new UserProfileGetResponse();
				UserNewPayload userPayload =UserNewMapper.INSTANCE
						.userNewEntityToUserNewPayload(account);
				dataResponse.setUser(userPayload);

				/*if (account.getGenericCityId() != null) {

					GenericCity genericCity = account.getGenericCityId();
					GenericCityPayload genericCityPayload = new GenericCityPayload(genericCity.getId(),
							genericCity.getName(), genericCity.getShortDescription(), genericCity.getLatitude(),
							genericCity.getLongitude(), genericCity.getState(),genericCity.getLongDescription());
					dataResponse.setGenericCity(genericCityPayload);

				}*/
				
				// Get JWT token
				//String jwt = JWTUtils.createJWT(account.getUserId().toString(), account.getEmail(), account.getPhone(),
				//		1000 * 60 * 10);
				//dataResponse.setJwt(jwt);

				response.setData(dataResponse);
				logger.info("AAAAAAA: {}", response);
			} else {
				logger.info("User not found for the userId:" + user.getUserId());
				response.setStatus(0);
				// response.setError(new ErrorMessage(ERROR_CODE_CLASS,
				// Messages.get("User not found for the userId :"+
				// user.getUserId())));
				response.setError(new ErrorMessage(ERROR_CODE_CLASS, "User not found for the userId :" + user.getUserId()));
			}
           } else {
//				logger.info("User not found for the userId:" + user.getUserId());
				response.setStatus(0);
				// response.setError(new ErrorMessage(ERROR_CODE_CLASS,
				// Messages.get("User not found for the userId :"+
				// user.getUserId())));
				response.setError(new ErrorMessage(ERROR_CODE_CLASS, "Null Values"));
			}

			// if ( account != null) {
			// dataResponse = new UserProfileGetResponse();
			// AccountPayload accountPayload = new
			// AccountPayload(account.getId().toString(), account.getEmail(),
			// account.getMobile(), account.getRole());
			//
			// dataResponse.setAccount(accountPayload);
			// if (account.getUserProfile() != null) {
			// UserProfile profile = account.getUserProfile();
			// UserPayload userPayload = new
			// UserPayload(profile.getId().toString(), profile.getFirstName(),
			// profile.getLastName(),
			// profile.getPan(), profile.getGender());
			// dataResponse.setProfile(userPayload);
			// }
			// if (account.getCompanyId() != null) {
			// Company company = account.getCompanyId();
			// CompanyPayload companyPayload = new
			// CompanyPayload(company.getId(), company.getCompanyCode(),
			// company.getName(),
			// company.getType(),company.getAddressId());
			// dataResponse.setCompany(companyPayload);
			// }
			// // Get JWT token
			// String jwt = JWTUtils.createJWT(account.getId().toString(),
			// account.getEmail(), account.getMobile(), 1000*60*10);
			// dataResponse.setJwt(jwt);
			//
			// response.setData(dataResponse);
			// } else {

		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("user.login.failed"), ex.getMessage()));
		}
		logger.info("BBBBBB: {}", response);
		return response;

	}

	/*public void readJwtToken(String jwt) {
		JWTUtils.parseJWT(jwt);
	}*/
	
	/*public GenericResponse socialLogin(LoginUser user) {
		logger.info("User loging by ID: {}", user.getUserId());		
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		UserProfileGetResponse dataResponse = null;
		try {
           if(user.getUserId() != null && !"".equals(user.getUserId()) && user.getStatus() != null){			
			if(user.getStatus() != 1){
			User account = userRegisterRepository.findByEmailAndStatus(user.getUserId(), user.getStatus());
			if (account == null) {
				account = new UserDetail();
				account.setEmail(user.getUserId());
				account.setName(user.getName());
				account.setStatus(user.getStatus());				
				account.setGenericCityId(genericCityRepository.findById(1));
				account.setDeviceId(user.getDeviceId());
				account.setDeviceType(user.getDeviceType());
				account.setFcmId(user.getFcmId());
				userRegisterRepository.save(account);
			}
				
				dataResponse = new UserProfileGetResponse();
				AccountPayload accountPayload = new AccountPayload(account.getId().toString(), account.getEmail(),
						account.getMobile(),account.getName(),user.getLanguageId());
				dataResponse.setAccount(accountPayload);

				if (account.getGenericCityId() != null) {

					GenericCity genericCity = account.getGenericCityId();
					GenericCityPayload genericCityPayload = new GenericCityPayload(genericCity.getId(),
							genericCity.getName(), genericCity.getShortDescription(), genericCity.getLatitude(),
							genericCity.getLongitude(), genericCity.getState(),genericCity.getLongDescription());
					dataResponse.setGenericCity(genericCityPayload);

				}

				// Get JWT token
				String jwt = JWTUtils.createJWT(account.getId().toString(), account.getEmail(), account.getMobile(),
						1000 * 60 * 10);
				dataResponse.setJwt(jwt);

				response.setData(dataResponse);
				logger.info("AAAAAAA: {}", response);
			} else {
				logger.info("User not found for the userId:" + user.getUserId());
				response.setStatus(0);
				response.setError(new ErrorMessage(ERROR_CODE_CLASS, "Status 1 is reserved for website!!"));
			}
           } else {				
				response.setStatus(0);				
				response.setError(new ErrorMessage(ERROR_CODE_CLASS, "Null Values"));
			}

			

		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("user.login.failed"), ex.getMessage()));
		}
		logger.info("BBBBBB: {}", response);
		return response;

	}*/
	
	public GenericResponse getUserList(Map<String, Object> params) {		  
		
		  logger.info("Getting the list of user details .... ");		  
		  GenericResponse response = new GenericResponse();		  
		  
		  //UserPayload userPayload = null;
		  List<UserNew> users = null;
		  List<UserNewPayload> result = null;
		  response.setStatus(1);		  
		  try{
			  
			  Integer id =  (Integer) params.get("id");	
			  String email =  (String) params.get("email");
			  
			  if(id != null){
				  users = userNewRegisterRepository.findByUserId(id);
			  }else if(email != null){
				  users = userNewRegisterRepository.findByEmail(email);
			  }else {
				  users = userNewRegisterRepository.findAllUser();
			  }
			  
			  if (users != null && !users.isEmpty()) {
					result = new ArrayList<>();
					for (UserNew s : users) {
						UserNewPayload payload = UserNewMapper.INSTANCE
								.userNewEntityToUserNewPayload(s);
						result.add(payload);
					}
					response.setData(result);
				}else {				
			
					response.setError(new ErrorMessage(ERROR_CODE_CLASS,Messages.get("sales.save.update"), "Not Found"));
				}
				
		  }catch (Exception ex) {
			   logger.error(ERROR_CODE_CLASS+"-Exception occurred due to the:"+ex.fillInStackTrace());
			   response.setStatus(0);
			   response.setError(new ErrorMessage(ERROR_CODE_CLASS,Messages.get("sales.save.update"),ex.getMessage()));
			  }
		return response;
	}

}
