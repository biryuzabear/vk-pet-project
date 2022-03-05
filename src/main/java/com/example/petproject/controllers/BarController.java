package com.example.petproject.controllers;

import com.example.petproject.services.MainPageService;
import com.example.petproject.services.VKService;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class BarController {

    @Autowired
    VKService vkService;
    @Autowired
    private MainPageService mainPageService;

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }


    @GetMapping("/main")
    public String showMainPage(Model model) throws ClientException, ApiException {
        model.addAttribute("projects", mainPageService.getAllProjects(vkService.getUserId()))
                .addAttribute("userName",vkService.getUserName());
        return "main";
    }

    @GetMapping("/add")
    public String showAddPage(Model model){
        return "add";
    }

}
