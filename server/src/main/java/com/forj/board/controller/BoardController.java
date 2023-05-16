package com.forj.board.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forj.board.model.dto.BoardDto;
import com.forj.board.model.service.BoardService;

@RestController
@RequestMapping("/board")
// @CrossOrigin("*") // CorsConfig로 처리
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
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
		return boardService.boardDetail(boardId);
	}
	
	// 글 수정
	@PutMapping("/modify")
	public void modifyBoard(@RequestBody BoardDto board) {
		System.out.println(board.getBoardId());
		System.out.println(board.getTitle());
		boardService.modifyBoard(board);
	}
	
	// 글 삭제
	@DeleteMapping("/delete/{bno}")
	public void deleteBoard(@PathVariable("bno") int boardId) {
		boardService.deleteBoard(boardId);
	}
	
}
