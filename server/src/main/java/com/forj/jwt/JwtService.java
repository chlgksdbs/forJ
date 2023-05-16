package com.forj.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${jwt.secret.key}")
	private String SALT;

	// User 정보를 가지고 AccessToken, RefreshToken을 생성하는 메서드 
	public <T> String generateToken(String key, T data, String subject) {
		
		// Access Token 생성 (만료시간 : 1000(ms) * 60 * 30 => 30분)
		String accessToken = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("regDate", System.currentTimeMillis())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.setSubject(subject)
				.claim(key, data)
				.signWith(this.generateKey(), SignatureAlgorithm.HS256)
				.compact();
		
		return accessToken;
	}
	
	private Key generateKey() {
		
		byte[] bytekey = null;
		Key key = null;
		
		try {
			bytekey = DatatypeConverter.parseBase64Binary(SALT);
			key = Keys.hmacShaKeyFor(bytekey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return key;
	}
	
	// JWT를 복호화하여 Token에 들어있는 정보를 꺼내는 메서드
	public Authentication getAuthentication(String accessToken) {
		
		// Token 복호화
		Claims claims = parseClaims(accessToken);
		
		if (claims.get("auth") == null) {
			throw new RuntimeException("권한 정보가 없는 토큰입니다.");
		}
		
		// Claim에서 권한 정보 가져오기
		Collection<? extends GrantedAuthority> authorities =
				Arrays.stream(claims.get("auth").toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		
		// UserDetails 객체를 만들어서 Authentication 리턴
		UserDetails principal = new User(claims.getSubject(), "", authorities);
		
		return new UsernamePasswordAuthenticationToken(principal, "", authorities);
	}
	
	// 토큰 정보를 검증하는 메서드
	public boolean validateToken(String token) {
		
		try {
			Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private Claims parseClaims(String accessToken) {
		
		try {
			return Jwts.parserBuilder()
					.setSigningKey(generateKey())
					.build()
					.parseClaimsJws(accessToken)
					.getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
