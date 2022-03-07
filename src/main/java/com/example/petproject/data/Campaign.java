package com.example.petproject.data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "campaigns")
@IdClass(Campaign.CampaignId.class)
public class Campaign {
    @Id
    @Column(name = "campaign_id", nullable = false)
    private Integer id;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Campaign() {
    }

    public static class CampaignId implements Serializable {

        private Integer id;
        private Integer project;

        public CampaignId(Integer id, Integer project) {
            this.id = id;
            this.project = project;
        }

        public CampaignId() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CampaignId that = (CampaignId) o;
            return Objects.equals(id, that.id) && Objects.equals(project, that.project);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, project);
        }
    }
}