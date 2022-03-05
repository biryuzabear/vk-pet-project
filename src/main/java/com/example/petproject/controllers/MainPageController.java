package com.example.petproject.controllers;

import com.example.petproject.services.MainPageService;
import com.example.petproject.services.VKService;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class MainPageController {

    final
    MainPageService mainPageService;

    final
    VKService vkService;

    public MainPageController(MainPageService mainPageService, VKService vkService) {
        this.mainPageService = mainPageService;
        this.vkService = vkService;
    }

    @GetMapping
    public String main(Model model) {
        model.addAttribute("projects", mainPageService.getAllProjects(vkService.getUserId()));
        return "mainPage";
    }



}