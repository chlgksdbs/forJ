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
import com.forj.model.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	// 공지사항 전체 조회
	@GetMapping("/list")
	public List<NoticeDto> noticeList() {
		return noticeService.noticeList();
	}

	// 상세 조회
	@GetMapping("/detail/{bno}")
	public NoticeDto boardDetail(@PathVariable("bno") int noticeId) {

		// 게시글 조회수 증가
		noticeService.updateViewCount(noticeId);

		return noticeService.noticeDetail(noticeId);
	}

	// 글 작성
	@PostMapping("/write")
	public void insertBoard(@RequestBody NoticeDto notice) {
		noticeService.insertNotice(notice);
	}

	// 글 수정
	@PutMapping("/modify")
	public void modifyBoard(@RequestBody NoticeDto notice) {
		noticeService.modifyNotice(notice);
	}

	// 글 삭제
	@DeleteMapping("/delete/{bno}")
	public void deleteBoard(@PathVariable("bno") int noticeId) {
		noticeService.deleteNotice(noticeId);
	}

	// 검색어로 들어온 키워드 리스트 출력
	@GetMapping("/search/{text}")
	public List<NoticeDto> searchList(@PathVariable("text") String text) {
		return noticeService.searchList(text);
	}

}
