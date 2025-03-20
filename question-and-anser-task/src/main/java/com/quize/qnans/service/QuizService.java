package com.quize.qnans.service;

import java.util.List;
import java.util.Set;

import com.quize.qnans.dto.CategoryDto;
import com.quize.qnans.dto.QuizDto;
import com.quize.qnans.entities.exam.Quiz;

public interface QuizService {

    public QuizDto addQuiz(QuizDto quizDto);

    public QuizDto updateQuiz(QuizDto quizDto);

    public Set<QuizDto> getQuizzes();

    public QuizDto getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);


    public List<QuizDto> getQuizzesOfCategory(CategoryDto categoryDto);

    public List<QuizDto> getActiveQuizzes();

    public List<QuizDto> getActiveQuizzesOfCategory(CategoryDto categoryDto);
}
