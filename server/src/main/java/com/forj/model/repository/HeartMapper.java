package com.forj.model.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.forj.model.dto.HeartDto;

@Mapper
public interface HeartMapper {
	
	// 좋아요 총 갯수 조회
	int countAll(int boardId);

	// 좋아요 정보 조회
	HeartDto selectOne(HeartDto heartDto);
	
	// 현재 사용자의 게시글에 대한 좋아요 상태를 출력
	int selectStatus(Map<String, Object> map);
	
	// 좋아요 생성 (처음 요청)
	void insert(HeartDto heartDto);
	
	// 좋아요 수정 (0 -> 1 or 1 -> 0)
	void update(HeartDto heartDto);
	
}
