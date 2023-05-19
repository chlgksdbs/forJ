package com.forj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forj.model.dto.HeartDto;
import com.forj.model.service.HeartService;

@RestController
@RequestMapping("/heart")
public class HeartController {

	@Autowired
	private HeartService heartService;
	
	// 현재 게시글의 총 좋아요 갯수 출력
	@GetMapping("/count/{boardId}")
	public int heartCount(@PathVariable int boardId) {
		
		return heartService.count(boardId);
	}
	
	// 처음 요청은, 게시글에 사용자가 좋아요를 한 번이라도 누른적이 있는 지에 대한 체크
	@PostMapping("/check")
	public int heartCheck(@RequestBody HeartDto heartDto) {
		
		try {
			// HeartDto에 대한 정보가 DB에 존재하는 지 체크
			HeartDto heartInfo = heartService.selectOne(heartDto);
			
			// 존재하는 경우, HEART_STATUS 값 확인 후, 변경
			// 좋아요가 비활성화(0) 상태인 경우, 활성화(1)로 변경
			if (heartInfo.getHeartStatus() == 0) {
				heartInfo.setHeartStatus(1);
				heartService.update(heartInfo);
			}
			// 좋아요가 활성화(1) 상태인 경우, 비활성화(0)로 변경
			else {
				heartInfo.setHeartStatus(0);
				heartService.update(heartInfo);
			}
			
			// 현재 좋아요 상태를 리턴
			return heartInfo.getHeartStatus();
		} catch (NullPointerException e) {
			// TODO: handle exception
			// 존재하지 않는 경우, 생성 후 HEART_STATUS 값을 1로 설정
			heartService.insert(heartDto); // HEART_STATUS의 default값은 1
			return 1;
		}
	}
}
