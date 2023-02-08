package com.wegoing.enumpackage;

public enum ClubMemberStatus {
	accept("y"), wait("n");
	private final String cstatus;
	
	ClubMemberStatus(String cstatus) {
		this.cstatus = cstatus;
	}
	
	public String cstatus() {
		return cstatus;
	}
}
