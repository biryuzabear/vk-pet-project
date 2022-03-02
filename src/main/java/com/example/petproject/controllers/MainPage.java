package com.example.petproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPage {

    @GetMapping("/login")
    public String mainPage(){
        return "login";
    }


}
