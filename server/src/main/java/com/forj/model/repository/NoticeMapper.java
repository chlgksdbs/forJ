package com.forj.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.forj.model.dto.NoticeDto;

@Mapper
public interface NoticeMapper {

	// 공지사항 전체 조회
	List<NoticeDto> selectAll();
}
