package com.forj.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forj.model.dto.BoardDto;
import com.forj.model.dto.CommentDto;
import com.forj.model.repository.BoardMapper;
import com.forj.model.repository.CommentMapper;

@Service
public class BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private CommentMapper commentMapper;
	
//	// 게시글 전체 조회
//	public List<BoardDto> boardList(){
//		return boardMapper.selectAll();
//	}
	
	// 페이지 네비게이션을 통한 전체 조회
	public Map<String, Object> boardList(int curPage) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// (1) 현재 페이지 정보 삽입
		map.put("curPage", curPage);
		
		int startRange = (curPage - 1) / 10 * 10 + 1; // 페이지 시작 범위 계산
		// (2) 페이지 시작 범위 정보 삽입
		map.put("startRange", startRange);
		
		int totalCount = boardMapper.selectTotalCount(); // 총 게시글의 수 계산
		int totalPage = totalCount / 10; // 총 페이지의 수 계산
		if (totalPage % 10 > 0) { // 나머지 게시글이 존재하는 경우, 페이지 수 증가
			totalPage++;
		}
		// (3) 총 페이지 수 정보 삽입
		map.put("totalPage", totalPage);
		
		int endRange = startRange + 9; // 페이지 마지막 범위 계산 (페이지 범위 10개로 세팅)
		if (endRange > totalPage) { // 페이지 마지막 범위가 총 페이지 수보다 많은 경우, 갱신
			endRange = totalPage;
		}
		// (4) 페이지 마지막 범위 정보 삽입
		map.put("endRange", endRange);
		
		// 현재 페이지에서 보여질 게시글의 시작 범위 계산
		int startRow = (curPage - 1) * 10; // limit 0, 10 || limit 10, 10 ...
		List<BoardDto> boardList = boardMapper.selectAll(startRow);
		// (5) 게시글 10개에 대한 정보 삽입
		map.put("boardList", boardList);
		
		return map;
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
	
	// 댓글 조회
	public CommentDto viewComment(int commentId) {
		return commentMapper.selectOne(commentId);
	}
	
	// 댓글 삭제
	public void deleteComment(int commentId) {
		commentMapper.delete(commentId);
	}
	
	// 댓글 수정
	public void modifyComment(CommentDto commentDto) {
		commentMapper.update(commentDto);
	}
	
	// 댓글 작성
	public void writeComment(CommentDto comment) {
		commentMapper.insert(comment);
	}
	
	// 검색어로 들어온 키워드 리스트 출력
	public List<BoardDto> searchList(String text) {
		return boardMapper.selectSearchAll(text);
	}
	
	// 내가 쓴 게시글 리스트 출력
	public List<BoardDto> myList(String userId) {
		return boardMapper.selectMyList(userId);
	}
	
	// 내가 쓴 댓글 리스트 출력
	public List<CommentDto> myCommentList(String userId) {
		return commentMapper.selectMyList(userId);
	}
}
