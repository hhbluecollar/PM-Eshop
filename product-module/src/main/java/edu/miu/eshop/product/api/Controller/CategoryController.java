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

    @PostMapping("/create")
    public ResponseEntity saveCategory(@RequestBody Category category){
        categoryService.save(category);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Category Created");
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody CategoryDto categoryDto){

        categoryService.addCategory(categoryDto.getParentId(), categoryDto.getValue(), categoryDto.getCategoryId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Category Added");
    }

    @PostMapping("/edit")
    public ResponseEntity editCategory(@RequestBody CategoryDto categoryDto){

        categoryService.editCategory(categoryDto.getParentId(), categoryDto.getValue(),categoryDto.getCategoryId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Category edited");
    }

    @PostMapping("/delete")
    public ResponseEntity deleteCategory(@RequestBody CategoryDto categoryDto){

        categoryService.deleteCategory(categoryDto.getParentId(), categoryDto.getCategoryId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Category deleted");
    }
}
