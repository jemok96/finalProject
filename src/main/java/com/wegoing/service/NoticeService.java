package com.wegoing.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wegoing.dao.NoticeDao;
import com.wegoing.dto.NoticeDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NoticeService {
	private final NoticeDao dao;
	public NoticeService(NoticeDao dao) {
		this.dao = dao;
	}
	
	@Cacheable("layoutCaching")
	public List<NoticeDTO> getNoticeList(Map<String, Integer>  map){
		return dao.getNoticeList(map);
	}
	public NoticeDTO getNoticeOne(int notino) throws Exception{
		return dao.getNoticeOne(notino);
	}
	public int getNoticeCount() {
		return dao.getNoticeCount();
	}
	public int writeNotice(NoticeDTO notice) {
		return dao.writeNotice(notice);
	}
	public int updateHits(int notino) {
		return dao.updateHits(notino);
	}
	public int noticeUpdate(NoticeDTO notice) {
		
		return dao.noticeUpdate(notice);
	}
	public int noticeDelete(int notino) {
		
		return dao.noticeDelete(notino);
	}
}
