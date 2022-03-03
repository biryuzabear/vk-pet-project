package com.example.petproject.services;

import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.objects.ads.Account;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;

@Service
@SessionScope
public class VKService {

    UserActor userActor;

    public void setUserActor(UserActor userActor) {
        this.userActor = userActor;
    }


    public boolean isLogin() {
        System.out.println(userActor);
        return userActor!=null;
    }


    public ArrayList<Account> getAccounts() {
        return
    }
}
