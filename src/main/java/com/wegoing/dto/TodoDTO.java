package com.wegoing.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {
	private int tno;
	private String tname;
	private String tstatus;
	private String reg_dt;
	private String up_dt;
	private String tstart_dt;
	private String tend_dt;
	private int cno;
	private int dno;
	
}
