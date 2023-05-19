package com.forj.model.dto;

public class HeartDto {

	private int heartId;
	private String userId;
	private int boardId;
	private int heartStatus;

	public HeartDto() {
	}

	public int getHeartId() {
		return heartId;
	}

	public void setHeartId(int heartId) {
		this.heartId = heartId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getHeartStatus() {
		return heartStatus;
	}

	public void setHeartStatus(int heartStatus) {
		this.heartStatus = heartStatus;
	}

}
