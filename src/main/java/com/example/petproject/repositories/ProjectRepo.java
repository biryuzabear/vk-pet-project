package com.example.petproject.repositories;

import com.example.petproject.data.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {

    List<Project> findByUserId(Integer userId);
    Optional<Project> findById(Integer id);

}
