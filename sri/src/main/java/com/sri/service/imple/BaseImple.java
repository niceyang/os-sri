package com.sri.service.imple;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data persistence business logic layer
 * The interface-implementation architechture is required by the Spring framework (the multiple implementation is allowed)
 * The service implementations implement the actual methods and will be injected into required components(like other services or restAPI layer)
 * <p>
 * There are many common method between the services(like CRUD), so the common part is define in a BaseImple
 * The actual service implements will get the methods by extends this BaseService
 * The E is the Entity type of the service
 */
public class BaseImple<E> {

    //JpaRepository, will be override by the children
    public JpaRepository<E, Integer> repository;

    // Return all repos
    public List<E> findAll() {
        return repository.findAll();
    }


    // Return entity with input id
    public E findById(int id) {
        Optional<E> result = repository.findById(id);

        E entity = null;
        if (result.isPresent()) {
            entity = result.get();
        }
        return entity;
    }

    // Save entity in repository
    public E save(E entity) {
        return repository.save(entity);
    }

    // Update entity in repository
    public void update(E entity) {
        repository.save(entity);
    }

    // Delete entity with input id in repository
    public void deleteById(int id) {
        repository.deleteById(id);
    }

}
