package com.wegoing.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmDTO { 
	private long ano;
	private String acontent;
	private String email;
	private String url;
	private String type;
	private String reg_dt;
	private Boolean checked;
	
	public AlarmDTO() {
	}

	@Builder
	public AlarmDTO(long ano, String acontent, String email, 
					String url, String type, String reg_dt, Boolean checked) {
		this.ano = ano;
		this.acontent = acontent;
		this.url = url;
		this.email = email;
		this.checked = false;
		this.type = type;
		this.reg_dt = reg_dt;
	}

	public static AlarmDTO create(AlarmDTO alarm) {
		 return new AlarmDTO(alarm.getAno(),alarm.getAcontent(), alarm.getUrl(),
				 alarm.getEmail(), alarm.getType(), alarm.getReg_dt(), alarm.getChecked());
	}
}
