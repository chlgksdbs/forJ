package com.forj.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.forj.board.model.dto.CommentDto;
import com.forj.board.model.service.BoardService;

@RestController
@CrossOrigin("*")
public class CommentController {
	
	@Autowired
	private BoardService boardService;
	
	// 댓글 전체 조회
	@GetMapping("/comment/{bno}")
	public List<CommentDto> commentList(@PathVariable("bno") int boardId){
		return boardService.commentList(boardId);
	}
	
	// 댓글 작성
	@PostMapping("/comment")
	public void writeComment(@RequestBody CommentDto comment) {
		boardService.writeComment(comment);
	}
	
}
