package com.wegoing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PartnerDTO {
	private long pno; 
	private String pemail;
	private String padd_dt;
	private String pstatus; 
	private String email; 
	
	@Builder
	public PartnerDTO(long pno, String pemail, String padd_dt, String pstatus, String email ) {
		this.pno = pno;
		this.pemail = pemail;
		this.padd_dt =  padd_dt;
		this.pstatus = pstatus;
		this.email = email; 
	}
}
