package com.forj.user.model.repository;

import org.apache.ibatis.annotations.Mapper;

import com.forj.user.model.dto.UserDto;

@Mapper
public interface UserMapper {
	
	// 회원가입
	void join(UserDto userDto);
	
	// 로그인
	UserDto login(UserDto userDto);
}
