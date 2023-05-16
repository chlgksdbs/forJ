package com.forj.user.controller;


import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forj.user.model.service.MailSendService;
//import com.forj.user.model.service.UserService;

@RestController
@RequestMapping("/user")
// @CrossOrigin("*") // CorsConfig로 처리
public class UserController {
	
//	@Autowired
//	private UserService userService;
	
	@Autowired
	private MailSendService mailSendService;
	
//	@PostMapping("/login")
//	public JwtToken loginSuccess(@RequestBody Map<String, String> loginInfo) {
//		
//		JwtToken token = userService.login(loginInfo.get("userId"), loginInfo.get("userPw"));
//		
//		return token;
//	}
	
	@GetMapping("/join")
	public String join() {
		return "user/join";
	}
	
	@PostMapping("/certmail/{userEmail}")
	public String certmail(@PathVariable String userEmail) throws Exception {
		
		System.out.println(userEmail);
		String code = mailSendService.sendMail(userEmail);
		System.out.println(code);
		
		return code;
	}
}
