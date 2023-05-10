package com.forj.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forj.jwt.JwtToken;
import com.forj.user.model.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	public ResponseEntity<JwtToken> loginSuccess(@RequestBody Map<String, String> loginForm) {
		
		JwtToken token = service.login(loginForm.get("username"), loginForm.get("password"));
		
		return ResponseEntity.ok(token);
	}
	
}
