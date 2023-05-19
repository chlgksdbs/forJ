package com.forj.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.forj.model.dto.BoardDto;

@Mapper
public interface BoardMapper {

	// 전체 조회
	List<BoardDto> selectAll();
	
	// 글 작성
	void insert(BoardDto board);
	
	// 상세 조회
	BoardDto selectOne(int boardId);
	
	// 글 조회수 증가
	void updateHit(int boardId);
	
	// 글 수정
	void update(BoardDto board);
	
	// 글 삭제
	void delete(int boardId);
	
}