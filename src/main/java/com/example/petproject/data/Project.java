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

    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "favourite")
    private Boolean favourite;

    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private Set<Campaign> campaigns = new LinkedHashSet<>();

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean allCampaigns) {
        this.favourite = allCampaigns;
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

    public Integer getClientId() { return clientId; }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Project() {
    }

    public String getCampaignsAsString() {
        StringBuilder campaignsString = new StringBuilder("[");
        for (Campaign campaign : campaigns) {
            campaignsString.append(campaign.getId()).append(",");
        }
        campaignsString.deleteCharAt(campaignsString.length()-1);
        campaignsString.append("]");
        return campaignsString.toString();
    }
}