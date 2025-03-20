package com.quize.qnans.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quize.qnans.dto.CategoryDto;
import com.quize.qnans.entities.exam.Category;
import com.quize.qnans.exception.ResourceNotFoundException;
import com.quize.qnans.repositories.CategoryRepository;
import com.quize.qnans.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        return categoryToDto(categoryRepository.save(dtoToCategory(categoryDto)));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        return categoryToDto(categoryRepository.save(dtoToCategory(categoryDto)));
    }

    @Override
    public Set<CategoryDto> getCategories() {
        return new LinkedHashSet<>(
        		categoryRepository.findAll()
        		.stream()
        		.map(category->categoryToDto(category)).collect(Collectors.toSet())
        		);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
    	Category category = categoryRepository.findById(categoryId).orElseThrow(
    			()->new ResourceNotFoundException("Category","category id",categoryId)
    			);
        return categoryToDto(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(
        		()->new ResourceNotFoundException("Category", "category id", categoryId)
        		);
        categoryRepository.deleteById(categoryId);
    }
    
    public CategoryDto categoryToDto(Category category) {
    	CategoryDto categoryDto = new CategoryDto();
    	BeanUtils.copyProperties(category, categoryDto);
    	return categoryDto;
    }
    
    public Category dtoToCategory(CategoryDto categoryDto) {
    	Category category = new Category();
    	BeanUtils.copyProperties(categoryDto, category);
    	return category;
    }
}
