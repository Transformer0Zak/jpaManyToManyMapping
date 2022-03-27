package com.project.jpaManyToManyMapping.service;

import com.project.jpaManyToManyMapping.exception.JobSeekerAlreadyExistException;
import com.project.jpaManyToManyMapping.exception.JobSeekerNotFoundException;
import com.project.jpaManyToManyMapping.model.JobSeeker;
import com.project.jpaManyToManyMapping.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    public JobSeekerServiceImpl(JobSeekerRepository jobSeekerRepository) {
        this.jobSeekerRepository = jobSeekerRepository;
    }

    @Override
    public JobSeeker saveJobSeeker(JobSeeker jobSeeker) throws JobSeekerAlreadyExistException {
        if (jobSeeker.getJobSeekerId() == 0) {
            return jobSeekerRepository.save(jobSeeker);
        }
        if (jobSeekerRepository.findById(jobSeeker.getJobSeekerId()).isPresent()) {
            throw new JobSeekerAlreadyExistException("JobSeeker Already Exists");
        } else {
            return jobSeekerRepository.save(jobSeeker);
        }
    }

    @Override
    public JobSeeker getJobSeeker(int jobSeekerId) throws JobSeekerNotFoundException {
        if (!jobSeekerRepository.findById(jobSeekerId).isPresent()) {
            throw new JobSeekerNotFoundException("JobSeeker Not Found");
        } else {
            return jobSeekerRepository.findById(jobSeekerId).get();
        }
    }


    @Override
    public List<JobSeeker> getAllJobSeekers() {
        return jobSeekerRepository.findAll();
    }

}
