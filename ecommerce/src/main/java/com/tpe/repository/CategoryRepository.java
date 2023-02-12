package com.tpe.repository;

import com.tpe.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    @Query("select c from Category c where c.name like '%a%'")
    List<Category> getCategoriesHasLetterA();

    @Query("select c from Category c where c.name like 'C%'")
    List<Category> getCategoriesFirstLetterC();
}
