package com.forj.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forj.board.model.dto.BoardDto;
import com.forj.board.model.dto.CommentDto;
import com.forj.board.model.repository.BoardMapper;
import com.forj.board.model.repository.CommentMapper;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
	// 게시글 전체 조회
	public List<BoardDto> boardList(){
		return boardMapper.selectAll();
	}
	
	// 글 작성
	public void insertBoard(BoardDto board) {
		boardMapper.insert(board);
	}
	
	// 상세 조회
	public BoardDto boardDetail(int boardId) {
		return boardMapper.selectOne(boardId);
	}
	
	// 글 조회수 증가
	public void updateViewCount(int boardId) {
		boardMapper.updateHit(boardId);
	}
	
	// 글 수정
	public void modifyBoard(BoardDto board) {
		boardMapper.update(board);
	}
	
	// 글 삭제
	public void deleteBoard(int boardId) {
		boardMapper.delete(boardId);
	}
	
	// 댓글 전체 조회
	public List<CommentDto> commentList(int boardId){
		return commentMapper.selectAll(boardId);
	}
	
	// 댓글 작성
	public void writeComment(CommentDto comment) {
		commentMapper.insert(comment);
	}
}
