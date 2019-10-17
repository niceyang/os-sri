package com.sri.reporsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sri.entity.Data;

/**
 * Data persistence layer communicating with database
 * Spring Data JpaRepository, which implements the CRUD(create, read, update, delete) operation by default
 * If the other operations is required, it will be added with JPQL in this interface later
 */
public interface DataRepository extends JpaRepository<Data, Integer> {
    public List<Data> findByUserId(int id);
    public List<Data> findByUserIdAndType(int id, String type);
}
