package com.blogapp.services;

import java.util.List;

import com.blogapp.payloads.CategoryDto;

public interface CategoryService {

	// create
	CategoryDto generateCategory(CategoryDto categoryDto);

	// update
	CategoryDto updatecategory(CategoryDto categoryDto, Integer catId);

	// delete
	void deleteCategory(Integer catId);
	// get

	CategoryDto getCategory(Integer catId);

	//getAll
		List<CategoryDto> getCategories();
}
