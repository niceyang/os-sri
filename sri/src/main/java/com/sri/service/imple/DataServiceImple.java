package com.sri.service.imple;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sri.entity.Cache;
import com.sri.entity.Data;
import com.sri.entity.User;
import com.sri.reporsitory.DataRepository;
import com.sri.service.CacheService;
import com.sri.service.DataService;

/**
 * Data persistence business logic layer
 * The interface-implementation architechture is required by the Spring framework (the multiple implementation is allowed)
 * The service implementations implement the actual methods and will be injected into required components(like other services or restAPI layer)
 * <p>
 * There are many common method between the services(like CRUD), so the common part is define in a BaseImple
 * This service implements get the common methods by extends the BaseService
 * The E is the Entity type of the service
 */
@Service
public class DataServiceImple extends BaseImple<Data> implements DataService {

	@Autowired
	private DataRepository dataRepository;
	
	@Autowired
	private CacheService cacheService;

	@PostConstruct
	public void initParent() {
		super.repository = dataRepository;
	}

	@Override
	public List<Data> findByUserId(int id) {
		return dataRepository.findByUserId(id);
	}

	@Override
	public List<Data> findByUserIdAndType(int id, String type) {
		return dataRepository.findByUserIdAndType(id, type);
	}

	@Async
	public void doAccessJob(String uuid, User user) {
		Cache cache = cacheService.findJob(uuid);
		List<Data> data = findByUserId(user.getId());
		ObjectMapper mapper = new ObjectMapper();
		String jdata = null;
		try {
			jdata = mapper.writeValueAsString(data);
			System.out.println(jdata);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			cache.setData("Faild");
			cache.setFinished(1);
			cacheService.update(cache);
			return;
		}
		
		cache.setData(jdata);
		cache.setFinished(1);
		cacheService.update(cache);
	}

}
