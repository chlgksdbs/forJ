package com.forj.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forj.model.dto.NoticeDto;
import com.forj.model.repository.NoticeMapper;

@Service
public class NoticeService {
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	// 공지사항 전체 조회
	public List<NoticeDto> noticeList() {
		return noticeMapper.selectAll();
	}
}
