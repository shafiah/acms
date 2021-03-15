package com.management.asset.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.management.asset.bean.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Category findByCategoryName(String categoryName);
}
