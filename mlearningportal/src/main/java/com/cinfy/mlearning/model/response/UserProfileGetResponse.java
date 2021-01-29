package com.cinfy.mlearning.model.response;

import com.cinfy.mlearning.model.common.UserNewPayload;

public class UserProfileGetResponse {

	private UserNewPayload user;

	public UserNewPayload getUser() {
		return user;
	}

	public void setUser(UserNewPayload user) {
		this.user = user;
	}

}
