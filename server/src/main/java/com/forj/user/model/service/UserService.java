package com.forj.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forj.jwt.JwtTokenProvider;
import com.forj.user.model.repository.UserMapper;

@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManagerBuilder authenticationManagerBuilder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
//	@Transactional
//	public JwtToken login(String userId, String userPw) {
//		
//		// 1. Login ID/PW를 기반으로 Authentication 객체 생성
//		// 이 때, authentication는 인증 여부를 확인하는 authenticated 값이 false
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, userPw);
//		
//		// 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
//		// authenticate 메서드가 실행될 때 CustomUserDetailsService에서 만든 loadUserByUsername 메서드가 실행
//		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//		
//		// 3. 검증된 인증 정보를 기반으로 JWT 생성
//		JwtToken token = jwtTokenProvider.generateToken(authentication);
//		
//		return token;
//	}

}