package com.quize.qnans.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quize.qnans.entities.exam.Question;
import com.quize.qnans.entities.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
