package com.forj.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.forj.model.dto.BoardDto;
import com.forj.model.dto.NoticeDto;

@Mapper
public interface NoticeMapper {

	// 공지사항 전체 조회
	List<NoticeDto> selectAll();
	
	// 공지사항 최신 글 5개 불러오기
	List<NoticeDto> selectLimitAll();
	
	// 글 작성
	void insert(NoticeDto notice);
	
	// 상세 조회
	NoticeDto selectOne(int noticeId);
	
	// 글 조회수 증가
	void updateHit(int noticeId);
	
	// 글 수정
	void update(NoticeDto notice);
	
	// 글 삭제
	void delete(int noticeId);
	
	// 검색어로 들어온 키워드 리스트 출력
	List<NoticeDto> selectSearchAll(String text);
}
