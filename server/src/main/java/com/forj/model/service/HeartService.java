package com.forj.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forj.model.dto.HeartDto;
import com.forj.model.repository.HeartMapper;

@Service
public class HeartService {

	@Autowired
	private HeartMapper heartMapper;
	
	// 좋아요 정보 조회
	public HeartDto selectOne(HeartDto heartDto) {
		
		return heartMapper.selectOne(heartDto);
	};
	
	// 좋아요 생성 (처음 요청)
	public void insert(HeartDto heartDto) {
		
		heartMapper.insert(heartDto);
	}
	
	// 좋아요 수정 (0 -> 1 or 1 -> 0)
	public void update(HeartDto heartDto) {
		
		heartMapper.update(heartDto);
	}
}
