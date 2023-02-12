package com.tpe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 100)
    private String description;


    private double price;

    @ManyToOne //many item to one Category
    @JoinColumn(name="category_id")
    private Category category;


}