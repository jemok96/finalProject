package com.wegoing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.AlarmDTO;
import com.wegoing.dto.MemberDTO;

@Repository
@Mapper
public interface AlarmDAO {
	long countByMemberEmail(MemberDTO mdto);
	List<AlarmDTO> findAllByEmail(String email);
	void save(AlarmDTO adto);
	void readAlarm(long alarmId);
	void deleteAlarm(long ano);
}
