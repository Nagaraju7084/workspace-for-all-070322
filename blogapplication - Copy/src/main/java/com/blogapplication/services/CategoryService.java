package com.blogapplication.services;

import java.util.List;

import com.blogapplication.payload.CategoryDto;

public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	CategoryDto updateCategory(CategoryDto newCategoryDto, Integer oldCategoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//get
	CategoryDto getCategoryById(Integer categoryId);
	
	//get all
	List<CategoryDto> getAllCategories();

}
