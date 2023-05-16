package com.forj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	// Interceptor를 이용해서 처리하므로 전역의 Cross Origin 처리
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		// 1. default 설정
		// Allow all origins.
		// Allow "simple" methods GET, HEAD and POST.
		// Allow all headers.
		// Set max age to 1800 seconds (30 minutes).
		
		
		// 2. 사용자 설정
		// (1) addMapping("/**") : 해당 서버의 모든 URL 요청에 해당 Class가 동작
		// (2) allowedOrigins("*") : 모든 Origin을 허용 (원하는 도메인만 허용하게 설정 가능)
		// (3) allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") : 원하는 HTTP Methods를 허용
		// (4) allowedHeaders("*") : Http Request Header에 허용해줄 Header Name을 설정
		// (5) maxAge(6000) : PreFilght 요청을 원하는 시간만큼 캐싱
		registry.addMapping("/**")
		.allowedOrigins("*")
//		.allowedOrigins("http://localhost:8080", "http://localhost:8081")
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
		.allowedHeaders("*")
		.maxAge(6000);
	}
}
