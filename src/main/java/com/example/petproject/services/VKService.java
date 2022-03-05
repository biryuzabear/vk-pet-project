package com.example.petproject.services;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.ads.Account;
import com.vk.api.sdk.objects.ads.responses.GetAccountsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class VKService {

    UserActor userActor;
    VkApiClient vk;

    public void setUserActor(UserActor userActor) {
        this.userActor = userActor;
    }

    public void setVk(VkApiClient vk) {
        this.vk = vk;
    }

    public boolean isLogin() {
        return userActor != null && vk!=null;
    }


    public List<GetAccountsResponse> getAccounts() throws ClientException, ApiException {
        return  vk.ads().getAccounts(userActor).execute();
    }


    public int getUserId() {
        return userActor.getId();
    }
}
