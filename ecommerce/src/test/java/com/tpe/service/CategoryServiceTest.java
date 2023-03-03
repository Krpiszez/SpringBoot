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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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
        categoryRepository.save(category);

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
                .hasMessage("The " + request.getName() + " already exists");

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