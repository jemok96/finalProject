package com.wegoing.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.wegoing.dao.AlarmDAO;
import com.wegoing.dao.EmitterDAO;
import com.wegoing.dto.AlarmDTO;
import com.wegoing.enumpackage.AlarmType;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AlarmService {
	private final EmitterDAO emitterDao;
	private final AlarmDAO alarmDao;
	    
	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60; // 타임아웃 
	
	public SseEmitter subscribe(String userId, String lastEventId) {
//		log.info("subscribe()");
		String emitterId = userId + "_" + System.currentTimeMillis();
		// sse 연결 요청에 응답하기 위해 sseEmitter 객체 생성
		SseEmitter emitter = emitterDao.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));
		
		// 네트워크 에러, 타임아웃 시 emitterId 삭제
		emitter.onCompletion(() -> emitterDao.deleteById(emitterId));
		emitter.onTimeout(() -> emitterDao.deleteById(emitterId));
		
		// 503 에러 방지를 위한 더미 이벤트 전송
		String eventId = userId + "_" + System.currentTimeMillis();
		sendAlarm(emitter, emitterId, "eventStream created.[userId=" + userId + "]");
		
		// lastEventId값이 헤더에 있는 경우 저장된 데이터 캐시에서 id값과 lastEventId값을 통해 유실된 데이터들만 다시 보내줌
		if (!lastEventId.isEmpty()) {
            Map<String, Object> events = emitterDao.findAllEventCacheStartWithId(String.valueOf(userId));
            events.entrySet().stream()
                  .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                  .forEach(entry -> sendAlarm(emitter, entry.getKey(), entry.getValue()));
        }

        return emitter;
	}
	
	private void sendAlarm(SseEmitter emitter, String emitterId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                                   .id(emitterId)
                                   .name("sse")
                                   .data(data));
        } catch (IOException exception) {
            emitterDao.deleteById(emitterId);
            throw new RuntimeException("연결 오류!");
        }
    }
	
	@Async
	public void send(String email, AlarmType type, String content, String url) {
//		log.info("send() >>> " + email + type + content + url);
	    AlarmDTO alarm = createAlarm(email, type, content, url);
	    alarmDao.save(alarm);
	    String receiverId = email;
	    String eventId = receiverId + "_" + System.currentTimeMillis();
	    // 사용자의 SseEmitter 모두 가져오기
	    Map<String, SseEmitter> emitters = emitterDao.findAllEmitterStartWithByMemberId(receiverId);
	    emitters.forEach(
	        (key, emitter) -> {
	            emitterDao.saveEventCache(key, alarm); // 데이터 캐시 저장 (유실된 데이터를 처리하기 위함)
	            sendAlarm(emitter, key, AlarmDTO.create(alarm));
	        }
	    );
	}
	
	private AlarmDTO createAlarm(String email, AlarmType type, String content, String url) {
		log.info("createAlarm() >>> " + email, type, content);
		AlarmDTO adto = AlarmDTO.builder()
		                .email(email)
		                .acontent(content)
		                .url(url)
		                .type(AlarmType.clubInvitaion.type())
		                .checked(false) // 현재 읽음상태
		                .build();
        
        return adto;
    }

	public List<AlarmDTO> findAllAlarms(String email) {
		List<AlarmDTO> alarmList = alarmDao.findAllByEmail(email);
		return alarmList;
	}

	public void readAlarm(long alarmId) {
		alarmDao.readAlarm(alarmId);
		
	}

	public void removeAlarm(long ano) {
		alarmDao.deleteAlarm(ano);
	}
}
