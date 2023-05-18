package com.forj.user.model.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.forj.user.model.dto.UserDto;

@Mapper
public interface UserMapper {
	
	// 로그인
	UserDto login(UserDto userDto);
	
	// 로그아웃
	void logout(Map<String, String> map);
	
	// 회원가입
	void join(UserDto userDto);
	
}
