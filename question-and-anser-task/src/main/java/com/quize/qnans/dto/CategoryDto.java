package com.quize.qnans.dto;

import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Data;

@Data
public class CategoryDto {

	private Long cid;

    private String title;

    private String description;

    private Set<QuizDto> quizzes = new LinkedHashSet<>();

}
