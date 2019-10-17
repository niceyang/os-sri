package com.sri.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sri.service.CacheService;
import com.sri.service.DataService;
import com.sri.service.imple.ResponseService;
import com.sri.util.model.RequestBody;
import com.sri.util.model.ResponseBody;

/**
 * 
 * The REST API layer, injected the service, providing the connections for the frontend
 * 
 */
@RestController
@RequestMapping("/api/dsr")
public class DataRestAPI {
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private ResponseService responseService;
	
	//reading all logs
	@PostMapping("{id}")
	public ResponseBody access(RequestBody req) {
		String uuid = cacheService.createJob();
		return null;
	}
	
	@DeleteMapping("{id}")
	public ResponseBody erase(RequestBody req) {
		return null;
	}
	
	@PostMapping("{id}")
	public ResponseBody portable(RequestBody req) {
		return null;
	}
	
	@PutMapping("{id}")
	public ResponseBody edit(RequestBody req) {
		return null;
	}
}










