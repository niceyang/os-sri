package com.sri.service.imple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.sri.util.model.Category;
import com.sri.util.model.TopologyModel;

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

	@Async
	public void doAccessJob(String uuid, User user, TopologyModel topo) {
		Cache cache = cacheService.findJob(uuid);
		List<Data> data = findByUserId(user.getId());
		ObjectMapper mapper = new ObjectMapper();
		String jdata = null;
		try {
			jdata = mapper.writeValueAsString(data);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			cache.setData("Faild");
			cache.setFinished(1);
			cacheService.update(cache);
			return;
		}
		
		// To simulate the time-consuming processing
		try {
			Thread.sleep(1000);
			System.out.println(jdata);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		cache.setData(jdata);
		cache.setFinished(1);
		cacheService.update(cache);
	}
	
	// Two layer only
	private Map<String, Map<String, List<Data>>> proces(List<Data> dataList, TopologyModel topo) {
		Map<String, Map<String, List<Data>>> res = new HashMap<>();
		Map<String, Set<String>> topoMap = new HashMap<>();
		
		for (Category cate : topo.getCategories()) {
			topoMap.put(cate.getVal(), new HashSet<>());
			if (cate.getSubCategories() != null) {
				for (Category sub : cate.getSubCategories()) {
					topoMap.get(cate.getVal()).add(sub.getVal());
				}
			}
		}
		
		// build res
		for (Category cate : topo.getCategories()) {
			res.put(cate.getVal(), new HashMap<>());
			if (cate.getSubCategories() != null) {
				for (Category sub : cate.getSubCategories()) {
					res.get(cate.getVal()).put(sub.getVal(), new ArrayList<>());
				}
			}
		}
		
		for (Data data : dataList) {
			res.get(data.getCategory()).get(data.getSubcategory()).add(data);
		}
		
		return res;
	}

}
