package com.cinfy.mlearning.model.common;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDashboardLogsPayload {

	
	List<CourseModulePayload> courseModulePendingNonCompliance=new ArrayList<CourseModulePayload>();
	Integer totalCourseModulePayloadPendingNonCompliance=0;
	
	List<CourseModulePayload> courseModulePendingCompliance=new ArrayList<CourseModulePayload>();
	Integer totalCourseModulePayloadPendingCompliance=0;
	
	List<CourseModulePayload> totalCompleteModulesNonCompliance=new ArrayList<CourseModulePayload>();
	Integer totalCompleteModulesNonComplianceCount=0;
	
	List<CourseModulePayload> totalIncompleteModulesNonCompliance=new ArrayList<CourseModulePayload>(); 
	Integer totalIncompleteModulesNonComplianceCount=0;	
	
	
	List<CourseModulePayload> totalPassNonCompliance=new ArrayList<CourseModulePayload>();
	Integer totalPassNonComplianceCount=0;
	
	List<CourseModulePayload> totalFailNonCompliance=new ArrayList<CourseModulePayload>();
	Integer totalFailNonComplianceCount=0;
	
	
	List<CourseModulePayload> totalCompleteModulesCompliance=new ArrayList<CourseModulePayload>();
	Integer totalCompleteModulesComplianceCount=0;
	
	List<CourseModulePayload> totalIncompleteModulesCompliance=new ArrayList<CourseModulePayload>();
	Integer totalIncompleteModulesComplianceCount=0;
	
	List<CourseModulePayload> totalPassCompliance=new ArrayList<CourseModulePayload>();
	Integer totalPassComplianceCount=0;
	
	List<CourseModulePayload> totalFailCompliance=new ArrayList<CourseModulePayload>();
	Integer totalFailComplianceCount=0;	
	
	List<CourseModulePayload> totalCompleteModulesOthers=new ArrayList<CourseModulePayload>();
	Integer totalCompleteModulesOthersCount=0;
	
	List<CourseModulePayload> totalIncompleteModulesOthers=new ArrayList<CourseModulePayload>();
	Integer totalIncompleteModulesOthersCount=0;
	
	List<CourseModulePayload> totalPassOthersCompliance=new ArrayList<CourseModulePayload>();
	Integer totalPassOthersComplianceCount=0;
	
	List<CourseModulePayload> totalFailOthersCompliance=new ArrayList<CourseModulePayload>();
	Integer totalFailOthersComplianceCount=0;
	public List<CourseModulePayload> getCourseModulePendingNonCompliance() {
		return courseModulePendingNonCompliance;
	}
	public void setCourseModulePendingNonCompliance(List<CourseModulePayload> courseModulePendingNonCompliance) {
		this.courseModulePendingNonCompliance = courseModulePendingNonCompliance;
	}
	public Integer getTotalCourseModulePayloadPendingNonCompliance() {
		return totalCourseModulePayloadPendingNonCompliance;
	}
	public void setTotalCourseModulePayloadPendingNonCompliance(Integer totalCourseModulePayloadPendingNonCompliance) {
		this.totalCourseModulePayloadPendingNonCompliance = totalCourseModulePayloadPendingNonCompliance;
	}
	public List<CourseModulePayload> getCourseModulePendingCompliance() {
		return courseModulePendingCompliance;
	}
	public void setCourseModulePendingCompliance(List<CourseModulePayload> courseModulePendingCompliance) {
		this.courseModulePendingCompliance = courseModulePendingCompliance;
	}
	public Integer getTotalCourseModulePayloadPendingCompliance() {
		return totalCourseModulePayloadPendingCompliance;
	}
	public void setTotalCourseModulePayloadPendingCompliance(Integer totalCourseModulePayloadPendingCompliance) {
		this.totalCourseModulePayloadPendingCompliance = totalCourseModulePayloadPendingCompliance;
	}
	public List<CourseModulePayload> getTotalCompleteModulesNonCompliance() {
		return totalCompleteModulesNonCompliance;
	}
	public void setTotalCompleteModulesNonCompliance(List<CourseModulePayload> totalCompleteModulesNonCompliance) {
		this.totalCompleteModulesNonCompliance = totalCompleteModulesNonCompliance;
	}
	public Integer getTotalCompleteModulesNonComplianceCount() {
		return totalCompleteModulesNonComplianceCount;
	}
	public void setTotalCompleteModulesNonComplianceCount(Integer totalCompleteModulesNonComplianceCount) {
		this.totalCompleteModulesNonComplianceCount = totalCompleteModulesNonComplianceCount;
	}
	public List<CourseModulePayload> getTotalIncompleteModulesNonCompliance() {
		return totalIncompleteModulesNonCompliance;
	}
	public void setTotalIncompleteModulesNonCompliance(List<CourseModulePayload> totalIncompleteModulesNonCompliance) {
		this.totalIncompleteModulesNonCompliance = totalIncompleteModulesNonCompliance;
	}
	public Integer getTotalIncompleteModulesNonComplianceCount() {
		return totalIncompleteModulesNonComplianceCount;
	}
	public void setTotalIncompleteModulesNonComplianceCount(Integer totalIncompleteModulesNonComplianceCount) {
		this.totalIncompleteModulesNonComplianceCount = totalIncompleteModulesNonComplianceCount;
	}
	public List<CourseModulePayload> getTotalPassNonCompliance() {
		return totalPassNonCompliance;
	}
	public void setTotalPassNonCompliance(List<CourseModulePayload> totalPassNonCompliance) {
		this.totalPassNonCompliance = totalPassNonCompliance;
	}
	public Integer getTotalPassNonComplianceCount() {
		return totalPassNonComplianceCount;
	}
	public void setTotalPassNonComplianceCount(Integer totalPassNonComplianceCount) {
		this.totalPassNonComplianceCount = totalPassNonComplianceCount;
	}
	public List<CourseModulePayload> getTotalFailNonCompliance() {
		return totalFailNonCompliance;
	}
	public void setTotalFailNonCompliance(List<CourseModulePayload> totalFailNonCompliance) {
		this.totalFailNonCompliance = totalFailNonCompliance;
	}
	public Integer getTotalFailNonComplianceCount() {
		return totalFailNonComplianceCount;
	}
	public void setTotalFailNonComplianceCount(Integer totalFailNonComplianceCount) {
		this.totalFailNonComplianceCount = totalFailNonComplianceCount;
	}
	public List<CourseModulePayload> getTotalCompleteModulesCompliance() {
		return totalCompleteModulesCompliance;
	}
	public void setTotalCompleteModulesCompliance(List<CourseModulePayload> totalCompleteModulesCompliance) {
		this.totalCompleteModulesCompliance = totalCompleteModulesCompliance;
	}
	public Integer getTotalCompleteModulesComplianceCount() {
		return totalCompleteModulesComplianceCount;
	}
	public void setTotalCompleteModulesComplianceCount(Integer totalCompleteModulesComplianceCount) {
		this.totalCompleteModulesComplianceCount = totalCompleteModulesComplianceCount;
	}
	public List<CourseModulePayload> getTotalIncompleteModulesCompliance() {
		return totalIncompleteModulesCompliance;
	}
	public void setTotalIncompleteModulesCompliance(List<CourseModulePayload> totalIncompleteModulesCompliance) {
		this.totalIncompleteModulesCompliance = totalIncompleteModulesCompliance;
	}
	public Integer getTotalIncompleteModulesComplianceCount() {
		return totalIncompleteModulesComplianceCount;
	}
	public void setTotalIncompleteModulesComplianceCount(Integer totalIncompleteModulesComplianceCount) {
		this.totalIncompleteModulesComplianceCount = totalIncompleteModulesComplianceCount;
	}
	public List<CourseModulePayload> getTotalPassCompliance() {
		return totalPassCompliance;
	}
	public void setTotalPassCompliance(List<CourseModulePayload> totalPassCompliance) {
		this.totalPassCompliance = totalPassCompliance;
	}
	public Integer getTotalPassComplianceCount() {
		return totalPassComplianceCount;
	}
	public void setTotalPassComplianceCount(Integer totalPassComplianceCount) {
		this.totalPassComplianceCount = totalPassComplianceCount;
	}
	public List<CourseModulePayload> getTotalFailCompliance() {
		return totalFailCompliance;
	}
	public void setTotalFailCompliance(List<CourseModulePayload> totalFailCompliance) {
		this.totalFailCompliance = totalFailCompliance;
	}
	public Integer getTotalFailComplianceCount() {
		return totalFailComplianceCount;
	}
	public void setTotalFailComplianceCount(Integer totalFailComplianceCount) {
		this.totalFailComplianceCount = totalFailComplianceCount;
	}
	public List<CourseModulePayload> getTotalCompleteModulesOthers() {
		return totalCompleteModulesOthers;
	}
	public void setTotalCompleteModulesOthers(List<CourseModulePayload> totalCompleteModulesOthers) {
		this.totalCompleteModulesOthers = totalCompleteModulesOthers;
	}
	public Integer getTotalCompleteModulesOthersCount() {
		return totalCompleteModulesOthersCount;
	}
	public void setTotalCompleteModulesOthersCount(Integer totalCompleteModulesOthersCount) {
		this.totalCompleteModulesOthersCount = totalCompleteModulesOthersCount;
	}
	public List<CourseModulePayload> getTotalIncompleteModulesOthers() {
		return totalIncompleteModulesOthers;
	}
	public void setTotalIncompleteModulesOthers(List<CourseModulePayload> totalIncompleteModulesOthers) {
		this.totalIncompleteModulesOthers = totalIncompleteModulesOthers;
	}
	public Integer getTotalIncompleteModulesOthersCount() {
		return totalIncompleteModulesOthersCount;
	}
	public void setTotalIncompleteModulesOthersCount(Integer totalIncompleteModulesOthersCount) {
		this.totalIncompleteModulesOthersCount = totalIncompleteModulesOthersCount;
	}
	public List<CourseModulePayload> getTotalPassOthersCompliance() {
		return totalPassOthersCompliance;
	}
	public void setTotalPassOthersCompliance(List<CourseModulePayload> totalPassOthersCompliance) {
		this.totalPassOthersCompliance = totalPassOthersCompliance;
	}
	public Integer getTotalPassOthersComplianceCount() {
		return totalPassOthersComplianceCount;
	}
	public void setTotalPassOthersComplianceCount(Integer totalPassOthersComplianceCount) {
		this.totalPassOthersComplianceCount = totalPassOthersComplianceCount;
	}
	public List<CourseModulePayload> getTotalFailOthersCompliance() {
		return totalFailOthersCompliance;
	}
	public void setTotalFailOthersCompliance(List<CourseModulePayload> totalFailOthersCompliance) {
		this.totalFailOthersCompliance = totalFailOthersCompliance;
	}
	public Integer getTotalFailOthersComplianceCount() {
		return totalFailOthersComplianceCount;
	}
	public void setTotalFailOthersComplianceCount(Integer totalFailOthersComplianceCount) {
		this.totalFailOthersComplianceCount = totalFailOthersComplianceCount;
	}

}
