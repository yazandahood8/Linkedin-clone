package com.example.jobservice.utils;

import com.example.jobservice.dto.ApplicationRequestDTO;
import com.example.jobservice.dto.ApplicationResponseDTO;
import com.example.jobservice.model.Application;
import com.example.jobservice.model.Job;

public class ApplicationMapper {

    /**
     * Converts ApplicationRequestDTO to Application entity.
     * Associates the Application with a specific Job entity.
     *
     * @param dto ApplicationRequestDTO containing application details
     * @param job The associated Job entity
     * @return Application entity
     */
    public static Application toEntity(ApplicationRequestDTO dto, Job job) {
        Application application = new Application();
        application.setApplicantName(dto.getApplicantName());
        application.setApplicantEmail(dto.getApplicantEmail());
        application.setResumeUrl(dto.getResumeUrl());
        application.setJob(job); // Associate application with the Job entity
        return application;
    }

    /**
     * Converts Application entity to ApplicationResponseDTO.
     *
     * @param application Application entity
     * @return ApplicationResponseDTO containing application details
     */
    public static ApplicationResponseDTO toDTO(Application application) {
        ApplicationResponseDTO dto = new ApplicationResponseDTO();
        dto.setId(application.getId());
        dto.setApplicantName(application.getApplicantName());
        dto.setApplicantEmail(application.getApplicantEmail());
        dto.setResumeUrl(application.getResumeUrl());
        dto.setJobId(application.getJob().getId()); // Get the associated Job ID
        dto.setAppliedAt(application.getAppliedAt());
        return dto;
    }
}
