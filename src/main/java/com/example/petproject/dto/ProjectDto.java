package com.example.petproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
@Data
public class ProjectDto implements Serializable {

    @NotBlank
    @Size(min = 2,max = 25)
    private String projectName;
    private Boolean favourite;
    private Set<Integer> campaigns = new LinkedHashSet<>();

}
