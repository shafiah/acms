package com.management.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.management.asset.bean.Category;
import com.management.asset.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addCategory(@RequestBody Category category) {
		return new ResponseEntity<>(categoryService.saveCategory(category),HttpStatus.OK);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<?>> listCategory() {
		return new ResponseEntity<>(categoryService.listCategory(),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateCategory(@RequestBody Category category) {
		
		String result=categoryService.updateCategory(category);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
