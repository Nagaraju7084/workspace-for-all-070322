package com.blogapplication.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.entities.Category;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payload.CategoryDto;
import com.blogapplication.repositories.CategoryRepository;
import com.blogapplication.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category savedCategory = categoryRepository.save(dtoToCategory(categoryDto));
		return categoryToDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto newCategoryDto, Integer oldCategoryId) {
		Category category = categoryRepository.findById(oldCategoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", oldCategoryId));
		category.setCategoryTitle(newCategoryDto.getCategoryTitle());
		category.setCategoryDescription(newCategoryDto.getCategoryDescription());
		Category updatedCategory = categoryRepository.save(category);
		return categoryToDto(updatedCategory);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		categoryRepository.delete(category);

	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		return categoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		return categoryRepository.findAll()
				.stream().map(category->categoryToDto(category))
				.collect(Collectors.toList());
	}
	
	public Category dtoToCategory(CategoryDto categoryDto) {
		return modelMapper.map(categoryDto, Category.class);
	}
	
	public CategoryDto categoryToDto(Category category) {
		return modelMapper.map(category, CategoryDto.class);
	}

}
