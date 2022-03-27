package com.project.jpaManyToManyMapping.repository;

import com.project.jpaManyToManyMapping.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Integer> {

}
