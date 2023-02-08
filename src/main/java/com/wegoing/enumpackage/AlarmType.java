package com.wegoing.enumpackage;

public enum AlarmType {
	clubInvitaion("club_invitation");
	private final String type;
	
	AlarmType(String type) {
		this.type = type;
	}
	
	public String type() {
		return type;
	}
}
