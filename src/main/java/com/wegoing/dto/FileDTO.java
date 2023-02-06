package com.wegoing.dto;

import java.util.Date;
import java.util.Objects;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO {
	private Integer fno;
	private String fname;
	private String uuid;
	private String fpath;
	private Date reg_dt;
	private Integer clno;
	
	public FileDTO() {}

	@Builder
	public FileDTO(Integer fno, String fname, String uuid, String fpath, Date reg_dt, Integer clno) {
		super();
		this.fno = fno;
		this.fname = fname;
		this.uuid = uuid;
		this.fpath = fpath;
		this.reg_dt = reg_dt;
		this.clno = clno;
	}
	
	@Override
	public String toString() {
		return "FileDTO [fno=" + fno + ", fname=" + fname + ", uuid=" + uuid + ", fpath=" + fpath + ", reg_dt=" + reg_dt
				+ ", clno=" + clno + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(clno, fname, fno, fpath, reg_dt, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileDTO other = (FileDTO) obj;
		return Objects.equals(clno, other.clno) && Objects.equals(fname, other.fname) && Objects.equals(fno, other.fno)
				&& Objects.equals(fpath, other.fpath) && Objects.equals(reg_dt, other.reg_dt)
				&& Objects.equals(uuid, other.uuid);
	}
	
	
}
