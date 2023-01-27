package com.wegoing.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
