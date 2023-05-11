package com.forj.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forj.jwt.JwtToken;
import com.forj.jwt.JwtTokenProvider;
import com.forj.user.model.repository.UserMapper;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public JwtToken login(String userId, String userPw) {
		
		// Authentication 객체 생성
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, userPw);
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		// 검증된 인증 정보로 JWT 생성
		JwtToken token = jwtTokenProvider.generateToken(authentication);
		
		return token;
	}

}