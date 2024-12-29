package com.example.jobservice.service;

import com.example.jobservice.dto.ApplicationRequestDTO;
import com.example.jobservice.dto.ApplicationResponseDTO;
import com.example.jobservice.exception.ApplicationNotFoundException;
import com.example.jobservice.exception.JobNotFoundException;
import com.example.jobservice.model.Application;
import com.example.jobservice.model.Job;
import com.example.jobservice.repository.ApplicationRepository;
import com.example.jobservice.repository.JobRepository;
import com.example.jobservice.utils.ApplicationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    public ApplicationService(ApplicationRepository applicationRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    public ApplicationResponseDTO submitApplication(ApplicationRequestDTO request) {
        // Validate job existence
        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new JobNotFoundException("Job with id " + request.getJobId() + " not found."));

        // Map request to entity
        Application application = ApplicationMapper.toEntity(request, job);
        application = applicationRepository.save(application);

        // Return response DTO
        return ApplicationMapper.toDTO(application);
    }

    public List<ApplicationResponseDTO> getApplicationsForJob(Long jobId) {
        return applicationRepository.findByJobId(jobId).stream()
                .map(ApplicationMapper::toDTO)
                .collect(Collectors.toList());
    }


    public void deleteApplication(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new ApplicationNotFoundException("Application with id " + id + " not found.");
        }
        applicationRepository.deleteById(id);
    }
}
