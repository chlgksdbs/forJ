package com.forj.model.repository;

import org.apache.ibatis.annotations.Mapper;

import com.forj.model.dto.PlanDto;
import com.forj.model.dto.PlannerDto;

@Mapper
public interface PlanMapper {
	
	// 여행 일정 생성
	void create(PlannerDto plannerDto);
	
	int selectPlanId(PlannerDto plannerDto);
	
	// 여행 일정 삽입
	void insert(PlanDto planDto);
}
