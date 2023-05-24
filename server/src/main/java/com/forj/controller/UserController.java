package com.forj.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.forj.model.dto.UserDto;
import com.forj.model.service.JwtService;
import com.forj.model.service.MailSendService;
import com.forj.model.service.UserService;

@RestController
@RequestMapping("/user")
// @CrossOrigin("*") // CorsConfig로 처리
public class UserController {

	// 토큰 검증 시, 활용할 메시지
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private MailSendService mailSendService;

	// API 1. 로그인
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> loginSuccess(@RequestBody UserDto userDto) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;

		try {
			UserDto loginInfo = userService.login(userDto);

			// 로그인 정보가 존재하는 경우
			if (loginInfo != null) {
				// Access Token 생성 (Key, Data)
				String accessToken = jwtService.createAccessToken("userId", loginInfo.getUserId());

				// Refresh Token 생성 (Key, Data)
				String refreshToken = jwtService.createRefreshToken("userId", loginInfo.getUserId());

				// Refresh Token -> DB 저장
				userService.saveRefreshToken(userDto.getUserId(), refreshToken);

				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
			// 로그인 정보가 존재하지 않는 경우
			else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// API 2. 로그아웃
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> logout(@PathVariable("userId") String userId) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			userService.logout(userId);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			// TODO: handle exception
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// API 3. 회원인증: 회원 정보를 담은 token 반환 메서드
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("userId") String userId,
			HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;

		// client에서 전달받은 request의 Header에 있는 access-token이 유효한 토큰인 경우
		if (jwtService.validateToken(request.getHeader("access-token"))) {
			try {
				// 로그인 사용자 정보 가져오기 (사용자 이름, 아이디, 이메일 등)
				UserDto userDto = userService.userInfo(userId);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				// TODO: handle exception
				// 사용자 정보 조회 실패
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}
		// access-token이 유효하지 않은 토큰(사용 불가능한 토큰)인 경우
		else {
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// API 4. Refresh Token을 이용한 Access Token 재발급
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;

		// client에서 전달받은 request의 Header에 있는 refresh-token 가져오기
		String refreshToken = request.getHeader("refresh-token");

		// refresh-token이 유효한 경우
		if (jwtService.validateToken(refreshToken)) {

			// client로부터 받아온 유효한 refresh-token이 DB에 저장되어있는 refresh-token과 같은 경우 수행
			if (refreshToken.equals(userService.getRefreshToken(userDto.getUserId()))) {
				// access-token 재발급
				String accessToken = jwtService.createAccessToken("userId", userDto.getUserId());

				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		}
		// refresh-token이 유효하지 않은 경우
		else {
			status = HttpStatus.UNAUTHORIZED;
		}

		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// API 5. 회원가입
	@PostMapping("/join")
	public String join(@RequestBody UserDto userDto) {

		try {
			userService.join(userDto);
			return "JOIN SUCCESS";
		} catch (Exception e) {
			e.printStackTrace();
			return "JOIN FAIL";
		}
	}

	// API 6. 회원가입 및 아이디 찾기 시, 네이버 메일 전송
	@PostMapping("/certmail")
	public String certmail(@RequestBody UserDto userDto) throws Exception {

		String code = mailSendService.sendMail(userDto.getUserEmail());
		System.out.println(code);

		return code;
	}

	// API 7. 프로필 이미지 변경
	// (1) FileUpload에 대한 전체적인 설명:
	// https://blog.naver.com/PostView.naver?blogId=xxhayoxx&logNo=221803768375&parentCategoryNo=&categoryNo=24&viewDate=&isShowPopularPosts=true&from=search
	// (2) (Backend) consumes에 대한 설명: https://superbono-2020.tistory.com/174
	// (3) (Frontend) formData에 대한 설명: https://jw910911.tistory.com/117
	@PutMapping(value = "/profileimg", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public void modifyImg(@RequestPart("userId") String userId, @RequestPart("profileImg") MultipartFile profileImg) {

//			System.out.println(userId); // 사용자 ID 디버깅
//			System.out.println(profileImg.getOriginalFilename()); // 파일 이름 디버깅

		userService.setProfileImg(userId, profileImg);

//			return "Profile Image Setting"; // Frontend 디버깅
	}

	// API 8. 프로필 이미지 출력
	// 이미지는 byte 배열 형태를 띄고 있기 때문에, return 값은 byte
	// (1) Profile 이미지 출력:
	// https://velog.io/@brince/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8%EB%A1%9C-%ED%94%84%EB%A1%9C%ED%95%84-%EC%9D%B4%EB%AF%B8%EC%A7%80%EB%A5%BC-%EB%B3%80%EA%B2%BD%ED%95%B4%EB%B3%B4%EC%9E%90-%ED%8C%8C%EC%9D%BC-%EC%9E%85%EC%B6%9C%EB%A0%A5
	// (2) Profile 이미지 출력: https://redbinalgorithm.tistory.com/382
	@GetMapping(value = "/profileimg/{userId}", produces = { MediaType.IMAGE_JPEG_VALUE })
	public @ResponseBody byte[] getImg(@PathVariable("userId") String userId) throws IOException {

		UserDto userDto = userService.getImg(userId);
		
//		System.out.println(userDto.getUserProfileimg()); // 디버깅

		// (1) InputStream으로 image 파일을 읽어오기
		InputStream inputStream = new FileInputStream("C:\\forj\\" + userDto.getUserProfileimg());
//		InputStream inputStream = new FileInputStream("/Users/hanyoon/Desktop/upload/" + userDto.getUserProfileimg());

		// (2) 읽어온 파일을 byte 형태로 변환
		byte[] imageByteArray = IOUtils.toByteArray(inputStream);
		inputStream.close();

		// 프론트에서 프로필 이미지를 byte 배열로 전달받아야 함
		return imageByteArray;
	}

	// API 9. 이메일을 입력받아, 사용자의 아이디 정보 받아오기
	@GetMapping(value = "/findid/{userEmail}")
	public UserDto findId(@PathVariable("userEmail") String userEmail) {

		return userService.findId(userEmail);
	}

	// API 10. 아이디와 이메일 정보를 입력받아, 사용자의 비밀번호 정보 받아오기
	@GetMapping(value = "/findpw/{userId}/{userEmail}")
	public UserDto findPw(@PathVariable("userId") String userId, @PathVariable("userEmail") String userEmail) {

		return userService.findPw(userId, userEmail);
	}

	// API 11. 사용자의 아이디와 이메일이 DB에 일치한 지 확인
	// 일치하다면 certmail을 동작하여 인증번호 전달
	// 일치하지 않다면, 에러메시지 전달
	@GetMapping(value = "/check/{userId}/{userEmail}")
	public String coincideIdEmail(@PathVariable("userId") String userId, @PathVariable("userEmail") String userEmail)
			throws Exception {

		UserDto userDto = userService.coincideIdEmail(userId, userEmail);

		// 아이디와 이메일 정보가 일치하는 사용자 정보가 없는 경우
		if (userDto == null) {
			return "error";
		}
		// 아이디와 이메일 정보가 일치하는 사용자 정보가 있는 경우
		else {
			return mailSendService.sendMail(userEmail);
		}
	}
	
	// API 12. 사용자 닉네임 변경 API
	@PutMapping(value = "/modify/nickname")
	public void modifyNickname(@RequestBody UserDto userDto) {
		
		userService.modifyNickname(userDto);
	}
	
	// API 13. 사용자 비밀번호 체크 API
	@PostMapping(value = "/check/pw")
	public String checkPw(@RequestBody UserDto userDto) {
		
		// DB에 아이디와 비밀번호가 일치하는 사용자가 있는 지 체크
		UserDto userInfo = userService.checkPw(userDto);
		
		// 존재하지 않는 사용자인 경우
		if (userInfo == null) {
			return "error";
		}
		else {
			return "success";
		}
	}
	
	// API 14. 사용자 비밀번호 변경 API
	@PutMapping(value = "/modify/pw")
	public void modifyPw(@RequestBody UserDto userDto) {
		
		userService.modifyPw(userDto);
	}
	
	// API 15. 사용자 이메일 변경 API
	@PutMapping(value = "/modify/email")
	public void modifyEmail(@RequestBody UserDto userDto) {
		
		userService.modifyEmail(userDto);
	}
}
