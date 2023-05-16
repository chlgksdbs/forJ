package com.forj.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forj.user.model.dto.UserDto;
import com.forj.user.model.repository.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
//	@Autowired
//	private BCryptPasswordEncoder encoder;
//	
//	@Autowired
//	private AuthenticationManagerBuilder authenticationManagerBuilder;
//	
//	@Autowired
//	private JwtService jwtTokenProvider;
	
	public UserDto login(UserDto userDto) {
		
		// 입력된 id나 pw 정보가 없는 경우, null을 리턴
		if (userDto.getUserId() == null || userDto.getUserPw() == null)
			return null;
		
		return userMapper.login(userDto);
		
		// 1. Login ID/PW를 기반으로 Authentication 객체 생성
		// 이 때, authentication는 인증 여부를 확인하는 authenticated 값이 false
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, userPw);
		
		// 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
		// authenticate 메서드가 실행될 때 CustomUserDetailsService에서 만든 loadUserByUsername 메서드가 실행
//		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		
		// 3. 검증된 인증 정보를 기반으로 JWT 생성
//		JwtDto token = jwtTokenProvider.generateToken(authentication);
		
//		return token;
	}

}