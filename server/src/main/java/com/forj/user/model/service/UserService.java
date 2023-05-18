package com.forj.user.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forj.user.model.dto.UserDto;
import com.forj.user.model.repository.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	// 로그인
	public UserDto login(UserDto userDto) {
		
		// 입력된 id나 pw 정보가 없는 경우, null을 리턴
		if (userDto.getUserId() == null || userDto.getUserPw() == null)
			return null;
		
		return userMapper.login(userDto);
	}
	
	// 로그아웃
	public void logout(String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("refreshToken", null);
		
		userMapper.logout(map);
	}
	
	// 회원가입
	public void join(UserDto userDto) {
		
		userMapper.join(userDto);
	}

}