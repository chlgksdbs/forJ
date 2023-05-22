package com.forj.model.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.forj.model.dto.UserDto;

@Mapper
public interface UserMapper {
	
	// 로그인
	UserDto login(UserDto userDto);
	
	// 로그아웃
	void logout(Map<String, String> map);
	
	// 사용자 정보 조회
	UserDto userInfo(String userId);
	
	// refresh token 저장
	void saveRefreshToken(Map<String, String> map);
	
	// refresh token 조회
	String getRefreshToken(String userId);
	
	// 회원가입
	void join(UserDto userDto);
	
	// 프로필 이미지 변경
	void setProfileImg(Map<String, Object> map);
	
	// 프로필 이미지 출력
	UserDto selectProfileImg(String userId);
}
