package com.wegoing.dto;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.wegoing.enumpackage.AlarmType;
import com.wegoing.service.AlarmService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class AddClubMemberEventListener {
	AlarmService alarmservice;
	
	@EventListener
	public void handleAddClubMemberEvent(AddClubMemberEvent event) {
		ClubMemberDTO member = event.getClubmember();
		ClubDTO club = event.getClub();
		String email = member.getEmail();
		String url = "/club/" + club.getClno();
		String content = "[" + club.getClname() + "] 로부터 초대를 받았습니다.";
		log.info("add Member : " + member.getClno());
		
		alarmservice.send(email, AlarmType.clubInvitaion, content, url);
	}
}
