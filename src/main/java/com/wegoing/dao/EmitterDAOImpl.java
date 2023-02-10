package com.wegoing.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lombok.NoArgsConstructor;

@Repository
@NoArgsConstructor
public class EmitterDAOImpl implements EmitterDAO {
	private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
	private final Map<String, Object> eventCache = new ConcurrentHashMap<>();
	
	@Override
	public SseEmitter save(String id, SseEmitter sseEmitter) {
		emitters.put(id, sseEmitter);
       return sseEmitter;
	}
	
	@Override
	public void saveEventCache(String id, Object event) {
       eventCache.put(id, event);
   }
	
	@Override
	public void deleteById(String id) {
		emitters.remove(id);
	}
	
	@Override
	public Map<String, SseEmitter> findAllEmitterStartWithByMemberId(String id) {
       return emitters.entrySet().stream()
               .filter(entry -> entry.getKey().startsWith(id))
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
   }
	
	@Override
	public Map<String, Object> findAllEventCacheStartWithId(String id) {
		return eventCache.entrySet().stream()
               .filter(entry -> entry.getKey().startsWith(id))
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

}
