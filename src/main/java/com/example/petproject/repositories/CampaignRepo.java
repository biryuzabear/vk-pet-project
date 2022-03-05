package com.example.petproject.repositories;

import com.example.petproject.data.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

interface CampaignRepo extends JpaRepository<Campaign, Integer> {

}
