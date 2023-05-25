package com.forj.model.dto;

public class PlannerDto {

	private int planId;
	private String userId;
	private String planStartdate;
	private String planEnddate;

	public PlannerDto() {
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlanStartdate() {
		return planStartdate;
	}

	public void setPlanStartdate(String planStartdate) {
		this.planStartdate = planStartdate;
	}

	public String getPlanEnddate() {
		return planEnddate;
	}

	public void setPlanEnddate(String planEnddate) {
		this.planEnddate = planEnddate;
	}

}
