package com.sri.service.imple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
import com.sri.util.model.DataModel;
import com.sri.util.model.PIITag;
import com.sri.util.model.ResultCandidate;
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
	private static final String SELECT = " SELECT ";
	private static final String FROM = " FROM ";
	private static final String WHERE = " WHERE ";

	@Autowired
	private DataRepository dataRepository;
	
	@Autowired
	private CacheService cacheService;
	
	@Autowired
	private MappingService mappingService;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@PostConstruct
	public void initParent() {
		super.repository = dataRepository;
	}

	@Override
	public List<Data> findByUserId(int id) {
		return dataRepository.findByUserId(id);
	}

	@Async
	public void doAccessJob(String uuid, int userId, TopologyModel topo) {
		List<Set<String>> queryIndexList = new ArrayList<>();
		List<ResultCandidate> resCandidates = new ArrayList<>(); // Index name [A-A1-A12], tale tag info
		
		for (Category cate : topo.getCategories()) {
			Set<String> indexSet = new HashSet<>();
			mappingService.buildIndex(cate, new StringBuilder(), indexSet);
			queryIndexList.add(indexSet);
		}
		
		Map<String, Set<String>> tableIndexs = mappingService.getTableIndexs();
		Map<String, PIITag> tabTags = mappingService.getTableDict();
		
		for (String tableName : tableIndexs.keySet()) { // all tables index
			Set<String> tableIndex = tableIndexs.get(tableName);
			checkTable : for (Set<String> queryIndexSet : queryIndexList) { // all query index
				for (String index : queryIndexSet) {
					if (tableIndex.contains(index)) {
						resCandidates.add(new ResultCandidate(index, tabTags.get(tableName)));
						break checkTable;
					}
				}
			}
		}
		
		generateQueries(userId, resCandidates);
		List<DataModel> dataRecords = queryData(resCandidates);
		
		Cache cache = cacheService.findJob(uuid);
		// old data collections
		ObjectMapper mapper = new ObjectMapper();
		String jdata = null;
		try {
			jdata = mapper.writeValueAsString(dataRecords);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			cache.setData("Faild");
			cache.setFinished(1);
			cacheService.update(cache);
			return;
		}
		// old data collections ends
		
		// To simulate the time-consuming processing
		try {
			Thread.sleep(1000);
			System.out.println(jdata);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		cache.setData(jdata);
		cache.setFinished(9);
		cacheService.update(cache);
	}
	
	// Generate queries
	private void generateQueries(int userId, List<ResultCandidate> resCandidates) {
		for (ResultCandidate candidate : resCandidates) {
			StringBuilder sb = new StringBuilder();
			PIITag tag = candidate.getTag();
			sb.append(SELECT);
			for (String col : tag.getPiiFields()) {
				sb.append(" ").append(col).append(",");
			}
			sb.setLength(sb.length() - 1);
			sb.append(FROM)
				.append(tag.getTable())
				.append(WHERE)
				.append(tag.getIdField())
				.append(" = ")
				.append(userId)
				.append(";");
			candidate.setQuery(sb.toString());
			System.out.println(sb.toString());
		}
	}
	
	// Generate queries
	private List<DataModel> queryData(List<ResultCandidate> candidates) {
		List<DataModel> res = new ArrayList<>();
		for (ResultCandidate candi : candidates) {
			DataModel data = new DataModel();
			String[] index = candi.getIndex().split("-");
			data.setCategory(index.length > 0 ? index[0] : "");
			data.setSubCategory1(index.length > 1 ? index[1] : "");
			data.setSubCategory2(index.length > 2 ? index[2] : "");
			
			List<Map<String, Object>> list = jdbcTemplate.queryForList(candi.getQuery());
			data.setData(list);
			res.add(data);
		}
		
		return res;
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
