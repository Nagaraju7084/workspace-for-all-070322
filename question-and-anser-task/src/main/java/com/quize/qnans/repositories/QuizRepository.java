package com.quize.qnans.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quize.qnans.entities.exam.Category;
import com.quize.qnans.entities.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public List<Quiz> findBycategory(Category category);

    public List<Quiz> findByActive(Boolean b);

    public List<Quiz> findByCategoryAndActive(Category c, Boolean b);
}
