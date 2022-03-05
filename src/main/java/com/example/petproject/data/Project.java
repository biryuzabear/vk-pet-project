package com.example.petproject.data;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", nullable = false)
    private Integer id;

    @Column(name = "project_name", nullable = false, length = 30)
    private String projectName;

    @Column(name = "user_id", length = 12)
    private Integer userId;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "all_campaigns")
    private Boolean allCampaigns;

    @OneToMany(mappedBy = "project")
    private Set<Campaign> campaigns = new LinkedHashSet<>();

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public Boolean getAllCampaigns() {
        return allCampaigns;
    }

    public void setAllCampaigns(Boolean allCampaigns) {
        this.allCampaigns = allCampaigns;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Project() {
    }
}