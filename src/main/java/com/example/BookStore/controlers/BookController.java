package com.example.BookStore.controlers;

import com.example.BookStore.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BookController {
    @Autowired
    private BookServices bookServices;
    @GetMapping("/")
    public String openPage(){
        return "main";
    }

    @GetMapping("/add")
    public String addPage(){
        return "addBook";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam("file")MultipartFile file, @RequestParam String name, @RequestParam String description, @RequestParam int price, @RequestParam String author_book ){
        System.out.println("add post method");
        bookServices.saveBookToDB(file, name, description, price, author_book);
        return "redirect:/";
    }
}
