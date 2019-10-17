package com.sri.service;

import java.util.List;

/**
 * Data persistence business logic layer
 * The interface-implementation architechture is required by the Spring framework (the multiple implementation is allowed)
 * There are many common interfaces between the services(like CRUD), so the common part is define in a BaseService
 * The actual service interfaces will get the methods by extends this BaseService
 * The E is the Entity type of the service
 */
public interface BaseService<E> {
    // Return all repos
    public List<E> findAll();

    // Return entity with input id
    public E findById(int id);

    // Save entity in repository
    public E save(E entity);

    // Update entity in repository
    public void update(E entity);

    // Delete entity with input id in repository
    public void deleteById(int id);
}
