package com.tpe.service;

import com.tpe.domain.Category;
import com.tpe.dto.request.CategoryRequestDTO;
import com.tpe.dto.response.CategoryResponseDTO;
import com.tpe.repository.CategoryRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class) // to initialize mock object
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService = new CategoryService();

    private Category category;

    @BeforeEach
    void setUp() {

        category = new Category();
        category.setName("TV");
        category.setId(1L);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test save category when the categoryName is not in DB")
    void saveCategory() {

        //given-preconditions
        given(categoryRepository.existsByName(category.getName())).willReturn(false);

        //given(categoryRepository.save(category)).willReturn(category);
        CategoryRequestDTO dto = new CategoryRequestDTO();
        dto.setName("TV");
        Category categoryNew = new Category();
        categoryNew.setName(dto.getName());
        //when
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();

        categoryResponseDTO.setName(category.getName());

        CategoryResponseDTO savedObj = categoryService.saveCategory(dto);
        //then
        assertThat(savedObj).isNotNull();

    }

    @Test
    @DisplayName("Test save category when the categoryName is already in DB. We shoudl get exception")
    void saveCategoryWithException() {

        //given-preconditions
        CategoryRequestDTO request = new CategoryRequestDTO();
        given(categoryRepository.existsByName(request.getName())).willReturn(true);

        //when
        //then
//        assertThrows(com.tpe.exception.ConflictException.class, ()->{
//            categoryService.saveCategory(request);
//        });
        assertThatThrownBy(()->categoryService.saveCategory(request))
                .isInstanceOf(com.tpe.exception.ConflictException.class)
                .hasMessage("The " + request.getName() + " already exists");



    }

    @Test
    @Disabled
    void getAllCategories() {
    }

    @Test
    @Disabled
    void getCategoriesHasLetterA() {
    }

    @Test
    @Disabled
    void getCategoriesFirstLetterC() {
    }

    @Test
    @Disabled
    void deleteById() {
    }

    @Test
    @Disabled
    void getCategoryById() {
    }

    @Test
    @Disabled
    void getCategoryEntityById() {
    }

    @Test
    @Disabled
    void getCategoriesByPage() {
    }

    @Test
    @Disabled
    void updateCategoryById() {
    }
}