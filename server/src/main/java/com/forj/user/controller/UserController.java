package com.forj.user.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forj.jwt.JwtService;
import com.forj.user.model.dto.UserDto;
import com.forj.user.model.service.MailSendService;
import com.forj.user.model.service.UserService;

@RestController
@RequestMapping("/user")
// @CrossOrigin("*") // CorsConfig로 처리
public class UserController {
	
	// 토큰 검증 시, 활용할 메시지
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private MailSendService mailSendService;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginSuccess(@RequestBody UserDto userDto) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			UserDto loginInfo = userService.login(userDto);
			
			// 로그인 정보가 존재하는 경우
			if (loginInfo != null) {
				// JWT 생성 (Key, Data, Subject)
				String token = jwtService.generateToken("userId", loginInfo.getUserId(), "access-token");
				
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
			// 로그인 정보가 존재하지 않는 경우
			else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@PostMapping("/join")
	public String join(@RequestBody UserDto userDto) {
		
		try {
			userService.join(userDto);
			return "JOIN SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return "JOIN FAIL";
		}
	}
	
	@PostMapping("/certmail")
	public String certmail(@RequestBody UserDto userDto) throws Exception {
		
		String code = mailSendService.sendMail(userDto.getUserEmail());
		System.out.println(code);
		
		return code;
	}
}
