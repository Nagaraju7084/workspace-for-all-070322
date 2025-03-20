package com.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

}
