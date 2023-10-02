package com.example.BookStore.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {


    @GetMapping("/basket/order")
    public String orderPage(){

        return "order";
    }
}
