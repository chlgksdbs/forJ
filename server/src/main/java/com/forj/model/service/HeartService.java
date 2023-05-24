package com.forj.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forj.model.dto.HeartDto;
import com.forj.model.repository.HeartMapper;

@Service
public class HeartService {

	@Autowired
	private HeartMapper heartMapper;
	
	// 좋아요 총 갯수 조회
	public int count(int boardId) {
		
		return heartMapper.countAll(boardId);
	}
	
	// 현재 사용자의 게시글에 대한 좋아요 상태를 출력
	public int selectStatus(int boardId, String userId) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardId", boardId);
		map.put("userId", userId);
		
		return heartMapper.selectStatus(map);
	}
	
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
	
	// 내가 좋아요 한 게시글 리스트 출력
	public List<HeartDto> myList(String userId) {
		
		return heartMapper.selectMyList(userId);
	}
}
