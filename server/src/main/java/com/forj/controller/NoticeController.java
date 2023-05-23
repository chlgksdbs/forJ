package com.forj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
