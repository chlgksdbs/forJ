package com.forj.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forj.user.model.repository.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

}