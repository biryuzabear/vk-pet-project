package com.example.petproject.services;
import com.example.petproject.data.Project;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.ads.responses.GetAccountsResponse;
import com.vk.api.sdk.objects.ads.responses.GetCampaignsResponse;
import com.vk.api.sdk.objects.ads.responses.GetClientsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class VKService {

    private UserActor userActor;
    private VkApiClient vk;
    private String username;
    private int userId;

    public void setUserActor(UserActor userActor) {
        this.userActor = userActor;
    }

    public void setVk(VkApiClient vk) {
        this.vk = vk;
    }

    public boolean isLogin() {
        return userActor != null && vk != null;
    }

    public List<GetAccountsResponse> getAccounts() throws ClientException, ApiException {
        return vk.ads().getAccounts(userActor).execute();
    }


    public int getUserId() {
        if(userId == 0)
            userId = userActor.getId();
            return userId;
    }

    public String getUserName() throws ClientException, ApiException {
        if(username==null)
        username =  vk.users()
                .get(userActor)
                .userIds(String.valueOf(userActor.getId()))
                .execute().get(0).getFirstName() + " " +
                vk.users()
                .get(userActor)
                .userIds(String.valueOf(userActor.getId()))
                .execute().get(0).getLastName();
        return username;
    }


    public List<GetClientsResponse> getClients(Integer accountId) throws ClientException, ApiException {
        return vk.ads().getClients(userActor,accountId).execute();
    }

    public List<GetCampaignsResponse> getAllCampaigns(Integer accountId, Integer clientId) throws ClientException, ApiException {
        return vk.ads()
                .getCampaigns(userActor, accountId)
                .clientId(clientId).execute();
    }
    public List<GetCampaignsResponse> getCampaigns(Project project) throws ClientException, ApiException {
        if (project.getCampaigns().isEmpty())
        return getAllCampaigns(project.getAccountId(),project.getClientId());
        else return getCampaigns(project.getAccountId(),project.getClientId(),project.getCampaignsAsString());
    }

    public List<GetCampaignsResponse> getCampaigns(Integer accountId, Integer clientId, String campaignsIds) throws ClientException, ApiException {
        return vk.ads()
                .getCampaigns(userActor, accountId)
                .clientId(clientId)
                .campaignIds(campaignsIds)
                .execute();
    }
}
