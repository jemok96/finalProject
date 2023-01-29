package com.wegoing.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ClubMemberDTO {
	private long cno; 
	private String cstatus;
	private String crank; 
	private long clno;
	private String email; 
	
	@Builder
    public ClubMemberDTO (long cno, String cstatus, String crank, long clno, String email){
		this.clno = cno;
        this.cstatus = cstatus;
        this.crank = crank;
        this.clno =  clno; 
        this.email = email;
    }
}	
