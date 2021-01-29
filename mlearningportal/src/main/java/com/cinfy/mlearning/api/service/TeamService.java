package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.UserNew;
import com.cinfy.mlearning.model.common.UserNewPayload;
import com.cinfy.mlearning.model.mapper.UserNewMapper;
import com.cinfy.mlearning.model.repositories.CourseContentRepository;
import com.cinfy.mlearning.model.repositories.TeamRepository;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TeamService {
	public static final Logger logger = LoggerFactory.getLogger(TeamService.class);

	private static final int ERROR_CODE_CLASS = 1600;

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	CourseContentRepository courseContentRepository;


	public GenericResponse getList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of team details .... ");
		List<UserNewPayload> result = null;
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			
			Integer userId =  (Integer) params.get("userId");
			
			Integer courseModuleId =  (Integer) params.get("courseModuleId");
			
			// Response convert to countries into List
			List<UserNew> userList = null;
			if (userId != null) {
				UserNew userNew = this.teamRepository.findById(userId);
				if(userNew.getIsManager() == 1) {
					userList =  this.teamRepository.findByManagerId(userId);
					for (UserNew u : userList) {
						System.out.println("userid--"+u.getEmail());
					}
				}
				
			}

			if (userList != null && !userList.isEmpty()) {
				result = new ArrayList<>();
				for (UserNew s : userList) {
					UserNewPayload userNewPayload = UserNewMapper.INSTANCE
							.userNewEntityToUserNewPayload(s);
					result.add(userNewPayload);
				}
			}
			response.setData(result);
		} catch (Exception ex) {
			logger.error(ERROR_CODE_CLASS + "-Exception occurred due to the:" + ex.fillInStackTrace());
			response.setStatus(0);
			response.setError(new ErrorMessage(ERROR_CODE_CLASS, Messages.get("sales.save.update"), ex.getMessage()));
		}
		return response;
	}
	

}
