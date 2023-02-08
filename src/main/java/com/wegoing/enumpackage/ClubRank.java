package com.wegoing.enumpackage;

public enum ClubRank {
	host("host"), guest("guest");
	private final String crank;
	
	ClubRank(String crank) {
		this.crank = crank;
	}
	
	public String crank() {
		return crank;
	}
	

}
