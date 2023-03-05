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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // to initialize mock object
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService = new CategoryService();

    private Category category;
    private CategoryRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new CategoryRequestDTO();
        requestDTO.setName("TV");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Test save category when the categoryName is not in DB")
    void testSaveCategoryWhenDataIsSaved() {

        //given-preconditions
        given(categoryRepository.existsByName(requestDTO.getName())).willReturn(false);

        // creating entity
        category = new Category();
        category.setName(requestDTO.getName());
        //saving category
        categoryRepository.save(category); // this method is coming from data JPA


        //when

        //then
        System.out.println(requestDTO.getName()+" "+category.getName());
        assertEquals(requestDTO.getName(), category.getName());
        //we can test in this way also
        assertThat(categoryService.saveCategory(requestDTO)).isNotNull();


    }

    @Test
    @DisplayName("Test save category when the categoryName is already in DB. We should get exception")
    void saveCategoryWithException() {

        //given-preconditions
        CategoryRequestDTO request = new CategoryRequestDTO();
        given(categoryRepository.existsByName(request.getName())).willReturn(true);

        //when
        //then

        assertThrows(com.tpe.exception.ConflictException.class, ()->{
            categoryService.saveCategory(request);
        });
        //another way to test exception if exception is thrown
        //we can test exception class and the message which will be thrown

        assertThatThrownBy(()->categoryService.saveCategory(request))
                .isInstanceOf(com.tpe.exception.ConflictException.class)
                .hasMessage("Category with the name: "+ request.getName() + " already exists!");

        //verify that categoryRepository.save() never executed
        verify(categoryRepository, never()).save(any());


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
    @DisplayName("Test case when the requested id is already in DB!")
    void testGetCategoryEntityByIdWhenThereIsCategory() {
        //given
        category = new Category();
        category.setId(1L);
        category.setName("Mike");
        given(categoryRepository.findById(1L)).willReturn(Optional.ofNullable(category));

        //when
        Category categoryFound = categoryService.getCategoryEntityById(1L);

        //then
        assertThat(categoryFound).isNotNull();


    }

    @Test
    @DisplayName("Test case when the requested id is not in DB!")
    void testGetCategoryEntityByIdForException() {
        //given
//        category = new Category();
//        category.setId(1L);
//        category.setName("Mike");
        given(categoryRepository.findById(2L)).willReturn(Optional.empty());

        //when


        //then
        assertThatThrownBy(()->categoryService.getCategoryEntityById(2L))
                .isInstanceOf(com.tpe.exception.ResourceNotFoundException.class)
                .hasMessage("Category with id: " + 2L + " is not found!");
    }

    @Test
    @Disabled
    void getCategoriesByPage() {
    }

    @Test
    @Disabled
    void updateCategoryById() {
    }

    @Test
    void testGetAllCategories(){
        category = new Category(1L, "Mark");
        Category category1 = new Category(2L, "Tom");

        given(categoryRepository.findAll()).willReturn(List.of(category, category1));

        List<Category> categoryList = categoryService.getAllCategories();

        assertThat(categoryList).isNotNull();
        assertThat(categoryList.size()).isEqualTo(2);
        verify(categoryRepository, times(1)).findAll();



    }
}