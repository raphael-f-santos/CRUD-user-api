package com.crudapplication.user_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crudapplication.user_api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
}
