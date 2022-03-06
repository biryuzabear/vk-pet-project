package com.example.petproject.controllers;

import com.example.petproject.data.Campaign;
import com.example.petproject.data.Project;
import com.example.petproject.dto.ProjectDto;
import com.example.petproject.repositories.CampaignRepo;
import com.example.petproject.repositories.ProjectRepo;
import com.example.petproject.services.ProjectService;
import com.example.petproject.services.VKService;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/add")
public class AddingProjectController {

    @Autowired
    ProjectRepo projectRepo;
    @Autowired
    CampaignRepo campaignRepo;
    @Autowired
    VKService vkService;
    @Autowired
    private ProjectService projectService;

    @PostMapping()
    public String add(@ModelAttribute("projectDto") ProjectDto projectDto) {
        Project project = new Project();

        for (ProjectDto.CampaignDto campaignDto : projectDto.getCampaigns()) {
            Campaign campaign = new Campaign();
            campaign.setId(campaignDto.getId());
            campaignRepo.save(campaign);
        }

        project.setUserId(vkService.getUserId());
        project.setAccountId(projectDto.getAccountId());
        project.setProjectName(projectDto.getProjectName());
        project.setClientId(projectDto.getClientId());
        project.setFavourite(false);
        projectRepo.save(project);

        return "redirect:/main";
    }

    @ModelAttribute
    public void barInfo(Model model) throws ClientException, ApiException {
        model.addAttribute("projects", projectService.getAllProjects(vkService.getUserId()))
                .addAttribute("userName",vkService.getUserName());
    }

    @GetMapping("/{accountId}")
    public String getClients(@PathVariable Integer accountId, Model model) throws ClientException, ApiException {
        model.addAttribute("clients", vkService.getClients(accountId));
        return "choose_client";
    }
}
