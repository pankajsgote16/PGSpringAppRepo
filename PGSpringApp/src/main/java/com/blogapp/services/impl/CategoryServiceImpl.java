package com.blogapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.entities.Category;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.CategoryDto;
import com.blogapp.repositories.CategoryRepo;
import com.blogapp.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto generateCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		Category savedCategory = categoryRepo.save(category);
		return modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updatecategory(CategoryDto categoryDto, Integer catId) {
		Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category ", "Id ", catId));
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category  updatedCategory = categoryRepo.save(category);
		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer catId) {
		Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category ", "Id ", catId));
		this.categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer catId) {
		Category category = this.categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFoundException("Category ", "Id ", catId));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> mappedCategories = categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return mappedCategories;
	}

}
