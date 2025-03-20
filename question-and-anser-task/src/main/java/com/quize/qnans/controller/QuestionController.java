package com.quize.qnans.controller;

import java.util.List;
import java.util.Map;

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

import com.quize.qnans.dto.QuestionDto;
import com.quize.qnans.entities.exam.Question;
import com.quize.qnans.entities.exam.Quiz;
import com.quize.qnans.service.QuestionService;
import com.quize.qnans.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService service;

    @Autowired
    private QuizService quizService;

    //add question
    @PostMapping("/")
    public ResponseEntity<QuestionDto> add(@RequestBody QuestionDto questionDto) {
        return new ResponseEntity<QuestionDto>(service.addQuestion(questionDto), HttpStatus.CREATED);
    }

    //update the question
    @PutMapping("/")
    public ResponseEntity<QuestionDto> update(@RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok(service.updateQuestion(questionDto));
    }

    //get all question of any quid
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
        return ResponseEntity.ok(quizService.getQuiz(qid));
    }


    @GetMapping("/quiz/all/{qid}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("qid") Long qid) {
    	 Quiz quiz = new Quiz();
         quiz.setqId(qid);
        return ResponseEntity.ok(service.getQuestionsOfQuiz(quiz));
    }


    //get single question
    @GetMapping("/{quesId}")
    public QuestionDto get(@PathVariable("quesId") Long quesId) {
        return service.getQuestion(quesId);
    }

    //delete question
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId) {
        service.deleteQuestion(quesId);
    }


    //eval quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
        System.out.println(questions);
        double marksGot = 0;
        int correctAnswers = 0;
        int attempted = 0;
        for (Question q : questions) {
            //single questions
            QuestionDto questionDto = service.get(q.getQuesId());
            if (questionDto.getAnswer().equals(q.getGivenAnswer())) {
                //correct
                correctAnswers++;

                double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks()) / questions.size();
                //       this.questions[0].quiz.maxMarks / this.questions.length;
                marksGot += marksSingle;

            }

            if (q.getGivenAnswer() != null) {
                attempted++;
            }

        }
        ;

        Map<String, Object> map = Map.of("marksGot", marksGot, "correctAnswers", correctAnswers, "attempted", attempted);
        return ResponseEntity.ok(map);

    }

}
