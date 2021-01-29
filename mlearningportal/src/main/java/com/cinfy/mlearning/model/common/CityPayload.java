package com.cinfy.mlearning.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityPayload {
	private Integer id;
    private String name;
    private int stateId;
    //private Collection<User> userCollection;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	/*public Collection<User> getUserCollection() {
		return userCollection;
	}
	public void setUserCollection(Collection<User> userCollection) {
		this.userCollection = userCollection;
	}*/
    
    

}
