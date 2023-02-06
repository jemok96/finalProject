package com.wegoing.enumpackage;

public enum Message{
	MSG("로그인 해주세요"),YES("YES"),NO("NO"),NICK("nick"),
	DEL_OK("success"), DELETE("삭제되었습니다."),
	WRITE_OK("success") ,WRITE("등록되었습니다");

	private final String message;
	Message(String message) {
		this.message = message;
	}
	public String message() {
		return message;
	}
	
}
