package com.wegoing.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wegoing.dao.BoardDao;
import com.wegoing.dto.BoardDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {
	
	private final BoardDao dao;
	
	public List<BoardDTO> selectCategory(int no){
		return dao.selectCategory(no);
	}
	public List<BoardDTO> selectboard(int no){
		return dao.selectboard(no);
	}
	public List<BoardDTO> selectPage(Map map){
		return dao.selectPage(map);
	}
	public BoardDTO selectOne(int no){
		return dao.selectOne(no);
	}
	public int insert(BoardDTO dto) {
		return dao.insert(dto);
	}
	public int deleteCategory(int cateno) {
		return dao.deleteCategory(cateno);
	}
	public int deleteAll() {
		return dao.deleteAll();
	}
	public int countBoard(int cateno) {
		return dao.countBoard(cateno);
	}
	public int update(BoardDTO dto) {
		return dao.update(dto);
	}
	public int updateHit(int bno) {
		return dao.updateHit(bno);
	}
	public int deleteOne(int bno) {
		return dao.deleteOne(bno);
	}
}
