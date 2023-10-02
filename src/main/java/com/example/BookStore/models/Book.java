package com.example.BookStore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Book {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @Column
    private String author_book;
    @Column(name = "description", length = 1000)
    private String description;
    @Column
    private int price;
    @Column
    private String author_card;

    @Column
    private int count_sales;

    @Column
    private LocalDate date;

}
