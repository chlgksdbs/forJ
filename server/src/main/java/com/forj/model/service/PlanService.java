package com.forj.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forj.model.dto.PlanDto;
import com.forj.model.dto.PlannerDto;
import com.forj.model.repository.PlanMapper;

@Service
public class PlanService {
	
	@Autowired
	private PlanMapper planMapper;
	
	// 여행 일정 생성
	public void create(PlannerDto plannerDto) {
		
		planMapper.create(plannerDto);
	}
	
	public int selectPlanId(PlannerDto plannerDto) {
		
		return planMapper.selectPlanId(plannerDto);
	}
	
	// 여행 일정 삽입
	public void write(PlanDto planDto) {
		
		planMapper.insert(planDto);
	}
}
