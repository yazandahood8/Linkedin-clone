package com.example.jobservice.utils;

import com.example.jobservice.dto.JobRequestDTO;
import com.example.jobservice.dto.JobResponseDTO;
import com.example.jobservice.model.Job;

public class JobMapper {

    public static Job toEntity(JobRequestDTO dto) {
        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());
        job.setType(dto.getType());
        job.setEmployerId(dto.getEmployerId());
        return job;
    }

    public static JobResponseDTO toDTO(Job job) {
        JobResponseDTO dto = new JobResponseDTO();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setLocation(job.getLocation());
        dto.setType(job.getType());
        dto.setEmployerId(job.getEmployerId());
        dto.setCreatedAt(job.getCreatedAt());
        dto.setUpdatedAt(job.getUpdatedAt());
        return dto;
    }
}
