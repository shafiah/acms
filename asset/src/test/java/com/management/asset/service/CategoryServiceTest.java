package com.management.asset.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.management.asset.bean.Category;
import com.management.asset.repository.CategoryRepository;
import com.management.asset.service.impl.CategoryServiceImpl;
import com.management.asset.util.Constant;

@SpringBootTest
public class CategoryServiceTest {

	@Mock
	CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryService categoryService = new CategoryServiceImpl();

	@DisplayName("Test Mock saveCategory when categoryName is empty")
	@Test
	void saveCategoryWhenCategoryNameIsEmpty() {
		Category category = new Category();
		assertEquals(Constant.CATEGORY_NAME_MISSING, categoryService.saveCategory(category));
	}

	@DisplayName("Test Mock saveCategory when categoryName is duplicate")
	@Test
	void saveCategoryWhenCategoryNameIsDuplicate() {
		Category category = new Category();
		category.setCategoryName("Laptop");
		Mockito.when(categoryRepository.findByCategoryName(category.getCategoryName())).thenReturn(new Category());
		assertEquals(Constant.DUPLICATE_CATEGORY, categoryService.saveCategory(category));
	}

	@DisplayName("Test Mock saveCategory when categoryName is not duplicate")
	@Test
	void saveCategoryWhenCategoryNameIsNotDuplicate() {
		Category category = new Category();
		category.setCategoryName("Laptop");
		Mockito.when(categoryRepository.findByCategoryName(category.getCategoryName())).thenReturn(null);
		assertEquals(Constant.ADDED_SUCCESSFULLY, categoryService.saveCategory(category));
	}

	@DisplayName("Test Mock List Category")
	@Test
	void listCategory() {
		Mockito.when(categoryRepository.findAll()).thenReturn(new ArrayList<>());
		assertNotNull(categoryService.listCategory());
	}

	@DisplayName("Test Mock updateCategory when CategoryName is Duplicate")
	@Test
	void updateCategoryWhenCategoryNameIsDuplicate() {
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("Test");

		Category temp = new Category();
		temp.setCategoryId(2);
		Mockito.when(categoryRepository.findByCategoryName(category.getCategoryName())).thenReturn(temp);
		assertEquals(Constant.DUPLICATE_CATEGORY, categoryService.updateCategory(category));
	}

	@DisplayName("Test Mock updatecategory when categoryId isNull ")
	@Test
	void updateCategoryWhenCategoryIdNull() {
		Category category = new Category();
		assertEquals(Constant.ID_MISSING, categoryService.updateCategory(category));
	}

	@DisplayName("Test Mock updateCategory When CategoryName isEmpty")
	@Test
	void updateCategoryWhenCategoryNameEmpty() {
		Category category = new Category();
		category.setCategoryId(1);
		assertEquals(Constant.CATEGORY_NAME_MISSING, categoryService.updateCategory(category));
	}

	@DisplayName("Test Mock updateCategory when CategoryName is Duplicate")
	@Test
	void updateCategoryWhenCategoryNameIsNotduplicate() {
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("Test");
		Category temp = new Category();
		temp.setCategoryId(1);
		Mockito.when(categoryRepository.findByCategoryName(category.getCategoryName())).thenReturn(temp);
		assertEquals(Constant.ADDED_SUCCESSFULLY, categoryService.updateCategory(category));
	}
}
