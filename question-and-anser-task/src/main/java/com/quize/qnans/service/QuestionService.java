package com.quize.qnans.service;

import java.util.Set;

import com.quize.qnans.dto.QuestionDto;
import com.quize.qnans.entities.exam.Quiz;

public interface QuestionService {

    public QuestionDto addQuestion(QuestionDto questionDto);

    public QuestionDto updateQuestion(QuestionDto questionDto);

    public Set<QuestionDto> getQuestions();

    public QuestionDto getQuestion(Long questionId);

    public Set<QuestionDto> getQuestionsOfQuiz(Quiz quiz);

    public void deleteQuestion(Long quesId);

    public QuestionDto get(Long questionsId);

}
