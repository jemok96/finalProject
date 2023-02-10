package com.wegoing.service;

import java.util.ArrayList;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wegoing.dao.TodoDAO;
import com.wegoing.dto.MemberDTO;
import com.wegoing.dto.TodoDTO;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class TodoService {
	
	@Autowired
	TodoDAO dao;
	
	
	public List<MemberDTO> idTodoList(String email){
		return dao.todoIdSelectOne(email);
	}
	
	
	
	
	public void write(int dno, String ajson) {
		if(ajson != null) {

			JSONParser jsonParser = new JSONParser();
			try {
				JSONArray jsonarray = (JSONArray) jsonParser.parse(ajson);
				log.info("<<<<<<<<<<<<<<JSONArray" + jsonarray);
				
				
				for(Object obj : jsonarray) {
					
					JSONObject jsonObj = (JSONObject) obj;
					
					String start_dt = (String) jsonObj.get("start_dt");
					String end_dt = (String) jsonObj.get("end_dt");
					String tstatus = (String) jsonObj.get("tstatus");
					String tname = (String) jsonObj.get("tname");
					String strcno = (String)jsonObj.get("cno");
				
					int cno = Integer.parseInt(strcno);
					
					
					
					TodoDTO dto = TodoDTO.builder()
										.tname(tname)
										.tstart_dt(start_dt)
										.tend_dt(end_dt)
										.tstatus(tstatus)
										.cno(cno)
										.dno(dno)
										.build();
					
					log.info("<<<<<<<<<<<dto" + dto);
					
					dao.insertOne(dto);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public List<MemberDTO> selectdno(int dno) {
		List<MemberDTO> list = dao.dnoSelectOne(dno);
		return list;
	}
	
	public void remove(int tno) {
		dao.deleteOne(tno);
	}
	// 담당자 변경
	public void updateManager(int cno, int tno) {
		TodoDTO dto = TodoDTO.builder()
							 .cno(cno)
							 .tno(tno)
							 .build();
		dao.modifyManager(dto);
		
	}
	public void updateStatus(int tno, String tstatus) {
		TodoDTO dto = TodoDTO.builder()
							 .tno(tno)
							 .tstatus(tstatus)
							 .build();
		dao.modifyStatus(dto);
		
	}
	
	public void updateTname(int tno, String tname) {
		TodoDTO dto = TodoDTO.builder()
							 .tno(tno)
							 .tname(tname)
							 .build();
		dao.modifyTname(dto);
		
	}
	
	public void modifyTodoWrite(int dno, String ajson) {
		if(ajson != null) {

			JSONParser jsonParser = new JSONParser();
			try {
				JSONObject jsonObject = (JSONObject) jsonParser.parse(ajson);
				log.info("<<<<<<<<<<<<<<JSONArray" + jsonObject);
				
				String start_dt = (String) jsonObject.get("start_dt");
				String end_dt = (String) jsonObject.get("end_dt");
				String tstatus = (String) jsonObject.get("tstatus");
				String tname = (String) jsonObject.get("tname");
				String strcno = (String)jsonObject.get("cno");
				int cno = Integer.parseInt(strcno);
				
				TodoDTO dto = TodoDTO.builder()
									 .tname(tname)
									 .tstart_dt(start_dt)
									 .tend_dt(end_dt)
									 .tstatus(tstatus)
									 .cno(cno)
									 .dno(dno)
									 .build();
				dao.insertOne(dto);
				
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


	public void removeDno(int dno) {
		dao.deleteDno(dno);
		log.info("removeDno");
	}

}
