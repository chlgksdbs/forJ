package com.forj.user.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginSuccess(@RequestBody UserDto userDto) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		
		try {
			UserDto loginInfo = userService.login(userDto);
			
			// 로그인 정보가 존재하는 경우
			if (loginInfo != null) {
				// Access Token 생성 (Key, Data)
				String accessToken = jwtService.createAccessToken("userId", loginInfo.getUserId());
				
				// Refresh Token 생성 (Key, Data)
				String refreshToken = jwtService.createRefreshToken("userId", loginInfo.getUserId());
				
				// Refresh Token -> DB 저장
//				userService.saveRefreshToken(userDto.getUserId(), refreshToken);
				
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
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
	
	// 로그아웃
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable("userId") String userId) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			userService.logout(userId);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	// 회원인증: 회원 정보를 담은 token 반환 메서드
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userId") String userId, HttpServletRequest request) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		
		// client에서 전달받은 request의 Header에 있는 access-token이 유효한 토큰인 경우 
		if (jwtService.validateToken(request.getHeader("access-token"))) {
			try {
				// 로그인 사용자 정보 가져오기 (사용자 이름, 아이디, 이메일 등)
				UserDto userDto = userService.userInfo(userId);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				// TODO: handle exception
				// 사용자 정보 조회 실패
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		// access-token이 유효하지 않은 토큰(사용 불가능한 토큰)인 경우
		else {
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	// 회원가입
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
	
	// 회원가입 시, 네이버 메일 전송
	@PostMapping("/certmail")
	public String certmail(@RequestBody UserDto userDto) throws Exception {
		
		String code = mailSendService.sendMail(userDto.getUserEmail());
		System.out.println(code);
		
		return code;
	}
}
