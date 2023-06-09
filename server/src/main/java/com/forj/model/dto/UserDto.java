package com.forj.model.dto;

public class UserDto {

	private String userId;
	private String userPw;
	private String userName;
	private String userNickname;
	private String userGender;
	private String userPhone;
	private String userEmail;
	private int userPlancnt;
	private String userProfileimg;
	private String userRefreshToken;

	public UserDto() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getUserPlancnt() {
		return userPlancnt;
	}

	public void setUserPlancnt(int userPlancnt) {
		this.userPlancnt = userPlancnt;
	}

	public String getUserProfileimg() {
		return userProfileimg;
	}

	public void setUserProfileimg(String userProfileimg) {
		this.userProfileimg = userProfileimg;
	}

	public String getUserRefreshToken() {
		return userRefreshToken;
	}

	public void setUserRefreshToken(String userRefreshToken) {
		this.userRefreshToken = userRefreshToken;
	}

}
