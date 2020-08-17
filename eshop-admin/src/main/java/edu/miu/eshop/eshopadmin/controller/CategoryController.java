package edu.miu.eshop.eshopadmin.controller;

// HH

import edu.miu.eshop.eshopadmin.domain.Category;
import edu.miu.eshop.eshopadmin.domain.Dto.CategoryDto;
import edu.miu.eshop.eshopadmin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public ResponseEntity getCategories(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(categoryService.getAll());
	}

	@PostMapping("/create")
	public ResponseEntity saveCategory(@RequestBody Category category){
		categoryService.saveCategory(category);
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(categoryService.getAll());
	}

	@PostMapping("/add")
	public ResponseEntity addCategory(@RequestBody CategoryDto categoryDto){

		categoryService.addCategory(categoryDto);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(categoryService.getAll());
	}

	@PutMapping("/edit")
	public ResponseEntity editCategory(@RequestBody CategoryDto categoryDto){
		categoryService.editCategory(categoryDto);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(categoryService.getAll());
	}

	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity deleteCategory(@PathVariable String categoryId){

		categoryService.deleteCategory(categoryId);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(categoryService.getAll());
	}

}