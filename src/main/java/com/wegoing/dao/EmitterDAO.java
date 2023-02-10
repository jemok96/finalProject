package com.wegoing.dao;

import java.util.Map;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EmitterDAO {
	SseEmitter save(String id, SseEmitter sseEmitter);
	void saveEventCache(String id, Object event);
	void deleteById(String id);
	Map<String, SseEmitter> findAllEmitterStartWithByMemberId(String id);
	Map<String, Object> findAllEventCacheStartWithId(String id);
}
