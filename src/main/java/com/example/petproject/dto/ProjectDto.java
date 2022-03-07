package com.example.petproject.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
@Data
public class ProjectDto implements Serializable {
    private String projectName;
    private Boolean favourite;
    private Set<Integer> campaigns = new LinkedHashSet<>();

}
