package com.forj.user.model.repository;

import org.apache.ibatis.annotations.Mapper;

import com.forj.user.model.dto.UserDto;

@Mapper
public interface UserMapper {
	
	// 로그인
	UserDto login(UserDto userDto);
}
