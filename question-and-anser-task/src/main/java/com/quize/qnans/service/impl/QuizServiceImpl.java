package com.quize.qnans.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quize.qnans.dto.CategoryDto;
import com.quize.qnans.dto.QuizDto;
import com.quize.qnans.entities.exam.Category;
import com.quize.qnans.entities.exam.Quiz;
import com.quize.qnans.exception.ResourceNotFoundException;
import com.quize.qnans.repositories.QuizRepository;
import com.quize.qnans.service.QuizService;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {
	
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public QuizDto addQuiz(QuizDto quizDto) {
        return quizToDto(quizRepository.save(dtoToQuiz(quizDto)));
    }

    @Override
    public QuizDto updateQuiz(QuizDto quizDto) {
        return quizToDto(quizRepository.save(dtoToQuiz(quizDto)));
    }

    @Override
    public Set<QuizDto> getQuizzes() {
        return new HashSet<>(quizRepository.findAll()
        		.stream()
        		.map(quiz->quizToDto(quiz))
        		.collect(Collectors.toSet())
        		);
    }

    @Override
    public QuizDto getQuiz(Long quizId) {
        return quizToDto(quizRepository.findById(quizId).
        		orElseThrow(
        		()->new ResourceNotFoundException("Quiz", "quiz id", quizId)		
        		));
    }

    @Override
    public void deleteQuiz(Long quizId) {
        quizRepository.delete(quizRepository.findById(quizId).orElseThrow(
        		()->new ResourceNotFoundException("Quiz", "quiz id", quizId)
        		));
    }

    @Override
    public List<QuizDto> getQuizzesOfCategory(CategoryDto categoryDto) {

    	return quizRepository.findBycategory(
        		new CategoryServiceImpl().dtoToCategory(categoryDto))
        		.stream()
        		.map(quiz->quizToDto(quiz)).collect(Collectors.toList());
    }


    //get active quizzes

    @Override
    public List<QuizDto> getActiveQuizzes() {
        return quizRepository.findByActive(true)
        		.stream()
        		.map(quiz->quizToDto(quiz))
        		.collect(Collectors.toList());
    }

    @Override
    public List<QuizDto> getActiveQuizzesOfCategory(CategoryDto categoryDto) {
        return quizRepository.findByCategoryAndActive(
        		new CategoryServiceImpl().dtoToCategory(categoryDto), true)
        		.stream()
        		.map(quiz->quizToDto(quiz))
        		.collect(Collectors.toList());
    }
    
    public QuizDto quizToDto(Quiz quiz) {
    	QuizDto quizDto = new QuizDto();
    	BeanUtils.copyProperties(quiz, quizDto);
    	return quizDto;
    }
    
    public Quiz dtoToQuiz(QuizDto quizDto) {
    	Quiz quiz = new Quiz();
    	BeanUtils.copyProperties(quizDto, quiz);
    	return quiz;
    }
    
}
