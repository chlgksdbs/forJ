package com.forj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forj.model.dto.PlanDto;
import com.forj.model.dto.PlannerDto;
import com.forj.model.service.PlanService;

@RestController
@RequestMapping("/plan")
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	// API 1. 사용자가 설정한 플랜을 DB에 생성하는 메서드
	@PostMapping("/create")
	public int planCreate(@RequestBody PlannerDto plannerDto) {
		
		planService.create(plannerDto);
		
		int result = planService.selectPlanId(plannerDto);
//		System.out.println(result);
		
		return result;
	}
	
	// API 2. 사용자가 설정한 일정을 DB에 등록하는 메서드
	@PostMapping("/write")
	public void planWrite(@RequestBody PlanDto planDto) {
		
		planService.write(planDto);
	}
}
