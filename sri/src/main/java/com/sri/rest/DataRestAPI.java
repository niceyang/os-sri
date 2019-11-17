package com.sri.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sri.entity.Cache;
import com.sri.exception.InvalidException;
import com.sri.exception.NotFoundException;
import com.sri.service.CacheService;
import com.sri.service.DataService;
import com.sri.service.UserService;
import com.sri.service.imple.ResponseService;
import com.sri.util.Constant;
import com.sri.util.model.RequestModel;
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
	private UserService userService;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private ResponseService responseService;
	
	// API for creating data access job
	@PostMapping("access")
	public ResponseBody access(@RequestBody RequestModel req) {
		String uuid = cacheService.createJob();
		Integer id = userService.queryUser(req.getPiType(), req.getPiData());
		if (id == null) {
			throw new InvalidException("PI TYPE UNSUPPORTED OR USER NOT FOUND");
		}
		
		dataService.doAccessJob(uuid, id, req.getTopology());
		
		return responseService.buildResponse(uuid, Constant.RESPONSE_TYPE_CALLBACK_ID, uuid);
	}
	
	// API for tracking job status
	@GetMapping("access/{id}")
	public ResponseBody pollAccess(@PathVariable String id) {
		Cache cache = cacheService.findJob(id);
		if (cache == null) {
			throw new NotFoundException();
		}
		if (cache.getFinished() == 0) {
			return responseService.buildResponse(cache.getUid(), Constant.RESPONSE_TYPE_STATUS, "Processing");
		} else {
			String data = cache.getData();
			cacheService.deleteById(cache.getId());
			return responseService.buildResponse(cache.getUid(), Constant.RESPONSE_TYPE_DATA, data);
		}
	}
	
	// API for erasure job
	@DeleteMapping("erase")
	public ResponseBody erase(@RequestBody RequestModel req) {
		Integer id = userService.queryUser(req.getPiType(), req.getPiData());
		if (id == null) {
			throw new InvalidException("PI TYPE UNSUPPORTED OR USER NOT FOUND");
		}
		
		dataService.doEraseJob(id);
		return responseService.buildResponse(null, Constant.RESPONSE_TYPE_STATUS, "OK");
	}
	
	// API for delete -> placeholder
	@DeleteMapping("delete")
	public ResponseBody delete(@RequestBody RequestModel req) {
		return responseService.buildResponse(null, Constant.RESPONSE_TYPE_STATUS, "OK");
	}
	
	// API for portable -> placeholder
	@PostMapping("portable")
	public ResponseBody portable(@RequestBody RequestModel req) {
		return responseService.buildResponse(null, Constant.RESPONSE_TYPE_STATUS, "OK");
	}
	
	// API for edit -> placeholder
	@PutMapping("edit")
	public ResponseBody edit(@RequestBody RequestModel req) {
		return responseService.buildResponse(null, Constant.RESPONSE_TYPE_STATUS, "OK");
	}
}










