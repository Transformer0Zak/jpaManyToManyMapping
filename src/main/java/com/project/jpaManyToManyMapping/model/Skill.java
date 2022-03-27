package com.project.jpaManyToManyMapping.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "skills")
public class Skill {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    @Column (name = "skill_id")
    private int skillId;
//    @Column (name ="skill")
    private String skill;
    @ManyToMany (fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "skills")
    private List<JobSeeker> jobSeekers;

}
