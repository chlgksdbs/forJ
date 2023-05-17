package com.forj.user.model.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

	@Autowired
	private JavaMailSender mailSender;
	private String certKey; // 인증 번호 (6자리)
	
	// 1. 메일 내용 작성 (to : 보내는 대상)
	public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
		
		System.out.println("to: " + to);
		System.out.println("certKey: " + certKey);
		
		MimeMessage message = mailSender.createMimeMessage();
		
		message.addRecipients(Message.RecipientType.TO, to); // 보내는 대상
		message.setSubject("[forJ] 회원가입 이메일 인증번호"); // 메일 제목
		
		String content = ""; // 메일 본문
		content += "<div>";
		content += "<h2>안녕하세요. forJ 관리자 입니다.</h2>";
		content += "<h2>회원가입 인증번호는 다음과 같습니다.</h2>";
		content += "<hr>";
		content += "<h2 style='color: blue;'>인증번호 : <strong>" + certKey;
		content += "</strong></h2>";
		content += "<div style='font-size: 8pt;'>이용약관 | 개인정보처리방침</div>";
		content += "<div style='font-size: 8pt;'>본 메일은 발신전용 메일이므로 문의 및 회신하실 경우 답변되지 않습니다.</div>";
		content += "<div style='font-size: 8pt;'>문의사항은 fgan75@naver.com를 이용해주시기 바랍니다.</div>";
		content += "<div style='font-size: 8pt;'>Copyright ⓒforJ. All Rights Reserved.</div>";
		content += "</div>";
		
		// 내용, charset type, subtype
		message.setText(content, "utf-8", "html");
		
		// 보내는 사람의 이메일 주소
		message.setFrom("chlgksdbs98@naver.com");
		
		
		return message;
	}
	
	// 2. 랜덤 인증 번호 생성
	public String createCertNumber() {
		
		String randKey = "";
		Random random = new Random();
		
		for (int i = 0; i < 6; i++) {
			randKey += Integer.toString(random.nextInt(9)); // 0부터 9까지 올 수 있는 1자리 난수 생성
		}
		
		return randKey;
	}
	
	// 3. 메일 발송
	public String sendMail(String to) throws Exception {
		
		certKey = createCertNumber(); // 랜덤 인증 번호 생성
		
		try {
			MimeMessage message = createMessage(to); // message 세팅
			mailSender.send(message); // 메일 발송
		} catch (MailException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		
		return certKey; // 메일로 보낸 인증 번호를 server로 반환
	}
}
