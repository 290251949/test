package com.entity;

public class Message {
	private String from;
	private String to;
	private String msg;
	private int isRead;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return "Message [from=" + from + ", to=" + to + ", msg=" + msg + ", isRead=" + isRead + "]";
	}
	
	

}
