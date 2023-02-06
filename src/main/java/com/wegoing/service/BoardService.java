package com.wegoing.service;


import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wegoing.dao.BoardDao;
import com.wegoing.dto.BoardDTO;
import com.wegoing.dto.SearchCondition;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardService {
	
	private final BoardDao dao;
	
	public BoardService(BoardDao dao) {
		this.dao = dao;
	}
	
	@Cacheable(value = "board")
	@CachePut(value="board")
	public List<BoardDTO> selectCategory(int no){
//		접속 후 한번 찍히고 다음부터 안찍힘
		List<BoardDTO> dto = dao.selectCategory(no);
		for(int i=0; i<dto.size(); i++) {
			log.info("{}",dto.get(i));;
		}
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
	public BoardDTO selectTest(int bno) {
		return dao.selectTest(bno);
	}
	public List<BoardDTO> searchSelectPage(SearchCondition sc){
		return dao.searchSelectPage(sc);
	}
	public  int searchResultCnt(SearchCondition sc) {
		 return dao.searchResultCnt(sc);
	 }
}
