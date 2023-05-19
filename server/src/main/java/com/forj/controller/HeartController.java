package com.forj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forj.model.service.HeartService;

@RestController
@RequestMapping("/heart")
public class HeartController {

	@Autowired
	private HeartService heartSerivce;
	
	
}
