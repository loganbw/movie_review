package com.logan.movie_review.dao;

import com.logan.movie_review.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    Role findByName(String roleName);
}
