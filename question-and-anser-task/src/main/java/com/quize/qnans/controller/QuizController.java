package com.quize.qnans.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quize.qnans.dto.CategoryDto;
import com.quize.qnans.dto.QuizDto;
import com.quize.qnans.entities.exam.Category;
import com.quize.qnans.entities.exam.Quiz;
import com.quize.qnans.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    //add quiz service
    @PostMapping("/")
    public ResponseEntity<QuizDto> add(@RequestBody QuizDto quizDto) {
        return new ResponseEntity<QuizDto>(quizService.addQuiz(quizDto), HttpStatus.CREATED);
    }

    //update quiz

    @PutMapping("/")
    public ResponseEntity<QuizDto> update(@RequestBody QuizDto quizDto) {
        return ResponseEntity.ok(quizService.updateQuiz(quizDto));
    }

    //get quiz
    @GetMapping("/")
    public ResponseEntity<?> quizzes() {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    //get single quiz
    @GetMapping("/{qid}")
    public QuizDto quiz(@PathVariable("qid") Long qid) {
        return quizService.getQuiz(qid);
    }

    //delete the quiz
    @DeleteMapping("/{qid}")
    public void delete(@PathVariable("qid") Long qid) {
        quizService.deleteQuiz(qid);
    }

    @GetMapping("/category/{cid}")
    public List<QuizDto> getQuizzesOfCategory(@PathVariable("cid") Long cid) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCid(cid);
        return quizService.getQuizzesOfCategory(categoryDto);
    }

    //get active quizzes
    @GetMapping("/active")
    public List<QuizDto> getActiveQuizzes() {
        return quizService.getActiveQuizzes();
    }

    //get active quizzes of category
    @GetMapping("/category/active/{cid}")
    public List<QuizDto> getActiveQuizzes(@PathVariable("cid") Long cid) {
    	CategoryDto categoryDto = new CategoryDto();
    	categoryDto.setCid(cid);
        return this.quizService.getActiveQuizzesOfCategory(categoryDto);
    }




}
