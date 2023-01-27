package com.wegoing.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.BoardDTO;
import com.wegoing.dto.MemberDTO;


@Repository
@Mapper
public interface BoardDao {
	 List<BoardDTO> selectCategory(int no);
	 List<BoardDTO> selectboard(int no);
	 int insert(BoardDTO dto);
	 int deleteCategory(int cateno);
	 int deleteAll();
	 int countBoard(int cateno);
}
