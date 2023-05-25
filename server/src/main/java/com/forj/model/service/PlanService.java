package com.forj.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	// 사용자 일정 전체 조회
	public List<PlannerDto> list(String userId) {
		
		return planMapper.selectAll(userId);
	}
	
	// 사용자 일정 상세 조회
	public List<PlanDto> detail(int planId, String userId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("planId", planId);
		map.put("userId", userId);
		
		return planMapper.selectDetail(map);
		
	}
}