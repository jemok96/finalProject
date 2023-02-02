package com.wegoing.controller;



public enum Category {
	hobby(1),free(2),pet(3),career(4);
	private final Integer cateno;

	Category(Integer cateno) {
		this.cateno = cateno;
	}
	public Integer cateno() {
		return cateno;
	}

}
