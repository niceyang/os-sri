package com.sri.reporsitory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

/**
 *  CustomizedRepository for session detach
 */
@Repository
public class CustomizedRepositoryImpl<E> implements CustomizedRepository<E> {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detach(E entity) {
        entityManager.detach(entity);
    }

}
