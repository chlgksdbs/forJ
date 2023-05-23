package com.forj.model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.forj.model.dto.AreaDto;


@Mapper
public interface AreaMapper {
	
//	// 여행지 정보 insert
//	void insert(AreaDto area);
	
	// 여행지 정보 get
	// 키워드를 입력하지 않았으면
	List<AreaDto> selectAll(Map<String, Object> map);
	// 키워드를 선택했으면
	List<AreaDto> selectSome(Map<String, Object> map);

}
