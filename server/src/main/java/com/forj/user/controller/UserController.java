package com.forj.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forj.user.model.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@PostMapping("/login")
//	public JwtToken loginSuccess(@RequestBody Map<String, String> loginInfo) {
//		
//		JwtToken token = userService.login(loginInfo.get("userId"), loginInfo.get("userPw"));
//		
//		return token;
//	}
	
}
