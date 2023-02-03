package com.wegoing.enumpackage;

public enum Message{
	MSG("로그인 해주세요"),YES("YES"),NO("NO"),NICK("nick");

	private final String message;
	Message(String message) {
		this.message = message;
	}
	public String message() {
		return message;
	}
	
}
