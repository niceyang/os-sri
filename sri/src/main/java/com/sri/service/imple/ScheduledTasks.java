package com.sri.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sri.reporsitory.CacheRepository;

/**
 * 
 * References:
 * https://spring.io/guides/gs/scheduling-tasks/
 * 
 * */
@Component
public class ScheduledTasks {
	
	@Autowired
	private CacheRepository cacheRepository;
	
	static final long WEEK = 1000 * 60 * 60 * 24 * 7;
	
    @Scheduled(fixedRate = WEEK) // 1 week
    public void removingExpiredContacts() {
    	// Clear the jobs time-out
    }
    
    
}
