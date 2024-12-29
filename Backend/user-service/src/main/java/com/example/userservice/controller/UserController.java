package com.example.userservice.controller;

import com.example.userservice.dto.LoginRequestDTO;
import com.example.userservice.dto.LoginResponseDTO;
import com.example.userservice.dto.UserDTO;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.*;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ---------------------------
    //        USER ENDPOINTS
    // ---------------------------

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseEntity.ok(newUser);
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        LoginResponseDTO response = userService.login(loginRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

  
    // @GetMapping("/{userId}")
    // public ResponseEntity<User> getUser(@PathVariable Long userId) {
    //    User user = userService.getUserById(userId);
    //     return ResponseEntity.ok(user);
    // }


    
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId,
                                          @Valid @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);
        user.setId(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    // ---------------------------
    //     EXPERIENCE ENDPOINTS
    // ---------------------------

    
    @GetMapping("/{userId}/experience")
    public ResponseEntity<List<Experience>> getAllExperiences() {
        List<Experience> experiences = userService.getAllExperiences();
        return ResponseEntity.ok(experiences);
    }
    
    
    @PostMapping("/{userId}/experience")
    public ResponseEntity<Experience> addExperience(
            @PathVariable Long userId, 
            @Valid @RequestBody Experience experience) {
        Experience savedExp = userService.addExperience(userId, experience);
        return ResponseEntity.ok(savedExp);
    }
    
    
    @GetMapping("/{userId}/experience/{experienceId}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long userId, @PathVariable Long experienceId) {
        // Ensure the user exists (optional validation)
        userService.getUserById(userId);

        // Fetch the experience by ID
        Experience experience = userService.getExperienceById(experienceId);

        // Return the experience
        return ResponseEntity.ok(experience);
    }
    
    @PutMapping("/{userId}/experience/{experienceId}")
    public ResponseEntity<Experience> updateExperience(
            @PathVariable Long experienceId,
            @Valid @RequestBody Experience experience) {
        Experience updatedExp = userService.updateExperience(experienceId, experience);
        return ResponseEntity.ok(updatedExp);
    }

    @DeleteMapping("/experience/{experienceId}")
    public ResponseEntity<Void> deleteExperience(@PathVariable Long experienceId) {
        userService.deleteExperience(experienceId);
        return ResponseEntity.noContent().build();
    }

    // ---------------------------
    //      EDUCATION ENDPOINTS
    // ---------------------------
    
    
    
    @GetMapping("/{userId}/education")
    public ResponseEntity<List<Education>> getAllEducations() {
        List<Education> educations = userService.getAllEducations();
        return ResponseEntity.ok(educations);
    }
    
    
    
    
    @GetMapping("/{userId}/education/{educationId}")
    public ResponseEntity<Education> getEducationById(@PathVariable Long userId, @PathVariable Long educationId) {
        // Ensure the user exists (optional validation)
        userService.getUserById(userId);

        // Fetch the experience by ID
        Education education = userService.getEducationById(educationId);

        // Return the experience
        return ResponseEntity.ok(education);
    }

    @PostMapping("/{userId}/education")
    public ResponseEntity<Education> addEducation(
            @PathVariable Long userId, 
            @Valid @RequestBody Education education) {
        Education savedEdu = userService.addEducation(userId, education);
        return ResponseEntity.ok(savedEdu);
    }

    @PutMapping("/{userId}/education/{educationId}")
    public ResponseEntity<Education> updateEducation(
            @PathVariable Long educationId,
            @Valid @RequestBody Education education) {
        Education updatedEdu = userService.updateEducation(educationId, education);
        return ResponseEntity.ok(updatedEdu);
    }

    @DeleteMapping("/{userId}/education/{educationId}")
    public ResponseEntity<Void> deleteEducation(@PathVariable Long educationId) {
        userService.deleteEducation(educationId);
        return ResponseEntity.noContent().build();
    }

    // ---------------------------
    //        SKILL ENDPOINTS
    // ---------------------------

    @PostMapping("/{userId}/skills")
    public ResponseEntity<User> addSkillToUser(
            @PathVariable Long userId, 
            @RequestParam String skillName) {
        User user = userService.addSkillToUser(userId, skillName);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}/skills/{skillId}")
    public ResponseEntity<User> removeSkillFromUser(
            @PathVariable Long userId, 
            @PathVariable Long skillId) {
        User user = userService.removeSkillFromUser(userId, skillId);
        return ResponseEntity.ok(user);
    }
}
