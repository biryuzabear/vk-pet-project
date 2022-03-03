package com.example.petproject.controllers;

import com.example.petproject.services.VKService;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPage {

    private static final Integer APP_ID = 8091762;
    private static final String CLIENT_SECRET = "EDWu1sdfJzBuBre10ueC";
    private static final String REDIRECT_URI = "http://localhost:8080/login";

    TransportClient transportClient = new HttpTransportClient();
    VkApiClient vk = new VkApiClient(transportClient);

    @Autowired
    VKService vkService;

    @GetMapping("/login")
    public String login(@RequestParam String code, Model model) throws ClientException, ApiException {
        UserAuthResponse authResponse = vk.oAuth()
                .userAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                .execute();

        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        vkService.setUserActor(actor);

        model.addAttribute("allAccounts", vkService.getAccounts());

        return "redirect:/";
    }

    @GetMapping("/")
    public String mainPage() {

        if (!vkService.isLogin())
            return "redirect:https://oauth.vk.com/authorize" +
                    "?client_id=8091762" +
                    "&display=page" +
                    "&redirect_uri=http://localhost:8080/login" +
                    "&scope=ads" +
                    "&response_type=code" +
                    "&v=5.131";

        return "mainPage";

    }


}
