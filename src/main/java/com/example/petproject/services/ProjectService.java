package com.example.petproject.services;

import com.example.petproject.data.Campaign;
import com.example.petproject.data.Project;
import com.example.petproject.dto.CampaignDto;
import com.example.petproject.repositories.CampaignRepo;
import com.example.petproject.repositories.ProjectRepo;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.ads.responses.GetCampaignsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    final
    CampaignRepo campaignRepo;

    final
    ProjectRepo projectRepo;

    final
    VKService vkService;

    public ProjectService(CampaignRepo campaignRepo, ProjectRepo projectRepo, VKService vkService) {
        this.campaignRepo = campaignRepo;
        this.projectRepo = projectRepo;
        this.vkService = vkService;
    }

    public List<Project> getAllProjects(Integer userId) {
        return projectRepo.findByUserId(userId);
    }

    public Optional<Project> getProject(Integer projectId) {
        return projectRepo.findById(projectId);
    }


    public List<CampaignDto> getCampaigns(Project project) throws ClientException, ApiException {
        List<CampaignDto> campaignDtoList = new ArrayList<>();

        for (GetCampaignsResponse response : vkService.getCampaigns(project)) {
            CampaignDto campaignDto = new CampaignDto();
            campaignDto.setId(response.getId());
            campaignDto.setName(response.getName());
            campaignDtoList.add(campaignDto);
        }

        return campaignDtoList;
    }
}
