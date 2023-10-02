package com.example.BookStore.repositories;

import com.example.BookStore.models.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Long deleteByIdbook(Long idbook);
    Basket findByIdbook(Long idbook);

//    void removeByIdBook(Long idBook);
}
