package com.forj.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forj.model.dto.AreaDto;
import com.forj.model.dto.BoardDto;
import com.forj.model.service.AreaService;


@RestController
@RequestMapping("/area")
//@CrossOrigin("*") // CorsConfig로 처리
public class AreaController {
	
	@Autowired
	private AreaService service;
	
//	// 여행지 정보 insert
//	@PostMapping("/write")
//	public void saveData(@RequestBody AreaDto areas) {
//		System.out.println(areas);
//		service.write(areas);
//	}
	
	// 여행지 정보 get
	@GetMapping("/list/{word}/{id}")
	public List<AreaDto> list(@PathVariable("word") String keyword, @PathVariable("id") String typeId){
		List<AreaDto> a = service.list(keyword, Integer.parseInt(typeId));
		System.out.println(a);
		return a;
	}
}
