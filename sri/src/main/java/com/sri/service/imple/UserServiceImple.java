package com.sri.service.imple;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sri.entity.User;
import com.sri.service.UserService;
import com.sri.util.model.Identifier;


/**
 * Data persistence business logic layer
 * The interface-implementation architechture is required by the Spring framework (the multiple implementation is allowed)
 * The service implementations implement the actual methods and will be injected into required components(like other services or restAPI layer)
 * <p>
 * There are many common method between the services(like CRUD), so the common part is define in a BaseImple
 * This service implements get the common methods by extends the BaseService
 * The E is the Entity type of the service
 * <p>
 */
@Service
public class UserServiceImple extends BaseImple<User> implements UserService {

    @Autowired
    private MappingService mappingService;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

	// Query user id according the tag info and query data
	@Override
	public Integer queryUser(String type, String value) {
		Identifier identifier = mappingService.getIdentifierDict().get(type);
		if (identifier == null) {
			return null;
		}
		String query = "SELECT " 
				+ identifier.getIdField() 
				+ " FROM " 
				+ identifier.getTable()
				+ " WHERE " 
				+ identifier.getField() 
				+ " = "
				+ value
				+ ";";
		List<Map<String, Object>> res = jdbcTemplate.queryForList(query);
		return res.size() == 0 ? null : (Integer)res.get(0).get(identifier.getIdField());
	}
}
