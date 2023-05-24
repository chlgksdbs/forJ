package com.forj.model.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.forj.model.dto.UserDto;
import com.forj.model.repository.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	// 로그인
	public UserDto login(UserDto userDto) {

		// 입력된 id나 pw 정보가 없는 경우, null을 리턴
		if (userDto.getUserId() == null || userDto.getUserPw() == null)
			return null;

		return userMapper.login(userDto);
	}

	// 로그아웃
	public void logout(String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("refreshToken", null);

		userMapper.logout(map);
	}

	// 회원 정보 조회
	public UserDto userInfo(String userId) {

		return userMapper.userInfo(userId);
	}

	// refresh token 저장
	public void saveRefreshToken(String userId, String refreshToken) {

		// Mapper에 2개 이상의 매개변수를 보내지 말고, Map 형태로 보낼 것
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("refreshToken", refreshToken);

		userMapper.saveRefreshToken(map);
	}

	// refresh token 조회
	public String getRefreshToken(String userId) {

		return userMapper.getRefreshToken(userId);
	}

	// 회원가입
	public void join(UserDto userDto) {

		userMapper.join(userDto);
	}

	// 프로필 이미지 변경
	public void setProfileImg(String userId, MultipartFile profileImg) {

		// (1) 파일을 저장할 폴더 설정 -> 서버에 배포 시, 경로 변경 필요!!!!!
//		String dirPath = "C:\\forj\\";
		String dirPath = "/Users/hanyoon/Desktop/upload/";
		File dir = new File(dirPath);

		// 만약, 폴더가 없는 경우 생성
		if (!dir.exists()) {
			dir.mkdir();
		}

		// (2) 파일 이름 암호화(고유 식별) 및 위에서 설정한 폴더에 파일 저장
		UUID uuid = UUID.randomUUID(); // 범용 고유 식별자 생성
		String uuidFileName = uuid + "_" + profileImg.getOriginalFilename();

		// nio 객체: 사진, 동영상, 스트리밍 모두 지원
		Path filePath = Paths.get(dirPath + uuidFileName);
		try {
			Files.write(filePath, profileImg.getBytes());
			// 옵션은 application.properties에서 걸기 때문에 여기서 걸지 않음
		} catch (IOException e) {
			e.printStackTrace();
		}

		// (3) 파일 DB에 저장
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("profileImg", uuidFileName);

		userMapper.setProfileImg(map);
	}

	// 프로필 이미지 출력
	public UserDto getImg(String userId) {

		return userMapper.selectProfileImg(userId);
	}

	// 이메일 정보를 가지고, 사용자 아이디 찾기
	public UserDto findId(String userEmail) {

		return userMapper.selectUserId(userEmail);
	}

	// 아이디와 이메일 정보를 가지고, 사용자의 비밀번호 찾기
	public UserDto findPw(String userId, String userEmail) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userEmail", userEmail);

		return userMapper.selectUserPw(map);
	}

	// 아이디와 이메일 정보를 가지고, 사용자 찾기
	public UserDto coincideIdEmail(String userId, String userEmail) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userEmail", userEmail);

		return userMapper.checkUserAuth(map);
	}

}