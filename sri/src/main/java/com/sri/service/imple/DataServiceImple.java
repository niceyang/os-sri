package com.sri.service.imple;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sri.entity.Data;
import com.sri.reporsitory.DataRepository;
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

}
