package com.forj.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.forj.model.dto.AreaDto;
import com.forj.model.repository.AreaMapper;

@Service
public class AreaService {
	
	@Autowired
	private AreaMapper mapper;
	
//	// 여행지 정보 insert
//	public void write(AreaDto areas) {
////		for(int i=0; i<areas.size(); i++) {
////			mapper.insert(areas.get(i));
////		}
//			mapper.insert(areas);
//	}
	
	// 여헹지 정보 get
	public List<AreaDto> list(String keyword, int typeId){
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("typeId", typeId);
		
		return mapper.selectSome(map);
	}
}
