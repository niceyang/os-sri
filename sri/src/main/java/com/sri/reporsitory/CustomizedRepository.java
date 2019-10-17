package com.sri.reporsitory;

/**
 * Data persistence layer communicating with database
 * Spring Data JpaRepository, which implements the CRUD(create, read, update, delete) operation by default
 * If the other operations is required, it will be added with JPQL in this interface later
 */
public interface CustomizedRepository<E> {
    public void detach(E entity);
}
