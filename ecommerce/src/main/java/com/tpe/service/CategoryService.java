package com.tpe.service;

import com.tpe.domain.Category;
import com.tpe.dto.request.CategoryRequestDTO;
import com.tpe.dto.response.CategoryResponseDTO;
import com.tpe.exception.ConflictException;
import com.tpe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public CategoryResponseDTO saveCategory(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRepository.existsByName(categoryRequestDTO.getName())){
            throw new ConflictException("Category with the name: " + categoryRequestDTO.getName() + " already exists!");
        }
        Category category = new Category();
        category.setName(categoryRequestDTO.getName());
        categoryRepository.save(category);

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setId(category.getId());
        return categoryResponseDTO;

    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
