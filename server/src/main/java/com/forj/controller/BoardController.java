package com.forj.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forj.model.dto.BoardDto;
import com.forj.model.dto.NoticeDto;
import com.forj.model.service.BoardService;
import com.forj.model.service.NoticeService;

@RestController
@RequestMapping("/board")
// @CrossOrigin("*") // CorsConfig로 처리
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private NoticeService noticeService;
	
	// 전체 조회
	@GetMapping("/list")
	public List<BoardDto> boardList() {
		return boardService.boardList();
	}
	
	// 글 작성
	@PostMapping("/write")
	public void insertBoard(@RequestBody BoardDto board) {
		boardService.insertBoard(board);
	}
	
	// 상세 조회
	@GetMapping("/detail/{bno}")
	public BoardDto boardDetail(@PathVariable("bno") int boardId) {
		
		// 게시글 조회수 증가
		boardService.updateViewCount(boardId);
		
		return boardService.boardDetail(boardId);
	}
	
	// 글 수정
	@PutMapping("/modify")
	public void modifyBoard(@RequestBody BoardDto board) {
		boardService.modifyBoard(board);
	}
	
	// 글 삭제
	@DeleteMapping("/delete/{bno}")
	public void deleteBoard(@PathVariable("bno") int boardId) {
		boardService.deleteBoard(boardId);
	}
	
	// 공지사항 최신 글 5개 불러오기
	@GetMapping("/notice/list")
	public List<NoticeDto> noticeList() {
		return noticeService.noticeLimitList();
	}
	
	// 검색어로 들어온 키워드 리스트 출력
	@GetMapping("/search/{text}")
	public List<BoardDto> searchList(@PathVariable("text") String text) {
		return boardService.searchList(text);
	}
	
}
