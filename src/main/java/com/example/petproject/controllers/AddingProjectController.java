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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/add")
public class AddingProjectController {

    final
    ProjectRepo projectRepo;
    final
    CampaignRepo campaignRepo;
    final
    VKService vkService;
    private final ProjectService projectService;

    public AddingProjectController(ProjectRepo projectRepo, CampaignRepo campaignRepo, VKService vkService, ProjectService projectService) {
        this.projectRepo = projectRepo;
        this.campaignRepo = campaignRepo;
        this.vkService = vkService;
        this.projectService = projectService;
    }

    @PostMapping("/account/{accountId}/client/{clientId}")
    public String add(@PathVariable Integer accountId, @PathVariable Integer clientId, @Valid ProjectDto projectDto, BindingResult bindingResult, Model model) throws ClientException, ApiException {

        if (bindingResult.hasErrors()) {
            model.addAttribute("campaigns", vkService.getAllCampaigns(accountId, clientId));
            return "choose_campaigns";
        }


        Project project = new Project();

        project.setUserId(vkService.getUserId());
        project.setAccountId(accountId);
        project.setClientId(clientId);
        project.setProjectName(projectDto.getProjectName());
        project.setFavourite(projectDto.getFavourite());
        projectRepo.save(project);

        for (Integer id : projectDto.getCampaigns()) {
            Campaign campaign = new Campaign();
            campaign.setId(id);
            campaign.setProject(project);
            campaignRepo.save(campaign);
        }

        return "redirect:/main";
    }

    @ModelAttribute
    public void barInfo(Model model) throws ClientException, ApiException {
        model.addAttribute("projects", projectService.getAllProjects(vkService.getUserId()))
                .addAttribute("userName", vkService.getUserName());
    }

    @GetMapping("/account/{accountId}")
    public String getClients(@PathVariable Integer accountId, Model model) throws ClientException, ApiException {
        model.addAttribute("clients", vkService.getClients(accountId));
        return "choose_client";
    }

    @GetMapping("/account/{accountId}/client/{clientId}")
    public String getCampaigns(@PathVariable Integer accountId, @PathVariable Integer clientId, Model model) throws ClientException, ApiException {
        model.addAttribute("campaigns", vkService.getAllCampaigns(accountId, clientId))
                .addAttribute("projectDto", new ProjectDto());
        return "choose_campaigns";
    }


}
