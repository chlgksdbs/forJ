package com.forj.model.repository;

import java.util.List;
import java.util.Map;

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
	
	// 사용자 일정 전체 조회
	List<PlannerDto> selectAll(String userId);
	
	// 사용자 일정 상세 조회
	List<PlanDto> selectDetail(Map<String, Object> map);
}
