package edu.miu.eshop.product.api.Controller;

import edu.miu.eshop.product.dto.CategoryDto;
import edu.miu.eshop.product.entity.Category;
import edu.miu.eshop.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
        categoryService.save(category);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody CategoryDto categoryDto){

        categoryService.addCategory(categoryDto.getParentId(), categoryDto.getValue());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getAll());
    }

    @PutMapping("/edit")
    public ResponseEntity editCategory(@RequestBody CategoryDto categoryDto){

        categoryService.editCategory( categoryDto.getValue(),categoryDto.getCategoryId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getAll());
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCategory(@RequestBody CategoryDto categoryDto){

        categoryService.deleteCategory( categoryDto.getCategoryId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryService.getAll());
    }

}
