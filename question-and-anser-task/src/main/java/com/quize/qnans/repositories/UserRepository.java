package com.quize.qnans.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quize.qnans.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findByUsername(String username);
    
}
