package com.project.jpaManyToManyMapping.controller;


import com.project.jpaManyToManyMapping.exception.SkillAlreadyExistException;
import com.project.jpaManyToManyMapping.exception.SkillNotFoundException;
import com.project.jpaManyToManyMapping.model.JobSeeker;
import com.project.jpaManyToManyMapping.model.Skill;
import com.project.jpaManyToManyMapping.repository.JobSeekerRepository;
import com.project.jpaManyToManyMapping.repository.SkillRepository;
import com.project.jpaManyToManyMapping.service.JobSeekerService;
import com.project.jpaManyToManyMapping.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SkillController {
    private ResponseEntity responseEntity;
    private SkillService skillService;
    private SkillRepository skillRepository;
    private JobSeekerRepository jobSeekerRepository;


    @Autowired
    public SkillController(SkillService skillService, SkillRepository skillRepository, JobSeekerRepository jobSeekerRepository) {
        this.skillService = skillService;
        this.jobSeekerRepository = jobSeekerRepository;
        this.skillRepository = skillRepository;

    }

    @PostMapping("/skill")
    public ResponseEntity saveJobSeeker(@RequestBody Skill skill) throws SkillAlreadyExistException {
        try {
            Skill skill1 = skillService.saveSkill(skill);
            responseEntity = new ResponseEntity(skill1, HttpStatus.CREATED);
        } catch (SkillAlreadyExistException e) {
            throw new SkillAlreadyExistException("Skill Already Exist");
        } catch (Exception ex) {
            responseEntity = new ResponseEntity("Internal Server Error Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/skills")
    public ResponseEntity<?> getAllSkills() {
        try {
            List<Skill> skillList = skillService.getAllSkills();
            responseEntity = new ResponseEntity<>(skillList, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Error Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/skill/{skillId}")
    public ResponseEntity getSkill(@PathVariable("skillId") int skillId) throws SkillNotFoundException {
        try {
            Skill skill = skillService.getSkill(skillId);
            return responseEntity = new ResponseEntity(skill, HttpStatus.OK);
        } catch (SkillNotFoundException e) {
            throw new SkillNotFoundException("Skill Not Found");

        } catch (Exception ex) {
            responseEntity = new ResponseEntity("Internal Server Error Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @PostMapping("/jobSeeker/{jobSeekerId}/skills")
    public ResponseEntity<Skill> addSkill(@PathVariable("jobSeekerId") int jobSeekerId, @RequestBody Skill skillRequest) throws SkillNotFoundException {
        Skill skill = jobSeekerRepository.findById(jobSeekerId).map(jobSeeker -> {
            int skillId = skillRequest.getSkillId();


            if (skillId != 0) {

                Skill skill1 = null;
                try {
                    skill1 = skillRepository.findById(skillId).orElseThrow(() -> new SkillNotFoundException("Skill NOt Found"));
                } catch (SkillNotFoundException e) {
                    e.printStackTrace();
                }
                jobSeeker.addSkill(skill1);
                jobSeekerRepository.save(jobSeeker);
                return skill1;
            }

                jobSeeker.addSkill(skillRequest);
                return skillRepository.save(skillRequest);
            }).orElseThrow(()-> new SkillNotFoundException("Skill Not Found"));


        return new ResponseEntity<>(skill, HttpStatus.CREATED);

    }
}
