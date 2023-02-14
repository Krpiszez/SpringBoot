package com.tpe.service;

import com.tpe.domain.Category;
import com.tpe.domain.Item;
import com.tpe.dto.request.ItemRequestDTO;
import com.tpe.dto.response.CategoryResponseDTO;
import com.tpe.dto.response.ItemResponseDTO;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CategoryService categoryService;

    public void saveItem(ItemRequestDTO itemRequestDTO) {

        Category category = categoryService.getCategoryEntityById(itemRequestDTO.getCategory_id());

        Item item = new Item();
        item.setName(itemRequestDTO.getName());
        item.setPrice(itemRequestDTO.getPrice());
        item.setDescription(itemRequestDTO.getDescription());
        item.setCategory(category);

        itemRepository.save(item);

    }

    public Item getItemById(Long id){
        return itemRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Item with id: " + id + " can not be found!"));
    }

    public List<ItemResponseDTO> getAllItems() {
        List<Item> itemList = itemRepository.findAll();
        List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();
        for (Item w: itemList){
            itemResponseDTOList.add(new ItemResponseDTO(w.getId(), w.getName(), w.getDescription(), w.getPrice(), w.getCategory()));
        }
        return itemResponseDTOList;
    }

    public void deleteItemById(Long id) {
        Item item = getItemById(id);
        itemRepository.delete(item);
    }

    public List<ItemResponseDTO> getItemByCategory(Category category) {

        List<Item> itemList = itemRepository.findByCategory(category);
        List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();
        for (Item w: itemList){
            itemResponseDTOList.add(new ItemResponseDTO(w.getId(), w.getName(), w.getDescription(), w.getPrice(), w.getCategory()));
        }
        return itemResponseDTOList;

    }

    public List<ItemResponseDTO> getItemByCategoryId(Long categoryId) {
        List<Item> itemList = itemRepository.findByCategoryId(categoryId);
        List<ItemResponseDTO> itemResponseDTOList = new ArrayList<>();
        for (Item w: itemList){
            itemResponseDTOList.add(new ItemResponseDTO(w.getId(), w.getName(), w.getDescription(), w.getPrice(), w.getCategory()));
        }
        return itemResponseDTOList;
    }

    public ItemResponseDTO updateItemByDTO(Long id, ItemRequestDTO itemRequestDTO) {
        Item item = getItemById(id);
        CategoryResponseDTO categoryResponseDTO = categoryService.getCategoryById(itemRequestDTO.getCategory_id());
        Category category = new Category(categoryResponseDTO.getId(), categoryResponseDTO.getName());
        item.setName(itemRequestDTO.getName());
        item.setCategory(category);
        item.setPrice(itemRequestDTO.getPrice());
        item.setDescription(itemRequestDTO.getDescription());
        itemRepository.save(item);

        ItemResponseDTO itemResponseDTO = new ItemResponseDTO(item.getId(), item.getName(), item.getDescription(), item.getPrice(), item.getCategory());
        itemResponseDTO.setId(item.getId());
        itemResponseDTO.setCategory(category);
        itemResponseDTO.setName(itemRequestDTO.getName());
        itemResponseDTO.setPrice(itemRequestDTO.getPrice());
        itemResponseDTO.setDescription(itemRequestDTO.getDescription());
        return itemResponseDTO;
    }
}
