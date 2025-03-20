package com.quize.qnans.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class QuizDto {

    private Long qId;


    private String title;

    private String description;

    private String maxMarks;

    private String numberOfQuestions;

    private boolean active = false;

    private CategoryDto category;

    private Set<QuestionDto> questions = new HashSet<>();

}
