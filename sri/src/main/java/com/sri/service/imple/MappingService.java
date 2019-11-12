package com.sri.service.imple;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.sri.util.model.Category;
import com.sri.util.model.Identifier;
import com.sri.util.model.PIITag;

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
public class MappingService {
	private String model = "0.1";
	private Map<String, PIITag> tableDict = new HashMap<>();
	private Map<String, Set<String>> tableIndexs = new HashMap<>();
	private Map<String, Identifier> identifierDict = new HashMap<>();
	
	
	public void buildIndex(List<Identifier> listIden, List<PIITag> tagList) {
		for (Identifier id : listIden) {
			identifierDict.put(id.getType(), id);
		}
		System.out.println(identifierDict);
		for (PIITag table : tagList) {
			tableDict.put(table.getTable(), table);
			Set<String> indexSet = new HashSet<>();
			indexSet.add("");
			buildIndex(table, 0, new StringBuilder(), indexSet);
			tableIndexs.put(table.getTable(), indexSet);
			System.out.println(indexSet);
		}
	}
	
	public void buildIndex(Category cate, StringBuilder sb, Set<String> res){
		int oriLen = sb.length();
		
		if (sb.length() > 0) {
			sb.append("-");
		}
		sb.append(cate.getVal());
		
		if (cate.getSubCategories() != null) {
			for (Category sub : cate.getSubCategories()) {
				buildIndex(sub, sb, res);
			}
		} else {
			res.add(sb.toString());
		}
		
		sb.setLength(oriLen);
	}

	private void buildIndex(PIITag table, int index, StringBuilder sb, Set<String> res) {
	  int oriLen = sb.length();
	  switch(index) {
	  	case 0: 
	  		if (table.getCategoryMain() != null) {
	  			sb.append(table.getCategoryMain());
	  			res.add(sb.toString());
		  		buildIndex(table, index + 1, sb, res);
		  		sb.setLength(oriLen);
	  		} else {
	  			return;
	  		}
	  		break;
	  	case 1: 
	  		if (table.getCategorySub1() != null) {
	  			for (String sub : table.getCategorySub1()) {
	  				sb.append("-")
		  			  .append(sub);
	  				res.add(sb.toString());
	  				buildIndex(table, index + 1, sb, res);
			  		sb.setLength(oriLen);
	  			}
	  		} else {
	  			return;
	  		}
	  		break;
	  	case 2: 
	  		if (table.getCategorySub2() != null) {
	  			for (String sub : table.getCategorySub2()) {
	  				sb.append("-")
		  			  .append(sub);
	  				res.add(sb.toString());
	  				buildIndex(table, index + 1, sb, res);
			  		sb.setLength(oriLen);
	  			}
	  		} else {
	  			return;
	  		}
	  		break;
	  	case 3: 
	  		return;
	  }
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Map<String, PIITag> getTableDict() {
		return tableDict;
	}

	public void setTableDict(Map<String, PIITag> tableDict) {
		this.tableDict = tableDict;
	}

	public Map<String, Set<String>> getTableIndexs() {
		return tableIndexs;
	}

	public void setTableIndexs(Map<String, Set<String>> tableIndexs) {
		this.tableIndexs = tableIndexs;
	}

	public Map<String, Identifier> getIdentifierDict() {
		return identifierDict;
	}

	public void setIdentifierDict(Map<String, Identifier> identifierDict) {
		this.identifierDict = identifierDict;
	}
}
