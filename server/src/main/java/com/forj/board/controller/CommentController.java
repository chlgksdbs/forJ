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

import com.forj.board.model.dto.CommentDto;
import com.forj.board.model.service.BoardService;

@RestController
@RequestMapping("/comment")
// @CrossOrigin("*") // CorsConfig로 처리
public class CommentController {
	
	@Autowired
	private BoardService boardService;
	
	// 댓글 전체 조회
	@GetMapping("/{bno}")
	public List<CommentDto> commentList(@PathVariable("bno") int boardId){
		return boardService.commentList(boardId);
	}
	
	// 댓글 조회
	@GetMapping("/view/{cno}")
	public CommentDto detailComment(@PathVariable("cno") int commentId) {
		return boardService.viewComment(commentId);
	}
	
	// 댓글 삭제
	@DeleteMapping("/{cno}")
	public void deleteComment(@PathVariable("cno") int commentId) {
		boardService.deleteComment(commentId);
	}
	
	// 댓글 수정
	@PutMapping
	public void modifyComment(@RequestBody CommentDto commentDto) {
		boardService.modifyComment(commentDto);
	}
	
	// 댓글 작성
	@PostMapping
	public void writeComment(@RequestBody CommentDto comment) {
		boardService.writeComment(comment);
	}
	
}
