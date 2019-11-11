package com.sri.service.imple;

import org.springframework.stereotype.Service;

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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
