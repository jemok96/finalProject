package com.wegoing.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wegoing.dto.MemberDTO;
import com.wegoing.service.MemberService;
import com.wegoing.service.TodoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TodoController {

	@Autowired
	TodoService todoservice;
	
	@Autowired
	MemberService memberservice;
	
	@PostMapping("club/{clno}/todo/deletetodo")
	@ResponseBody
	public List<MemberDTO> deleteOne(@PathVariable("clno")String clno,
									@RequestParam("tno")int tno,
									@RequestParam("dno")int dno){
		log.info("<<<<<<<<<<<<<<<<<<<<deletetodo " + dno);
		todoservice.remove(tno);
		List<MemberDTO> todoList = todoservice.selectdno(dno);
		
		
		return todoList;
	}
	// modifyForm에서 삭제
	@PostMapping("club/{clno}/todo/modifydelete")
	@ResponseBody
	public Map<String,List<MemberDTO>> deleteModify(@PathVariable("clno")int clno,
													@RequestParam("tno")int tno,
													@RequestParam("dno")int dno){
		log.info("<<<<<<<<<<<<<<<<<<<<deletetodo " + dno);
		todoservice.remove(tno);
		List<MemberDTO> todoList = todoservice.selectdno(dno);
		List<MemberDTO> cmlist = memberservice.getMembersInfo(clno);
		
		Map<String, List<MemberDTO>> maplist = new HashMap<String, List<MemberDTO>>();
		maplist.put("todoList", todoList);
		maplist.put("cmlist", cmlist);
		
		
		return maplist;
	}
	// modifyForm에서 할일 작성
	@PostMapping("club/{clno}/todo/modifytodo")
	@ResponseBody
	public Map<String,List<MemberDTO>> modifyOne(@PathVariable("clno")int clno,
									@RequestParam("ajson")String ajson,
									@RequestParam("dno")int dno){
		log.info("<<<<<<<<<<<<<<<<<<<<deletetodo " + dno);
		
		todoservice.modifyTodoWrite(dno, ajson);
		List<MemberDTO> todoList = todoservice.selectdno(dno);
		List<MemberDTO> cmlist = memberservice.getMembersInfo(clno);
		
		Map<String, List<MemberDTO>> maplist = new HashMap<String, List<MemberDTO>>();
		maplist.put("todoList", todoList);
		maplist.put("cmlist", cmlist);
		
		
		return maplist;
	}
	
	// 담당자 변경
	@PostMapping("club/{clno}/todo/modifymanager")
	@ResponseBody
	public Map<String,List<MemberDTO>> modifyManager(@PathVariable("clno")int clno,
			@RequestParam("cno")int cno,
			@RequestParam("dno")int dno,
			@RequestParam("tno")int tno){
		
		todoservice.updateManager(cno, tno);
		
		List<MemberDTO> todoList = todoservice.selectdno(dno);
		
		List<MemberDTO> cmlist = memberservice.getMembersInfo(clno);
		
		Map<String, List<MemberDTO>> maplist = new HashMap<String, List<MemberDTO>>();
		maplist.put("todoList", todoList);
		maplist.put("cmlist", cmlist);
		
		
		
		return maplist;
	}
	
	// 진행상태 변경
	@PostMapping("club/{clno}/todo/modifystatus")
	@ResponseBody
	public Map<String,List<MemberDTO>> modifyStatus(@PathVariable("clno")int clno,
			@RequestParam("tstatus")String tstatus,
			@RequestParam("dno")int dno,
			@RequestParam("tno")int tno){
		
		todoservice.updateStatus(tno, tstatus);
		
		List<MemberDTO> todoList = todoservice.selectdno(dno);
		
		List<MemberDTO> cmlist = memberservice.getMembersInfo(clno);
		
		Map<String, List<MemberDTO>> maplist = new HashMap<String, List<MemberDTO>>();
		maplist.put("todoList", todoList);
		maplist.put("cmlist", cmlist);
		
		
		
		return maplist;
	}
	
	// 할일 내용 변경
	@PostMapping("club/{clno}/todo/modifytname")
	@ResponseBody
	public Map<String,List<MemberDTO>> modifyTname(@PathVariable("clno")int clno,
			@RequestParam("tname")String tname,
			@RequestParam("dno")int dno,
			@RequestParam("tno")int tno){
		log.info("tname" + tname);
		log.info("dno" + dno);
		log.info("tno" + tno);
		
		todoservice.updateTname(tno, tname);
		log.info("updateTname");
		List<MemberDTO> todoList = todoservice.selectdno(dno);
		
		List<MemberDTO> cmlist = memberservice.getMembersInfo(clno);
		
		Map<String, List<MemberDTO>> maplist = new HashMap<String, List<MemberDTO>>();
		maplist.put("todoList", todoList);
		maplist.put("cmlist", cmlist);
		
		
		
		return maplist;
	}
}
