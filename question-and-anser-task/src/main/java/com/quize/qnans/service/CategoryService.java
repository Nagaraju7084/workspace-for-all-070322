package com.quize.qnans.service;

import java.util.Set;

import com.quize.qnans.dto.CategoryDto;

public interface CategoryService {
	
    public CategoryDto addCategory(CategoryDto categoryDto);

    public CategoryDto updateCategory(CategoryDto categoryDto);

    public Set<CategoryDto> getCategories();

    public CategoryDto getCategory(Long categoryId);

    public void deleteCategory(Long categoryId);
}
