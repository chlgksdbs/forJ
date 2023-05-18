//package com.forj.jwt;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.GenericFilterBean;
//
//@Component
//public class JwtAuthenticationFilter extends GenericFilterBean {
//	
//	private static final String HEADER_AUTH = "auth-token";
//	
//	@Autowired
//	private JwtService jwtService;
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		
//		// 1. Request Header에서 JWT 추출
//		String token = resolveToken((HttpServletRequest) request);
//		
//		// 2. validateToken으로 Token 유효성 검사
//		if (token != null && jwtService.validateToken(token)) {
//			// Token이 유효할 경우, Token에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
//			Authentication authentication = jwtService.getAuthentication(token);
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//		}
//		
//		chain.doFilter(request, response);
//	}
//	
//	// Request Header에서 Token 추출
//	private String resolveToken(HttpServletRequest request) {
//		
//		String bearerToken = request.getHeader(HEADER_AUTH);
//		
//		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
//			return bearerToken.substring(7);
//		}
//		
//		return null;
//	}
//
//}
