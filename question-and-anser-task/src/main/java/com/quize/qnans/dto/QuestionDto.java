package com.quize.qnans.dto;

import lombok.Data;

@Data
public class QuestionDto {

    private Long quesId;
    private String content;

    private String image;

    private String answer;

    private  String givenAnswer;

    private QuizDto quiz;

}
