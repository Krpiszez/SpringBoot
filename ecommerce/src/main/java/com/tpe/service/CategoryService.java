package com.tpe.service;

import com.tpe.domain.Category;
import com.tpe.dto.request.CategoryRequestDTO;
import com.tpe.dto.response.CategoryResponseDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

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

    public List<Category> getCategoriesHasLetterA() {
        return categoryRepository.getCategoriesHasLetterA();
    }

    public List<Category> getCategoriesFirstLetterC() {
        return categoryRepository.getCategoriesFirstLetterC();
    }

    public CategoryResponseDTO deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category with id: " + id + " is not found!"));
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        categoryRepository.delete(category);
        return categoryResponseDTO;
    }

    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category with id: " + id + " is not found!"));
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setId(category.getId());
        categoryResponseDTO.setName(category.getName());
        return categoryResponseDTO;
    }

    public Page<CategoryResponseDTO> getCategoriesByPage(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        // we need to map Page<Category> to Page<CategoryResponseDTO>
        Page<CategoryResponseDTO> categoryResponseDTOPage = categoryPage.map(new Function<Category, CategoryResponseDTO>() {
            @Override
            public CategoryResponseDTO apply(Category category) {
                CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
                categoryResponseDTO.setName(category.getName());
                categoryResponseDTO.setId(category.getId());
                return categoryResponseDTO;
            }
        });
        return categoryResponseDTOPage;
    }
}
