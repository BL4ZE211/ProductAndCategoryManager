package com.productmanager.demo.service;

import com.productmanager.demo.entity.Category;
import com.productmanager.demo.entity.Product;
import com.productmanager.demo.exception.ResourceNotFoundException;
import com.productmanager.demo.payload.CategoryDto;
import com.productmanager.demo.payload.ProductDto;
import com.productmanager.demo.repository.CategoryRepository;
import com.productmanager.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductDto mapToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryId(product.getCategory().getId());
        return productDto;
    }

    public ProductDto createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                ()-> new ResourceNotFoundException("No category with id : "+productDto.getCategoryId()));
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);

        productRepository.save(product);

        return  mapToDto(product);
    }

    public List<ProductDto> getAllProducts() {
        return  productRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No product with id : " + id));
        return mapToDto(product);
    }

    public ProductDto updateProduct(long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No product with id : "+ id));
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                ()-> new ResourceNotFoundException("No product with id : "+ id));
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);

        productRepository.save(product);

        return mapToDto(product);
    }

    public void deleteProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No Product with id : "+ id));
        productRepository.delete(product);
    }
}
