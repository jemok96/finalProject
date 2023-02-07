package com.wegoing.service;

import java.util.List;

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
	
	public List<NoticeDTO> getNoticeList(){
		return dao.getNoticeList();
	}
	public int getNoticeCount() {
		return dao.getNoticeCount();
	}
	public int writeNotice(NoticeDTO notice) {
		return dao.writeNotice(notice);
	}

}
