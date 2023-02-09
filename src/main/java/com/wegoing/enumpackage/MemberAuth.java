package com.wegoing.enumpackage;

public enum MemberAuth {
	user("ROLE_USER"), admin("ROLE_ADMIN");
	private final String auth;
	
	MemberAuth(String auth) {
		this.auth = auth;
	}
	
	public String auth() {
		return auth;
	}
}
