package com.example.petproject.repositories;

import com.example.petproject.data.Project;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProjectRepo extends JpaRepository<Project, Integer> {

}
