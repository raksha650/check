package com.cinfy.mlearning.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.cinfy.mlearning.model.UserDashboardLogs;
import com.cinfy.mlearning.model.common.UserDashboardLogsPayload;

@Mapper(uses = {CourseModuleMapper.class})
public interface UserDashboardLogsMapper {
	
	UserDashboardLogsMapper INSTANCE = Mappers.getMapper(UserDashboardLogsMapper.class);

	@Mappings({

			
			

	})

	UserDashboardLogs userDashboardLogsPayloadToUserDashboardLogsEntity(UserDashboardLogsPayload userDashboardLogsPayload);

	@InheritInverseConfiguration(name = "userDashboardLogsPayloadToUserDashboardLogsEntity")
	UserDashboardLogsPayload userDashboardLogsEntityToUserDashboardLogsPayload(UserDashboardLogs userDashboardLogs);

}
