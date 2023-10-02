package com.example.BookStore.services;

import com.example.BookStore.models.Basket;
import com.example.BookStore.models.Book;
import com.example.BookStore.repositories.BasketRepository;
import com.example.BookStore.repositories.BookRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class BasketServices {
    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        List<Basket> baskets = basketRepository.findAll();
        List<Book> books = new ArrayList<>();

        for (Basket basket : baskets) {
            Book book = bookRepository.findById(basket.getIdbook())
                    .orElseThrow(() -> new RuntimeException("Книжка не знайдена з id: " + basket.getIdbook()));
            books.add(book);
        }

        return books;
    }


    public void addItem(Long id) {
        System.out.println(id);
        Basket basket = new Basket();
        basket.setIdbook(id);
        basket.setCount(1);
        basketRepository.save(basket);
    }

    public void removeItem(Long id){
        System.out.println("2)Remove by id: " + id);

        basketRepository.deleteByIdbook(id);
    }

    public void plus_amount(Long id) {
        Basket basket = basketRepository.findByIdbook((bookRepository.findById(id)).get().getId());
        basket.setCount(basket.getCount()+1);
        basketRepository.save(basket);
    }

    public void minus_amount(Long id) {
        Basket basket = basketRepository.findByIdbook((bookRepository.findById(id)).get().getId());
        if(basket.getCount()!=1) basket.setCount(basket.getCount()-1);
        basketRepository.save(basket);
    }

    public List<Integer> findCount() {
        List<Basket>baskets =  basketRepository.findAll();
        List<Integer> counts = new ArrayList<>();

        for(Basket basket: baskets){
            counts.add(basket.getCount());
        }
        return counts;
    }

    public void deleteAll() {
        basketRepository.deleteAll();
    }
}
