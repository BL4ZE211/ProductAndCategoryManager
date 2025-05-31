package com.productmanager.demo.controller;

import com.productmanager.demo.payload.CategoryDto;
import com.productmanager.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryDto createCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.createCategory(categoryDto);
    }

    @GetMapping
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryCategoryById(@PathVariable long id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable long id,@RequestBody CategoryDto categoryDto){
        return categoryService.updateCategory(id,categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long id){
        categoryService.deletCategory(id);
    }
}
