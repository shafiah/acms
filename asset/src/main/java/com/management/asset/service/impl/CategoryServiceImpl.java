package com.management.asset.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.management.asset.bean.Category;
import com.management.asset.repository.CategoryRepository;
import com.management.asset.service.CategoryService;
import com.management.asset.util.Constant;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;
	/**
	 * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
	 * entity instance completely.
	 *
	 * @param entity must not be {@literal null}.
	 * @return the saved entity; will never be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@literal entity} is {@literal null}.
	 */
	private Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public String saveCategory(Category category) {
		String result = null;
		if (!StringUtils.hasText(category.getCategoryName())) {
			result = Constant.CATEGORY_NAME_MISSING;
		}else {
			Category temp = categoryRepository.findByCategoryName(category.getCategoryName());
			if (temp != null) {
				result = Constant.DUPLICATE_CATEGORY;
			} else {
				category.setCategoryId(null);
				category = save(category);
				result = Constant.ADDED_SUCCESSFULLY;
			}
		}
		return result;
	}
	
	
	@Override
	public List<Category> listCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public String updateCategory(Category category) {
		String result = null;
		if (category.getCategoryId() == null || category.getCategoryId()==0) {
			result = Constant.ID_MISSING;
		} else if (!StringUtils.hasText(category.getCategoryName())) {
			result = Constant.CATEGORY_NAME_MISSING;
		} else {
			Category temp = categoryRepository.findByCategoryName(category.getCategoryName());
			if (temp == null || temp.getCategoryId() == category.getCategoryId()) {
				save(category);
				result = Constant.ADDED_SUCCESSFULLY;
			} else {
				result = Constant.DUPLICATE_CATEGORY;
			}
		}
		return result;
	}

}
