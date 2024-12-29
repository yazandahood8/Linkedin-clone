package com.example.userservice.repository;

import com.example.userservice.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    /**
     * Find a Skill by name, ignoring case.
     * Example usage: skillRepository.findByNameIgnoreCase("Java");
     */
    Optional<Skill> findByNameIgnoreCase(String name);
}
