package com.wegoing.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.wegoing.dto.BoardDTO;
import com.wegoing.dto.SearchCondition;


@Repository
@Mapper
public interface BoardDao {
	 List<BoardDTO> selectCategory(int no);
	 List<BoardDTO> selectboard(int no);
	 List<BoardDTO> selectPage(Map map);
	 BoardDTO selectOne(int no);
	 int insert(BoardDTO dto);
	 int deleteCategory(int cateno);
	 int deleteAll();
	 int deleteOne(int bno);
	 int countBoard(int cateno);
	 int update(BoardDTO dto);
	 int updateHit(int bno);
	 BoardDTO selectTest(int bno);
	 List<BoardDTO> searchSelectPage(SearchCondition sc);
	 int searchResultCnt(SearchCondition sc);
}
