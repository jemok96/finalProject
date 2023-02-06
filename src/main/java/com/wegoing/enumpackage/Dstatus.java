package com.wegoing.enumpackage;

public enum Dstatus {
	start("발의됨"),ongoing("진행중"),stop("일시중지"),done("완료");
	private final String dstatus;
	Dstatus(String dstatus) {
		this.dstatus = dstatus;
	}
	public String dstatus() {
		return dstatus;
	}
}
