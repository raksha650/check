package com.cinfy.mlearning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinfy.mlearning.model.DeptMaster;
import com.cinfy.mlearning.model.common.DeptMasterPayload;
import com.cinfy.mlearning.model.mapper.DeptMasterMapper;
import com.cinfy.mlearning.model.repositories.DeptMasterRepository;
import com.cinfy.mlearning.model.response.ErrorMessage;
import com.cinfy.mlearning.model.response.GenericResponse;
import com.cinfy.mlearning.utils.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DeptMasterService {
	public static final Logger logger = LoggerFactory.getLogger(DeptMasterService.class);

	private static final int ERROR_CODE_CLASS = 1600;

	private DeptMasterRepository deptRepository;

	@Autowired
	public void setDeptRepository(DeptMasterRepository deptRepository) {
		this.deptRepository = deptRepository;
	}


	public GenericResponse getDeptList(Map<String, Object> params) {
		// TODO Need to implement Pagination
		logger.info("Getting the list of details .... ");
		List<DeptMasterPayload> result = null;
		GenericResponse response = new GenericResponse();
		response.setStatus(1);
		try {
			String deptName = params.get("deptName") != null ? params.get("deptName").toString() : "";
			
			Integer deptId =  (Integer) params.get("id");
			Integer language =  (Integer) params.get("language");
			
			// Response convert to countries into List
			List<DeptMaster> deptList = null;
			if (deptName != null && !deptName.isEmpty()) {
				deptList =  this.deptRepository.findByDeptNameAllIgnoreCase(deptName);	
			}else if(deptId != null){				
				deptList =  this.deptRepository.findByIdList(deptId);				
			}else if(language != null){				
				deptList =  this.deptRepository.findByLanguage(language);				
			} 

			if (deptList != null && !deptList.isEmpty()) {
				result = new ArrayList<>();
				for (DeptMaster s : deptList) {
					DeptMasterPayload deptPayload = DeptMasterMapper.INSTANCE
							.deptMasterEntityToDeptPayload(s);
					result.add(deptPayload);
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
