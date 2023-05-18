//package com.forj.area.controller;
//
//import java.util.ArrayList;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.forj.area.model.dto.AreaDto;
//import com.forj.area.model.service.AreaService;
//
//@RestController
//@RequestMapping("/area")
////@CrossOrigin("*") // CorsConfig로 처리
//public class AreaController {
//	
//	@Autowired
//	private AreaService service;
//	
//	// 여행지 정보 insert
//	@PostMapping("/write")
//	public void saveData(@RequestBody AreaDto areas) {
//		System.out.println(areas);
//		service.write(areas);
//	}
//}
