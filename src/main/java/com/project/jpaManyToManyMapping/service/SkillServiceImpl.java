package com.project.jpaManyToManyMapping.service;

import com.project.jpaManyToManyMapping.exception.SkillAlreadyExistException;
import com.project.jpaManyToManyMapping.exception.SkillNotFoundException;
import com.project.jpaManyToManyMapping.model.JobSeeker;
import com.project.jpaManyToManyMapping.model.Skill;
import com.project.jpaManyToManyMapping.repository.JobSeekerRepository;
import com.project.jpaManyToManyMapping.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {
    private SkillRepository skillRepository;
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository, JobSeekerRepository jobSeekerRepository) {
        this.skillRepository = skillRepository;
        this.jobSeekerRepository = jobSeekerRepository;

    }

    @Override
    public Skill saveSkill(Skill skill) throws SkillAlreadyExistException {
        if (skill.getSkillId() == 0) {
            return skillRepository.save(skill);
        }
        if (skillRepository.findById(skill.getSkillId()).isPresent()) {
            throw new SkillAlreadyExistException("Skill Already Exists");
        } else {
            return skillRepository.save(skill);
        }
    }

    @Override
    public Skill getSkill(int skillId) throws SkillNotFoundException {
        if (!skillRepository.findById(skillId).isPresent()) {
            throw new SkillNotFoundException("Skill Not Found");
        } else {
            return skillRepository.findById(skillId).get();
        }
    }


    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }


}

