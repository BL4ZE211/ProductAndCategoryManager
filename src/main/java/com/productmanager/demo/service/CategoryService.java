package com.productmanager.demo.service;

import com.productmanager.demo.entity.Category;
import com.productmanager.demo.exception.ResourceNotFoundException;
import com.productmanager.demo.payload.CategoryDto;
import com.productmanager.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDto mapToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());

        return categoryDto;
    }
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepository.save(category);

        return mapToDto(category);
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No CAtegory With Id : "+id));
        return mapToDto(category);
    }

    public CategoryDto updateCategory(long id, CategoryDto categoryDto) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No Category by Id : "+ id));
        existingCategory.setName(categoryDto.getName());
        categoryRepository.save(existingCategory);
        return mapToDto(existingCategory);

    }

    public void deletCategory(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No Category with id : "+ id));
        categoryRepository.delete(category);
    }
}
