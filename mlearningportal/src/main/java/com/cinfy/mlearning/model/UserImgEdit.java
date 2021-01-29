package com.cinfy.mlearning.model;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author j.singh
 */


public class UserImgEdit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer userId;
	
	private String imgPath;
	
	private String imgName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	
}
