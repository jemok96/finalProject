package com.wegoing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.MemberDTO;
import com.wegoing.dto.TodoDTO;

@Repository
@Mapper
public interface TodoDAO {
	public void insertOne(TodoDTO dto);
	public List<MemberDTO> dnoSelectOne(int dno);
	List<MemberDTO> todoIdSelectOne(String email);
	void deleteOne(int tno);
	void modifyManager(TodoDTO dto);
	void modifyStatus(TodoDTO dto);
	void modifyTname(TodoDTO dto);
	void deleteDno(int dno);
	List<TodoDTO> selectAll();
}
