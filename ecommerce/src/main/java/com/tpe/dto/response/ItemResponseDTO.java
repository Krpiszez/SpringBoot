package com.tpe.dto.response;

import com.tpe.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ItemResponseDTO {
    private Long id;


    private String name;


    private String description;


    private double price;


    private Category category;

}