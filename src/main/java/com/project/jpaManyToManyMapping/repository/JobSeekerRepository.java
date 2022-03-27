package com.project.jpaManyToManyMapping.repository;

import com.project.jpaManyToManyMapping.model.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker,Integer> {
//    List <jobSeeker> findJobSeekerById(int jobSeekerId);
}
