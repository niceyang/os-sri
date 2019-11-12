package com.sri.service;

import java.util.List;

import com.sri.entity.Data;
import com.sri.util.model.TopologyModel;

/**
 * Data persistence business logic layer
 * The interface-implementation architechture is required by the Spring framework (the multiple implementation is allowed)
 * There are many common interfaces between the services(like CRUD), so the common part is define in a BaseService
 * 
 * The service interface get the common methods by extends the BaseService
 * The other required interfaces are added here
 */
public interface DataService extends BaseService<Data> {

	public List<Data> findByUserId(int id);
    public void doAccessJob(String uuid, int id, TopologyModel topo);
    public void doEraseJob(int userId);
}
