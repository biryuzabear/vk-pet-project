package com.example.petproject.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
@Data
public class ProjectDto implements Serializable {
    private String projectName;
    private Integer accountId;
    private Integer clientId;
    private Set<CampaignDto> campaigns = new LinkedHashSet<>();
    @Data
    public static class CampaignDto implements Serializable {
        private Integer id;
    }
}
