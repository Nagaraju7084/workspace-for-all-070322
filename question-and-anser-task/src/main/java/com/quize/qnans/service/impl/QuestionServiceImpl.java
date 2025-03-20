package com.quize.qnans.service.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quize.qnans.dto.QuestionDto;
import com.quize.qnans.entities.exam.Question;
import com.quize.qnans.entities.exam.Quiz;
import com.quize.qnans.exception.ResourceNotFoundException;
import com.quize.qnans.repositories.QuestionRepository;
import com.quize.qnans.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public QuestionDto addQuestion(QuestionDto questionDto) {
        return questionToDto(questionRepository.save(dtoToQuestion(questionDto)));
    }

    @Override
    public QuestionDto updateQuestion(QuestionDto questionDto) {
        return questionToDto(questionRepository.save(dtoToQuestion(questionDto)));
    }

    @Override
    public Set<QuestionDto> getQuestions() {
        return new HashSet<>(questionRepository.findAll()
        		.stream().map((question)->questionToDto(question)).collect(Collectors.toSet())
        		);
    }

    @Override
    public QuestionDto getQuestion(Long questionId) {
        return questionToDto(questionRepository.findById(questionId).orElseThrow(
        		()->new ResourceNotFoundException("Question", "question id", questionId)
        		));
    }

    @Override
    public Set<QuestionDto> getQuestionsOfQuiz(Quiz quiz) {
        return questionRepository.findByQuiz(quiz)
        		.stream().map(question->questionToDto(question))
        		.collect(Collectors.toSet());
    }

    @Override
    public void deleteQuestion(Long questionId) {
    	
        questionRepository.delete(questionRepository.findById(questionId)
        		.orElseThrow(
        		()->new ResourceNotFoundException("Question", "question id", questionId)
        		));
    }

    @Override
    public QuestionDto get(Long questionId) {
       return questionToDto(questionRepository.findById(questionId)
    		   .orElseThrow(
    			()->new ResourceNotFoundException("Question", "question id", questionId)
    			));
    }
    
    public Question dtoToQuestion(QuestionDto questionDto) {
    	Question question = new Question();
    	BeanUtils.copyProperties(questionDto, question);
    	return question;
    }
    
    public QuestionDto questionToDto(Question question) {
    	QuestionDto questionDto = new QuestionDto();
    	BeanUtils.copyProperties(question, questionDto);
    	return questionDto;
    }
    
}
