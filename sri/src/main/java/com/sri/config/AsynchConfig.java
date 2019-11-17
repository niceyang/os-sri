package com.sri.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/*
 * Enable the multiple thread supporting for the RESTful API supporting
 * 
 * */
@Configuration
public class AsynchConfig {
	
	@Bean("threadPoolTaskExecutor")
	public TaskExecutor getAsyncExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(20);
	    executor.setMaxPoolSize(1000);
	    executor.setWaitForTasksToCompleteOnShutdown(true);
	    executor.setThreadNamePrefix("Async-");
	    return executor;
	}
}