package com.forj.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.forj.model.dto.CommentDto;

@Mapper
public interface CommentMapper {
	
	// 댓글 전체 조회
	List<CommentDto> selectAll(int boardId);
	
	// 댓글 조회
	CommentDto selectOne(int commentId);
	
	// 댓글 삭제
	void delete(int commentId);
	
	// 댓글 수정
	void update(CommentDto commentDto);
	
	// 댓글 작성
	void insert(CommentDto comment);
}
