package com.example.userservice.service;

import com.example.userservice.dto.*;
import com.example.userservice.exception.*;
import com.example.userservice.model.*;
import com.example.userservice.repository.*;
import com.example.userservice.utils.JwtUtil;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ExperienceRepository experienceRepository;
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       ExperienceRepository experienceRepository,
                       EducationRepository educationRepository,
                       SkillRepository skillRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.experienceRepository = experienceRepository;
        this.educationRepository = educationRepository;
        this.skillRepository = skillRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ---------------------------
    //        USER METHODS
    // ---------------------------

    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    
    
    public User updateUser(Long userId, User updatedUser) {
        User existingUser = getUserById(userId);

        // Update allowed fields
        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setHeadline(updatedUser.getHeadline());
        existingUser.setAbout(updatedUser.getAbout());
        existingUser.setLocation(updatedUser.getLocation());
        existingUser.setProfilePictureUrl(updatedUser.getProfilePictureUrl());
        existingUser.setBackgroundImageUrl(updatedUser.getBackgroundImageUrl());
       // existingUser.setExperiences(updatedUser.getExperiences());

        // Update password if provided
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
    
    
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = JwtUtil.generateToken(user.getEmail());
            return new LoginResponseDTO("Login successful", true, token);
        } else {
            return new LoginResponseDTO("Invalid email or password", false, null);
        }
    }

    // ---------------------------
    //     EXPERIENCE METHODS
    // ---------------------------

    
    public List<Experience> getAllExperiences() {
        return experienceRepository.findAll();
    }
    
    
    public Experience getExperienceById(Long experienceId) {
        return experienceRepository.findById(experienceId)
                .orElseThrow(() -> new RuntimeException("Experience not found with ID: " + experienceId));
    }
    @Transactional
    public Experience addExperience(Long userId, Experience experience) {
        // Retrieve the associated user by ID
        User user = getUserById(userId);

        // Set the user for the experience
        experience.setUser(user);
        // Save and return the experience
        return experienceRepository.save(experience);
    }

    public Experience updateExperience(Long experienceId, Experience updatedExperience) {
        Experience existingExp = experienceRepository.findById(experienceId)
                .orElseThrow(() -> new RuntimeException("Experience not found: " + experienceId));

        existingExp.setPosition(updatedExperience.getPosition());
        existingExp.setCompany(updatedExperience.getCompany());
        existingExp.setStartDate(updatedExperience.getStartDate());
        existingExp.setEndDate(updatedExperience.getEndDate());
        existingExp.setLocation(updatedExperience.getLocation());
        existingExp.setDescription(updatedExperience.getDescription());

        return experienceRepository.save(existingExp);
    }

    public void deleteExperience(Long experienceId) {
        if (!experienceRepository.existsById(experienceId)) {
            throw new RuntimeException("Experience not found: " + experienceId);
        }
        experienceRepository.deleteById(experienceId);
    }

    // ---------------------------
    //      EDUCATION METHODS
    // ---------------------------
    
    public List<Education> getAllEducations() {
        return educationRepository.findAll();
    }
    
    public Education getEducationById(Long educationId) {
        return educationRepository.findById(educationId)
                .orElseThrow(() -> new RuntimeException("Education not found with ID: " + educationId));
    }
    
    
    
    public Education addEducation(Long userId, Education education) {
        User user = getUserById(userId);
        education.setUser(user);
        return educationRepository.save(education);
    }

    public Education updateEducation(Long educationId, Education updatedEducation) {
        Education existingEdu = educationRepository.findById(educationId)
                .orElseThrow(() -> new RuntimeException("Education not found: " + educationId));

        existingEdu.setSchool(updatedEducation.getSchool());
        existingEdu.setDegree(updatedEducation.getDegree());
        existingEdu.setFieldOfStudy(updatedEducation.getFieldOfStudy());
        existingEdu.setStartDate(updatedEducation.getStartDate());
        existingEdu.setEndDate(updatedEducation.getEndDate());
        existingEdu.setDescription(updatedEducation.getDescription());

        return educationRepository.save(existingEdu);
    }

    public void deleteEducation(Long educationId) {
        if (!educationRepository.existsById(educationId)) {
            throw new RuntimeException("Education not found: " + educationId);
        }
        educationRepository.deleteById(educationId);
    }

    // ---------------------------
    //        SKILL METHODS
    // ---------------------------

    /**
     * Adds a skill to the user. If the skill does not exist, it is created. 
     */
    public User addSkillToUser(Long userId, String skillName) {
        User user = getUserById(userId);

        Skill skill = skillRepository.findAll()
                .stream()
                .filter(s -> s.getName().equalsIgnoreCase(skillName))
                .findFirst()
                .orElse(new Skill());
        
        if (skill.getId() == null) {
            skill.setName(skillName);
            skillRepository.save(skill);
        }

        user.getSkills().add(skill);
        return userRepository.save(user);
    }
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    /**
     * Removes a skill from the user (doesn't delete the skill from the DB if shared).
     */
    public User removeSkillFromUser(Long userId, Long skillId) {
        User user = getUserById(userId);
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found: " + skillId));

        user.getSkills().remove(skill);
        return userRepository.save(user);
    }
}
