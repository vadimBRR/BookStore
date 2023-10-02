package com.example.BookStore.controlers;

import com.example.BookStore.models.Basket;
import com.example.BookStore.models.Book;
import com.example.BookStore.services.BasketServices;
import com.example.BookStore.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookServices bookServices;
    @Autowired
    private BasketServices basketServices;
    @GetMapping("/")
    public String openPage(){
        return "main";
    }


    @GetMapping("/add")
    public String addPage(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "addBook";
    }

    @GetMapping("/books")
    public String booksPage(Model model){
        List<Book> books = bookServices.findAll();
        List<Book> baskets = basketServices.findAll();
        List<Boolean> is_basket = new ArrayList<>();
        int index = 0;
        for(Book book: books){
            is_basket.add(false);
            if(baskets.contains(book)) is_basket.set(index, true);
            index++;
//            for(Book basket: baskets){
//                if(book.getId().equals(basket.getId_book())){
//                    is_basket.set(index, 0);
//                    break;
//                }
//            }
        }
        System.out.println(is_basket);
        model.addAttribute("books", bookServices.findAll());
        model.addAttribute("is_basket", is_basket);
        return "books";
    }

    @PostMapping("/add")
    public String addBook(@RequestParam("file")MultipartFile file, @RequestParam String name, @RequestParam String description, @RequestParam int price, @RequestParam String author_book ){
        System.out.println("add post method");
        bookServices.saveBookToDB(file, name, description, price, author_book);
        return "redirect:/";
    }
}
