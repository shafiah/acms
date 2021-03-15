package com.management.asset.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.management.asset.bean.Category;

@Service
public interface CategoryService {
	
	/**
	 * Save new instance of category
	 * @return Status of save category.
	 */
	public String saveCategory(Category category);
	
	/**
	 * Update category
	 * @return status of update category
	 */
	public String updateCategory(Category category);
	/**
	 * @return  List of all category
	 */
	public List<Category> listCategory();
}
