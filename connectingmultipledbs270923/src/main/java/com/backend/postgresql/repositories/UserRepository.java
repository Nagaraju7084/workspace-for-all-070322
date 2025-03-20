package com.backend.postgresql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.postgresql.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
