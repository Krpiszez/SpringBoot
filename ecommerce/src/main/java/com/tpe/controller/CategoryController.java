package com.tpe.controller;

import com.tpe.domain.Category;
import com.tpe.dto.request.CategoryRequestDTO;
import com.tpe.dto.response.CategoryResponseDTO;
import com.tpe.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/firstLetterC")
    public ResponseEntity<List<Category>> getCategoriesFirstLetterC(){
        List<Category> categories = categoryService.getCategoriesFirstLetterC();
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
    //Get category by page (CategoryDTO by page)
    @GetMapping("/page")
    public ResponseEntity<Page<CategoryResponseDTO>> getCategoryByPage(@RequestParam("page") int page,
                                                                       @RequestParam("size") int size,
                                                                       @RequestParam("sort") String prop,
                                                                       @RequestParam("direction")Sort.Direction direction){
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, prop));
        Page<CategoryResponseDTO> responseDTOS = categoryService.getCategoriesByPage(pageable);
        return ResponseEntity.ok(responseDTOS);
    }


}
