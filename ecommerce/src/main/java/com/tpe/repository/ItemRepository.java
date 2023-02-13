package com.tpe.repository;

import com.tpe.domain.Category;
import com.tpe.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByCategory(Category category);

    List<Item> findByCategoryId(Long categoryId);
}
