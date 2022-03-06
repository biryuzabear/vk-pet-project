package com.example.petproject.services;

import com.example.petproject.data.Project;
import com.example.petproject.repositories.CampaignRepo;
import com.example.petproject.repositories.ProjectRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    final
    CampaignRepo campaignRepo;

    final
    ProjectRepo projectRepo;

    public ProjectService(CampaignRepo campaignRepo, ProjectRepo projectRepo) {
        this.campaignRepo = campaignRepo;
        this.projectRepo = projectRepo;
    }

    public List<Project> getAllProjects(Integer userId) {
        return projectRepo.findByUserId(userId);
    }



}
