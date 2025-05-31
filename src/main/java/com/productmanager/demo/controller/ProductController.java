package com.productmanager.demo.controller;

import com.productmanager.demo.entity.Product;
import com.productmanager.demo.payload.CategoryDto;
import com.productmanager.demo.payload.ProductDto;
import com.productmanager.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productService.createProduct(productDto);
    }

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable long id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductDto udateProduct(@PathVariable long id,@RequestBody ProductDto productDto) {
        return productService.updateProduct(id,productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id){
         productService.deleteProduct(id);
    }
}
