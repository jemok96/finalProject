package com.wegoing.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDTO {
	private Integer bno;
	private String email;
	private Integer cateno;
	private String btitle;
	private String bcontent;
	private Integer bhits;
	private Date reg_dt;
	private Date up_dt;
}
