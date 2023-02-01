package com.wegoing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDTO {
	private int dno;
	private String dname;
	private String dcontent;
	private String dstatus;
	private String dwriter;
	private String reg_dt;
	private String up_dt;
	private int clno;
}
