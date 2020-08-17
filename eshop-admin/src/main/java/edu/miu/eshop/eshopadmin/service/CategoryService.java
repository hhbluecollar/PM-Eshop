package edu.miu.eshop.eshopadmin.service;

// HH

import edu.miu.eshop.eshopadmin.domain.Category;
import edu.miu.eshop.eshopadmin.domain.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {

	void saveCategory(Category category);

	List<Category> getAll();

	void editCategory(CategoryDto categoryDto);

	void deleteCategory(String categoryId);

	void addCategory(CategoryDto categoryDto);
}