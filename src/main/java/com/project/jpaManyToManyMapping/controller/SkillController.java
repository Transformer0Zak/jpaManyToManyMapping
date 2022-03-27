package com.project.jpaManyToManyMapping.controller;


import com.project.jpaManyToManyMapping.exception.SkillAlreadyExistException;
import com.project.jpaManyToManyMapping.exception.SkillNotFoundException;
import com.project.jpaManyToManyMapping.model.Skill;
import com.project.jpaManyToManyMapping.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class SkillController {
    private ResponseEntity responseEntity;
    private SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
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


}
