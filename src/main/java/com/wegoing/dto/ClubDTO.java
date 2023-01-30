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
	private String cre_dt;
	
	@Builder
	public ClubDTO (long clno, String clname, String clexplain, String cre_dt) {
        this.clno = clno;
        this.clname = clname;
        this.clexplain = clexplain;
        this.cre_dt = cre_dt;
    }
}
