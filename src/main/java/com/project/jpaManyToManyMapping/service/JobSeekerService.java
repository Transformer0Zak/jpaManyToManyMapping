package com.project.jpaManyToManyMapping.service;

import com.project.jpaManyToManyMapping.exception.JobSeekerAlreadyExistException;
import com.project.jpaManyToManyMapping.exception.JobSeekerNotFoundException;
import com.project.jpaManyToManyMapping.model.JobSeeker;

import java.util.List;

public interface JobSeekerService {
    JobSeeker  saveJobSeeker (JobSeeker jobSeeker) throws JobSeekerAlreadyExistException;
    JobSeeker getJobSeeker (int jobSeekerId) throws JobSeekerNotFoundException;
    List<JobSeeker> getAllJobSeekers();

}
