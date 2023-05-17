package com.forj.area.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forj.area.model.service.AreaService;

@RestController
//@CrossOrigin("*") // CorsConfig로 처리
public class AreaController {
	
	@Autowired
	private AreaService service;
	
	@PostMapping("/api")
	public void saveData(Model model) {
		addAttribute(service.saveData());
	}
}
