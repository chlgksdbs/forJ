package com.forj.model.repository;

import org.apache.ibatis.annotations.Mapper;

import com.forj.model.dto.HeartDto;

@Mapper
public interface HeartMapper {

	// 좋아요 정보 조회
	HeartDto selectOne(HeartDto heartDto);
	
	// 좋아요 생성 (처음 요청)
	void insert(HeartDto heartDto);
	
	// 좋아요 수정 (0 -> 1 or 1 -> 0)
	void update(HeartDto heartDto);
}
