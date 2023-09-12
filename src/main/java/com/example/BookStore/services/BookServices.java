package com.example.BookStore.services;

import com.example.BookStore.models.Book;
import com.example.BookStore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class BookServices {
    @Autowired
    private BookRepository repository;

    public void saveBookToDB(MultipartFile file, String name, String description, int price, String authorBook) {
        Book book = new Book();
        String filename= StringUtils.cleanPath(file.getOriginalFilename());
        if(filename.contains("..")) System.out.println("Not a valid file");
        try {
            book.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        book.setName(name);
        book.setDescription(description);
        book.setPrice(price);
        book.setAuthor_book(authorBook);
        book.setAuthor_card("none");

        repository.save(book);



    }
}
