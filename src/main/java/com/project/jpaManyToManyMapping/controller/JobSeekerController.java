package com.project.jpaManyToManyMapping.controller;


import com.project.jpaManyToManyMapping.exception.JobSeekerAlreadyExistException;
import com.project.jpaManyToManyMapping.exception.JobSeekerNotFoundException;
import com.project.jpaManyToManyMapping.model.JobSeeker;
import com.project.jpaManyToManyMapping.service.JobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class JobSeekerController {
    private JobSeekerService jobSeekerService;
    private ResponseEntity responseEntity;

    @Autowired
    public JobSeekerController(JobSeekerService jobSeekerService) {
        this.jobSeekerService = jobSeekerService;
    }

    @PostMapping("/jobSeeker")
    public ResponseEntity saveJobSeeker(@RequestBody JobSeeker jobSeeker) throws JobSeekerAlreadyExistException {
        try {
            JobSeeker jobSeeker1 = jobSeekerService.saveJobSeeker(jobSeeker);
            responseEntity = new ResponseEntity(jobSeeker1, HttpStatus.CREATED);
        } catch (JobSeekerAlreadyExistException e) {
            throw new JobSeekerAlreadyExistException("JobSeeker Already Exist");
        } catch (Exception ex) {
            responseEntity = new ResponseEntity("Internal Server Error Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/jobSeekers")
    public ResponseEntity<?> getAllJobSeekers() {
        try {
            List<JobSeeker> jobSeekerList = jobSeekerService.getAllJobSeekers();
            responseEntity = new ResponseEntity<>(jobSeekerList, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/jobSeeker/{jobSeekerId}")
    public ResponseEntity getJobSeeker(@PathVariable("jobSeekerId") int jobSeekerId) throws JobSeekerNotFoundException {
        try {
            JobSeeker jobSeeker = jobSeekerService.getJobSeeker(jobSeekerId);
            return responseEntity = new ResponseEntity(jobSeeker, HttpStatus.OK);
        } catch (JobSeekerNotFoundException e) {
            throw new JobSeekerNotFoundException("JobSeeker Not Found");

        } catch (Exception ex) {
            responseEntity = new ResponseEntity("Internal Server Error Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}








