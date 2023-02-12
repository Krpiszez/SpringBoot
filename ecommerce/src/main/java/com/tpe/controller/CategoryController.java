package com.tpe.controller;

import com.tpe.domain.Category;
import com.tpe.dto.request.CategoryRequestDTO;
import com.tpe.dto.response.CategoryResponseDTO;
import com.tpe.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> addCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO){
        CategoryResponseDTO categoryResponseDTO = categoryService.saveCategory(categoryRequestDTO);
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/secondLetterA")
    public ResponseEntity<List<Category>> getCategoriesHasLetterA(){
        List<Category> categories = categoryService.getCategoriesHasLetterA();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/id")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@RequestParam("id") Long id){
        CategoryResponseDTO categoryResponseDTO = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryResponseDTO);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CategoryResponseDTO> deleteCategoryById(@PathVariable("id") Long id){
        CategoryResponseDTO categoryResponseDTO = categoryService.deleteById(id);
        return ResponseEntity.ok(categoryResponseDTO);
    }


}
