package com.forj.jwt;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.forj.exception.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
	
	public static final Logger logger = LoggerFactory.getLogger(JwtService.class);

	// SALT는 토큰 유효성 확인 시 사용하기 때문에 외부에 노출되지 않도록 설정
	@Value("${jwt.secret.key}")
	private String SALT;
	
	// User 정보(userId)를 가지고 AccessToken을 생성하는 메서드 
	public String createAccessToken(String key, String data) {
		
		// Access Token 생성 (만료시간 : 1000(ms) * 60 * 30 => 30분)
		String accessToken = Jwts.builder()
				// (1) Header 설정: Token 타입, 해쉬 알고리즘 정보 설정
				.setHeaderParam("typ", "JWT")
				// (2) Token 제목 설정. ex) access-token, refresh-token
				.setSubject("access-token")
				// (3) 생성일 설정 (현재 시간)
				.setIssuedAt(new Date())
				// (4) 만료일 설정 (유효기간)
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				// (5) Claim에 들어갈 key, data 정보 설정 (여기서는 key: "userId", data: userId)
				.claim(key, data)
				// (6) Signature 설정: secret key를 활용한 암호화
				.signWith(SignatureAlgorithm.HS256,this.generateKey())
				// (7) 직렬화 처리
				.compact();
		
		return accessToken;
	}
	
	// User 정보(userId)를 가지고 RefreshToken을 생성하는 메서드 (AccessToken에 비해 유효기간을 길게 설정)
	public String createRefreshToken(String key, String data) {
		
		// Refresh Token 생성 (만료시간 : 1000(ms) * 60 * 60 * 24 * 7 => 1주일)
		String refreshToken = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setSubject("refresh-token")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
				.claim(key, data)
				.signWith(SignatureAlgorithm.HS256, this.generateKey())
				.compact();
		
		return refreshToken;
	}
	
	// Signature 설정에 들어갈 key 생성
	private byte[] generateKey() {
		
		byte[] key = null;
		
		try {
			key = SALT.getBytes("UTF-8"); // charset 설정 안하면 사용자 플랫폼의 기본 인코딩 설정으로 인코딩됨
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			else {
				logger.error("Making JWT Key Error ::: {}", e.getMessage());
			}
		}
		
		return key;
	}
	
	// 토큰 정보를 검증하는 메서드 (문제가 있다면 UnauthorizedException 에러 발생)
	public boolean validateToken(String token) {
		
		try {
			
			// Json Web Signature란?
			// -> 서버에서 인증을 근거로 인증 정보를 서버의 private key로 서명한 것을 토큰화한 것
			Jws<Claims> claims = Jwts.parser()
					// (1) JWS 서명 검증을 위한 secret key 설정
					.setSigningKey(this.generateKey())
					// (2) 파싱하여 원본 Jws 생성
					.parseClaimsJws(token);
			
			// Claims는 Map의 구현체 형태
			logger.debug("claims: {}", claims);
			
			return true;
		} catch (Exception e) {
//			if (logger.isInfoEnabled()) {
//				e.printStackTrace();
//			}
//			else {
				logger.error(e.getMessage());
//			}
			
//			throw new UnauthorizedException();
			// 개발환경
			return false;
		}
	}
	
	// JWT를 복호화하여 Token에 들어있는 정보를 꺼내는 메서드
	public Map<String, Object> get(String key) {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		
		String token = request.getHeader("access-token");
		Jws<Claims> claims = null;
		
		try {
			claims = Jwts.parser()
					.setSigningKey(this.generateKey())
					.parseClaimsJws(token);
		} catch (Exception e) {
			// TODO: handle exception
//			if (logger.isInfoEnabled()) {
//				e.printStackTrace();
//			}
//			else {
				logger.error(e.getMessage());
//			}
			throw new UnauthorizedException();
			// 개발환경
//			Map<String, Object> testMap = new HashMap<String, Object>();
//			testMap.put("userId", userId);
//			return testMap;
		}
		Map<String, Object> value = claims.getBody();
		logger.info("value: {}", value);
		return value;
	}
	
	public String getUserId() {
		return (String) this.get("user").get("userId");
	}
}
