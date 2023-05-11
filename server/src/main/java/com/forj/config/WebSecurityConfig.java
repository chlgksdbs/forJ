package com.forj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.forj.jwt.JwtAuthenticationFilter;
import com.forj.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity // Web 보안 활성화를 위한 annotation
public class WebSecurityConfig {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	// 출처 : https://datamoney.tistory.com/334
	// csrf(cross site request forgery) : 사이트간 위조 요청 -> 인증된 사용자의 토큰을 탈취해 위조된 요청을 보냈을 경우 파악해 방지하는 것
	// (1) .csrf().disable() : rest api에서는 권한이 필요한 요청 위해서 인증 정보를 포함시켜야 함. 서버에 인증 정보를 저장하지 않기 때문에 작성할 필요 없음 (JWT를 쿠키에 저장히자 않기 때문)
	// (2) .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) : JWT를 사용하기 때문에 session도 사용하지 않음
	// (3) .formLogin().disable() : HTTP Basic Authentication을 사용하지 않음
	// (4) .httpBasic().disable() : Form Based Authentication을 사용하지 않음
	// (5) .authorizeRequests() : 요청에 의한 보안 검사 시작
	// (6) .antMatchers("/user").permitAll() : 해당 API에 대해서는 모든 요청을 허가한다는 설정
	// (7) .anyRequest().authenticated() : 이 밖의 모든 요청에 대해서 인증을 필요로 한다는 설정
	// (8) .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class) : UsernamePasswordAuthenticationFilter에 가기 전에 직접 작성한 JwtAuthenticationFilter를 실행함 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.formLogin().disable()
			.httpBasic().disable()
			.authorizeRequests()
			.antMatchers("/user").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
		System.out.println(jwtTokenProvider);
		return http.build();
	}
}
