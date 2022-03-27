package com.project.jpaManyToManyMapping.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int skillId;
    private String skill;
    @ManyToMany (fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "skills")
    @JsonIgnore
    private List<JobSeeker> jobSeekers;

}
