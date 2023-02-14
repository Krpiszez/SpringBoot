package com.tpe.controller;

import com.tpe.domain.Category;
import com.tpe.dto.request.ItemRequestDTO;
import com.tpe.dto.response.CategoryResponseDTO;
import com.tpe.dto.response.ItemResponseDTO;
import com.tpe.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @Autowired
    CategoryController categoryController;
    /*
     post http://localhost:8081/item
    {
        "name" : "Mercedes-Benz",
        "price" : 125.5,
        "description" : "Model:S500",
        "category_id" : 2
    }
     */
    @PostMapping
    public ResponseEntity<String> createItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO){
        String message = "You have saved item successfully";
        itemService.saveItem(itemRequestDTO);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAllItems(){
        List<ItemResponseDTO> itemResponseDTOList = itemService.getAllItems();
        return ResponseEntity.ok(itemResponseDTOList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable Long id){
        itemService.deleteItemById(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<ItemResponseDTO>> getItemByCategory(@RequestParam("category_id") Long id){
        Category category = categoryController.categoryService.getCategoryEntityById(id);
        List<ItemResponseDTO> itemResponseDTOList = itemService.getItemByCategory(category);
        return ResponseEntity.ok(itemResponseDTOList);
    }

    @GetMapping("/{id}/category")
    public ResponseEntity<List<ItemResponseDTO>> getItemByCategoryId(@PathVariable("id") Long categoryId){
        List<ItemResponseDTO> itemResponseDTOList = itemService.getItemByCategoryId(categoryId);
        return ResponseEntity.ok(itemResponseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, ItemResponseDTO>> updateItem(@PathVariable Long id,
                                                                   @Valid @RequestBody ItemRequestDTO itemRequestDTO){
        String message = "Item with id: " + id + " is updated successfully!";
        ItemResponseDTO itemResponseDTO = itemService.updateItemByDTO(id, itemRequestDTO);
        Map<String, ItemResponseDTO> map = new HashMap<>();
        map.put(message, itemResponseDTO);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


}
