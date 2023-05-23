package com.forj.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forj.model.dto.BoardDto;
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

	// 공지사항 최신 글 5개 불러오기
	public List<NoticeDto> noticeLimitList() {
		return noticeMapper.selectLimitAll();
	}

	// 글 작성
	public void insertNotice(NoticeDto notice) {
		noticeMapper.insert(notice);
	}

	// 상세 조회
	public NoticeDto noticeDetail(int noticeId) {
		return noticeMapper.selectOne(noticeId);
	}

	// 글 조회수 증가
	public void updateViewCount(int noticeId) {
		noticeMapper.updateHit(noticeId);
	}

	// 글 수정
	public void modifyNotice(NoticeDto notice) {
		noticeMapper.update(notice);
	}

	// 글 삭제
	public void deleteNotice(int noticeId) {
		noticeMapper.delete(noticeId);
	}

	// 검색어로 들어온 키워드 리스트 출력
	public List<NoticeDto> searchList(String text) {
		return noticeMapper.selectSearchAll(text);
	}
}
