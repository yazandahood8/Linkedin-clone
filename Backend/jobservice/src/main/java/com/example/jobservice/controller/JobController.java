package com.example.jobservice.controller;

import com.example.jobservice.dto.JobRequestDTO;
import com.example.jobservice.dto.JobResponseDTO;
import com.example.jobservice.service.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /**
     * Create a new job post.
     */
    @PostMapping
    public ResponseEntity<JobResponseDTO> createJob(@RequestBody JobRequestDTO request) {
        JobResponseDTO response = jobService.createJob(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get details of a specific job post.
     */
    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponseDTO> getJobById(@PathVariable Long jobId) {
        JobResponseDTO job = jobService.getJobById(jobId);
        return ResponseEntity.ok(job);
    }

    /**
     * Get a list of all job posts.
     */
    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> getAllJobs() {
        List<JobResponseDTO> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    /**
     * Update an existing job post.
     */
    @PutMapping("/{jobId}")
    public ResponseEntity<JobResponseDTO> updateJob(
            @PathVariable Long jobId,
            @RequestBody JobRequestDTO request) {
        JobResponseDTO updatedJob = jobService.updateJob(jobId, request);
        return ResponseEntity.ok(updatedJob);
    }

    /**
     * Delete a specific job post by its ID.
     */
    @DeleteMapping("/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.noContent().build();
    }
}
