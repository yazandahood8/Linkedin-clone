package com.example.jobservice.controller;

import com.example.jobservice.dto.ApplicationRequestDTO;
import com.example.jobservice.dto.ApplicationResponseDTO;
import com.example.jobservice.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs/{jobId}/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * Submit an application for a specific job.
     */
    @PostMapping
    public ResponseEntity<ApplicationResponseDTO> submitApplication(
            @PathVariable Long jobId,
            @RequestBody ApplicationRequestDTO request) {
        // Set the jobId in the request object for consistency
        request.setJobId(jobId);

        // Pass the request and jobId to the service layer
        ApplicationResponseDTO response = applicationService.submitApplication(request);

        // Return the response with status 200 OK
        return ResponseEntity.ok(response);
    }


    /**
     * Get all applications for a specific job.
     */
    @GetMapping
    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsForJob(@PathVariable Long jobId) {
        List<ApplicationResponseDTO> applications = applicationService.getApplicationsForJob(jobId);
        return ResponseEntity.ok(applications);
    }

    /**
     * Delete a specific application by its ID.
     */
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long applicationId) {
        applicationService.deleteApplication(applicationId);
        return ResponseEntity.noContent().build();
    }
}
