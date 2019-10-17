package com.sri.service;

import com.sri.entity.User;

/**
 * Data persistence business logic layer
 * The interface-implementation architechture is required by the Spring framework (the multiple implementation is allowed)
 * There are many common interfaces between the services(like CRUD), so the common part is define in a BaseService
 * 
 * The service interface get the common methods by extends the BaseService
 * The other required interfaces are added here
 */
public interface UserService extends BaseService<User> {
	public User getUserByName(String name);
	public User getUserByEmail(String email);
	public User getUserByPhone(String phone);
}
