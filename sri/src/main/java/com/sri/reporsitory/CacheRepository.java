package com.sri.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sri.entity.Cache;

/**
 * Data persistence layer communicating with database
 * Spring Data JpaRepository, which implements the CRUD(create, read, update, delete) operation by default
 * If the other operations is required, it will be added with JPQL in this interface later
 */
public interface CacheRepository extends JpaRepository<Cache, Integer> {
    public Cache findByUid(String uid);
}
