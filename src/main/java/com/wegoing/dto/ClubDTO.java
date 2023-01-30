package com.wegoing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ClubDTO {
	private Long clno;
	private String clname;
	private String clexplain;
	
	@Builder
	public ClubDTO (long clno, String clname, String clexpalin) {
        this.clno = clno;
        this.clname = clname;
        this.clexplain =  clexplain; 
    }
}
