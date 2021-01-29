/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cinfy.mlearning.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author j.pathak
 */


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDashboardLogs  {
	
	 Integer totalCourseModeulesInDB = 0;
	 Integer totalCourseModeulesAssignedToUser=0;
	
	List<CourseModule> courseModulePendingNonCompliance=new ArrayList<CourseModule>();
	Integer totalCourseModulePendingNonCompliance=0;
	
	List<CourseModule> courseModulePendingCompliance=new ArrayList<CourseModule>();
	Integer totalCourseModulePendingCompliance=0;
	
	List<CourseModule> totalCompleteModulesNonCompliance=new ArrayList<CourseModule>();
	Integer totalCompleteModulesNonComplianceCount=0;
	
	List<CourseModule> totalIncompleteModulesNonCompliance=new ArrayList<CourseModule>(); 
	Integer totalIncompleteModulesNonComplianceCount=0;	
	
	
	List<CourseModule> totalPassNonCompliance=new ArrayList<CourseModule>();
	Integer totalPassNonComplianceCount=0;
	
	List<CourseModule> totalFailNonCompliance=new ArrayList<CourseModule>();
	Integer totalFailNonComplianceCount=0;
	
	
	List<CourseModule> totalCompleteModulesCompliance=new ArrayList<CourseModule>();
	Integer totalCompleteModulesComplianceCount=0;
	
	List<CourseModule> totalIncompleteModulesCompliance=new ArrayList<CourseModule>();
	Integer totalIncompleteModulesComplianceCount=0;
	
	List<CourseModule> totalPassCompliance=new ArrayList<CourseModule>();
	Integer totalPassComplianceCount=0;
	
	List<CourseModule> totalFailCompliance=new ArrayList<CourseModule>();
	Integer totalFailComplianceCount=0;	
	
	List<CourseModule> totalCompleteModulesOthers=new ArrayList<CourseModule>();
	Integer totalCompleteModulesOthersCount=0;
	
	List<CourseModule> totalIncompleteModulesOthers=new ArrayList<CourseModule>();
	Integer totalIncompleteModulesOthersCount=0;
	
	List<CourseModule> totalPassOthersCompliance=new ArrayList<CourseModule>();
	Integer totalPassOthersComplianceCount=0;
	
	List<CourseModule> totalFailOthersCompliance=new ArrayList<CourseModule>();
	Integer totalFailOthersComplianceCount=0;
	public List<CourseModule> getCourseModulePendingNonCompliance() {
		return courseModulePendingNonCompliance;
	}
	public void setCourseModulePendingNonCompliance(List<CourseModule> courseModulePendingNonCompliance) {
		this.courseModulePendingNonCompliance = courseModulePendingNonCompliance;
	}
	public Integer getTotalCourseModulePendingNonCompliance() {
		return totalCourseModulePendingNonCompliance;
	}
	public void setTotalCourseModulePendingNonCompliance(Integer totalCourseModulePendingNonCompliance) {
		this.totalCourseModulePendingNonCompliance = totalCourseModulePendingNonCompliance;
	}
	public List<CourseModule> getCourseModulePendingCompliance() {
		return courseModulePendingCompliance;
	}
	public void setCourseModulePendingCompliance(List<CourseModule> courseModulePendingCompliance) {
		this.courseModulePendingCompliance = courseModulePendingCompliance;
	}
	public Integer getTotalCourseModulePendingCompliance() {
		return totalCourseModulePendingCompliance;
	}
	public void setTotalCourseModulePendingCompliance(Integer totalCourseModulePendingCompliance) {
		this.totalCourseModulePendingCompliance = totalCourseModulePendingCompliance;
	}
	public List<CourseModule> getTotalCompleteModulesNonCompliance() {
		return totalCompleteModulesNonCompliance;
	}
	public void setTotalCompleteModulesNonCompliance(List<CourseModule> totalCompleteModulesNonCompliance) {
		this.totalCompleteModulesNonCompliance = totalCompleteModulesNonCompliance;
	}
	public Integer getTotalCompleteModulesNonComplianceCount() {
		return totalCompleteModulesNonComplianceCount;
	}
	public void setTotalCompleteModulesNonComplianceCount(Integer totalCompleteModulesNonComplianceCount) {
		this.totalCompleteModulesNonComplianceCount = totalCompleteModulesNonComplianceCount;
	}
	public List<CourseModule> getTotalIncompleteModulesNonCompliance() {
		return totalIncompleteModulesNonCompliance;
	}
	public void setTotalIncompleteModulesNonCompliance(List<CourseModule> totalIncompleteModulesNonCompliance) {
		this.totalIncompleteModulesNonCompliance = totalIncompleteModulesNonCompliance;
	}
	public Integer getTotalIncompleteModulesNonComplianceCount() {
		return totalIncompleteModulesNonComplianceCount;
	}
	public void setTotalIncompleteModulesNonComplianceCount(Integer totalIncompleteModulesNonComplianceCount) {
		this.totalIncompleteModulesNonComplianceCount = totalIncompleteModulesNonComplianceCount;
	}
	public List<CourseModule> getTotalPassNonCompliance() {
		return totalPassNonCompliance;
	}
	public void setTotalPassNonCompliance(List<CourseModule> totalPassNonCompliance) {
		this.totalPassNonCompliance = totalPassNonCompliance;
	}
	public Integer getTotalPassNonComplianceCount() {
		return totalPassNonComplianceCount;
	}
	public void setTotalPassNonComplianceCount(Integer totalPassNonComplianceCount) {
		this.totalPassNonComplianceCount = totalPassNonComplianceCount;
	}
	public List<CourseModule> getTotalFailNonCompliance() {
		return totalFailNonCompliance;
	}
	public void setTotalFailNonCompliance(List<CourseModule> totalFailNonCompliance) {
		this.totalFailNonCompliance = totalFailNonCompliance;
	}
	public Integer getTotalFailNonComplianceCount() {
		return totalFailNonComplianceCount;
	}
	public void setTotalFailNonComplianceCount(Integer totalFailNonComplianceCount) {
		this.totalFailNonComplianceCount = totalFailNonComplianceCount;
	}
	public List<CourseModule> getTotalCompleteModulesCompliance() {
		return totalCompleteModulesCompliance;
	}
	public void setTotalCompleteModulesCompliance(List<CourseModule> totalCompleteModulesCompliance) {
		this.totalCompleteModulesCompliance = totalCompleteModulesCompliance;
	}
	public Integer getTotalCompleteModulesComplianceCount() {
		return totalCompleteModulesComplianceCount;
	}
	public void setTotalCompleteModulesComplianceCount(Integer totalCompleteModulesComplianceCount) {
		this.totalCompleteModulesComplianceCount = totalCompleteModulesComplianceCount;
	}
	public List<CourseModule> getTotalIncompleteModulesCompliance() {
		return totalIncompleteModulesCompliance;
	}
	public void setTotalIncompleteModulesCompliance(List<CourseModule> totalIncompleteModulesCompliance) {
		this.totalIncompleteModulesCompliance = totalIncompleteModulesCompliance;
	}
	public Integer getTotalIncompleteModulesComplianceCount() {
		return totalIncompleteModulesComplianceCount;
	}
	public void setTotalIncompleteModulesComplianceCount(Integer totalIncompleteModulesComplianceCount) {
		this.totalIncompleteModulesComplianceCount = totalIncompleteModulesComplianceCount;
	}
	public List<CourseModule> getTotalPassCompliance() {
		return totalPassCompliance;
	}
	public void setTotalPassCompliance(List<CourseModule> totalPassCompliance) {
		this.totalPassCompliance = totalPassCompliance;
	}
	public Integer getTotalPassComplianceCount() {
		return totalPassComplianceCount;
	}
	public void setTotalPassComplianceCount(Integer totalPassComplianceCount) {
		this.totalPassComplianceCount = totalPassComplianceCount;
	}
	public List<CourseModule> getTotalFailCompliance() {
		return totalFailCompliance;
	}
	public void setTotalFailCompliance(List<CourseModule> totalFailCompliance) {
		this.totalFailCompliance = totalFailCompliance;
	}
	public Integer getTotalFailComplianceCount() {
		return totalFailComplianceCount;
	}
	public void setTotalFailComplianceCount(Integer totalFailComplianceCount) {
		this.totalFailComplianceCount = totalFailComplianceCount;
	}
	public List<CourseModule> getTotalCompleteModulesOthers() {
		return totalCompleteModulesOthers;
	}
	public void setTotalCompleteModulesOthers(List<CourseModule> totalCompleteModulesOthers) {
		this.totalCompleteModulesOthers = totalCompleteModulesOthers;
	}
	public Integer getTotalCompleteModulesOthersCount() {
		return totalCompleteModulesOthersCount;
	}
	public void setTotalCompleteModulesOthersCount(Integer totalCompleteModulesOthersCount) {
		this.totalCompleteModulesOthersCount = totalCompleteModulesOthersCount;
	}
	public List<CourseModule> getTotalIncompleteModulesOthers() {
		return totalIncompleteModulesOthers;
	}
	public void setTotalIncompleteModulesOthers(List<CourseModule> totalIncompleteModulesOthers) {
		this.totalIncompleteModulesOthers = totalIncompleteModulesOthers;
	}
	public Integer getTotalIncompleteModulesOthersCount() {
		return totalIncompleteModulesOthersCount;
	}
	public void setTotalIncompleteModulesOthersCount(Integer totalIncompleteModulesOthersCount) {
		this.totalIncompleteModulesOthersCount = totalIncompleteModulesOthersCount;
	}
	public List<CourseModule> getTotalPassOthersCompliance() {
		return totalPassOthersCompliance;
	}
	public void setTotalPassOthersCompliance(List<CourseModule> totalPassOthersCompliance) {
		this.totalPassOthersCompliance = totalPassOthersCompliance;
	}
	public Integer getTotalPassOthersComplianceCount() {
		return totalPassOthersComplianceCount;
	}
	public void setTotalPassOthersComplianceCount(Integer totalPassOthersComplianceCount) {
		this.totalPassOthersComplianceCount = totalPassOthersComplianceCount;
	}
	public List<CourseModule> getTotalFailOthersCompliance() {
		return totalFailOthersCompliance;
	}
	public void setTotalFailOthersCompliance(List<CourseModule> totalFailOthersCompliance) {
		this.totalFailOthersCompliance = totalFailOthersCompliance;
	}
	public Integer getTotalFailOthersComplianceCount() {
		return totalFailOthersComplianceCount;
	}
	public void setTotalFailOthersComplianceCount(Integer totalFailOthersComplianceCount) {
		this.totalFailOthersComplianceCount = totalFailOthersComplianceCount;
	}
	public Integer getTotalCourseModeulesInDB() {
		return totalCourseModeulesInDB;
	}
	public void setTotalCourseModeulesInDB(Integer totalCourseModeulesInDB) {
		this.totalCourseModeulesInDB = totalCourseModeulesInDB;
	}
	public Integer getTotalCourseModeulesAssignedToUser() {
		return totalCourseModeulesAssignedToUser;
	}
	public void setTotalCourseModeulesAssignedToUser(Integer totalCourseModeulesAssignedToUser) {
		this.totalCourseModeulesAssignedToUser = totalCourseModeulesAssignedToUser;
	}
	
	

}
