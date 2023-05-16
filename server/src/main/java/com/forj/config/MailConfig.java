package com.forj.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	
	@Bean
	public JavaMailSender javaMailService() {
		
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		
		javaMailSender.setHost("smtp.naver.com"); // 메인 도메인 서버 주소 (smtp 서버 주소)
		javaMailSender.setUsername("chlgksdbs98"); // 네이버 아이디
		javaMailSender.setPassword("zxc1599611"); // 네이버 비밀번호
		javaMailSender.setPort(465); // 메일 인증 서버 port
		javaMailSender.setJavaMailProperties(getMailProperties()); // 메일 인증 서버 정보 가져오기
		javaMailSender.setDefaultEncoding("UTF-8");
		
		return javaMailSender;
	}
	
	private Properties getMailProperties() {
		
		Properties properties = new Properties();
		
		properties.setProperty("mail.transport.protocol", "smtp"); // 프로토콜 설정
		properties.setProperty("mail.smtp.auth", "true"); // smtp 인증
		properties.setProperty("mail.smtp.starttls.enable", "true"); // smtp strattles 사용
		properties.setProperty("mail.debug", "true"); // debug 사용
		properties.setProperty("mail.smtp.ssl.trust", "smtp.naver.com"); // ssl 인증 서버 : smtp.naver.com
		properties.setProperty("mail.smtp.ssl.enable", "true"); // ssl 사용
		
		return properties;
	}
}
