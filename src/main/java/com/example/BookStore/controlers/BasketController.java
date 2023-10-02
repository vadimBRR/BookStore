package com.example.BookStore.controlers;

import com.example.BookStore.services.BasketServices;
import com.example.BookStore.services.BookServices;
import org.aspectj.apache.bcel.classfile.LineNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BasketController {
    @Autowired
    private BasketServices basketServices;


    @GetMapping("/basket")
    public String BasketPage(Model model){
        model.addAttribute("books", basketServices.findAll());
        model.addAttribute("count", basketServices.findCount());
        return "basket";
    }

    @PostMapping("/add_basket/{id}")
    public String addBook(@PathVariable Long id){
        System.out.println("Id - " + id);
        basketServices.addItem(id);
        return "redirect:/books";
    }

    @PostMapping("/delete_basket/{id}/{url}")
    public String deleteFromBasket(@PathVariable Long id, @PathVariable String url){
        System.out.println("Remove by id" + id);
        basketServices.removeItem(id);
        if(url.equals("basket")) return "redirect:/basket";
        return "redirect:/books";
    }

    @PostMapping("/plus_amount/{id}")
    public String plus_amount(@PathVariable Long id){
        basketServices.plus_amount(id);
        return "redirect:/basket";
    }
    @PostMapping("/minus_amount/{id}")
    public String minus_amount(@PathVariable Long id){
        basketServices.minus_amount(id);
        return "redirect:/basket";
    }

    @PostMapping("/basket/clear_all")
    public String clearBasket(){
        basketServices.deleteAll();
        return "redirect:/basket";
    }
}
