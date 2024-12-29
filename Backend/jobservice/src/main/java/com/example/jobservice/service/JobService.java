package com.example.jobservice.service;

import com.example.jobservice.dto.JobRequestDTO;
import com.example.jobservice.dto.JobResponseDTO;
import com.example.jobservice.exception.JobNotFoundException;
import com.example.jobservice.model.Job;
import com.example.jobservice.repository.JobRepository;
import com.example.jobservice.utils.JobMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public JobResponseDTO createJob(JobRequestDTO request) {
        Job job = JobMapper.toEntity(request);
        job = jobRepository.save(job);
        return JobMapper.toDTO(job);
    }

    public JobResponseDTO getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job with id " + id + " not found."));
        return JobMapper.toDTO(job);
    }

    public List<JobResponseDTO> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(JobMapper::toDTO)
                .collect(Collectors.toList());
    }

    public JobResponseDTO updateJob(Long id, JobRequestDTO request) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job with id " + id + " not found."));
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setLocation(request.getLocation());
        job.setType(request.getType());
        job = jobRepository.save(job);
        return JobMapper.toDTO(job);
    }

    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new JobNotFoundException("Job with id " + id + " not found.");
        }
        jobRepository.deleteById(id);
    }
}
