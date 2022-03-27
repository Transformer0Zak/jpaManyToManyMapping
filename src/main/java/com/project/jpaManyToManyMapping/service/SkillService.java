package com.project.jpaManyToManyMapping.service;

import com.project.jpaManyToManyMapping.exception.SkillAlreadyExistException;
import com.project.jpaManyToManyMapping.exception.SkillNotFoundException;
import com.project.jpaManyToManyMapping.model.JobSeeker;
import com.project.jpaManyToManyMapping.model.Skill;

import java.io.IOException;
import java.util.List;

public interface SkillService {
    Skill saveSkill(Skill skill) throws SkillAlreadyExistException;
    Skill getSkill(int SkillId) throws SkillNotFoundException;
    List<Skill> getAllSkills();


}
