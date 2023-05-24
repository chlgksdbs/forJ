package com.forj.model.dto;

public class PlanDto {
	private int planId;
	private int planContentId;
	private String planStartdate;
	private String planEnddate;
	private int planDday;
	private String planAreaTitle;
	private String planAreaAddr;
	private int planBudget;
	private int planTime;
	private int planMovetime;
	private int planMovedist;

	public PlanDto() {
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getPlanContentId() {
		return planContentId;
	}

	public void setPlanContentId(int planContentId) {
		this.planContentId = planContentId;
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

	public int getPlanDday() {
		return planDday;
	}

	public void setPlanDday(int planDday) {
		this.planDday = planDday;
	}

	public String getPlanAreaTitle() {
		return planAreaTitle;
	}

	public void setPlanAreaTitle(String planAreaTitle) {
		this.planAreaTitle = planAreaTitle;
	}

	public String getPlanAreaAddr() {
		return planAreaAddr;
	}

	public void setPlanAreaAddr(String planAreaAddr) {
		this.planAreaAddr = planAreaAddr;
	}

	public int getPlanBudget() {
		return planBudget;
	}

	public void setPlanBudget(int planBudget) {
		this.planBudget = planBudget;
	}

	public int getPlanTime() {
		return planTime;
	}

	public void setPlanTime(int planTime) {
		this.planTime = planTime;
	}

	public int getPlanMovetime() {
		return planMovetime;
	}

	public void setPlanMovetime(int planMovetime) {
		this.planMovetime = planMovetime;
	}

	public int getPlanMovedist() {
		return planMovedist;
	}

	public void setPlanMovedist(int planMovedist) {
		this.planMovedist = planMovedist;
	}

}
