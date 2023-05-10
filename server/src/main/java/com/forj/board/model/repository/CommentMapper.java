package com.forj.board.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.forj.board.model.dto.CommentDto;

@Mapper
public interface CommentMapper {
	
	// 댓글 전체 조회
	List<CommentDto> selectAll(int boardId);
	
	// 댓글 작성
	void insert(CommentDto comment);
}
