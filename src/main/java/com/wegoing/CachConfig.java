package com.wegoing;

import java.util.Objects;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.config.CacheConfiguration;

@EnableCaching
@Configuration
public class CachConfig{
	
    @Bean
    public EhCacheManagerFactoryBean cacheManagerFactoryBean(){
    	return new EhCacheManagerFactoryBean();
    }
    
    // EhcacheManager 등록
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(){
    	// 캐시 설정
        CacheConfiguration conf = new CacheConfiguration()
        .eternal(false)
        .timeToIdleSeconds(0)
        .timeToLiveSeconds(60)
        .maxEntriesLocalHeap(0)
        .memoryStoreEvictionPolicy("LRU")
        .name("layoutCaching");
        
        // 설정을 가지고 캐시 생성
        net.sf.ehcache.Cache layoutCache = new net.sf.ehcache.Cache(conf);
        
        // 캐시 팩토리에 생성한 eh캐시를 추가
        Objects.requireNonNull(cacheManagerFactoryBean().getObject()).addCache(layoutCache);
        
        
        return new EhCacheCacheManager(Objects.requireNonNull(cacheManagerFactoryBean().getObject()));
    }

    
}