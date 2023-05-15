package com.forj.user.controller;


import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.forj.user.model.service.MailSendService;
//import com.forj.user.model.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
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
	
	@PostMapping("/certmail")
	public String certmail(String userEmail) throws Exception {
		
		String code = mailSendService.sendMail(userEmail);
		System.out.println(code);
		
		return code;
	}
}
