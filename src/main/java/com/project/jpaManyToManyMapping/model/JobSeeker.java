package com.project.jpaManyToManyMapping.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "jobSeeker")
public class JobSeeker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "jobSeekerId")
    private int jobSeekerId;
    @Column(name = "jobSeeker")
    private String jobSeekerName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "jobSeeker_skill", joinColumns = {@JoinColumn(name = "jobSeeker_id")},
            inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    private List<Skill> skills= new ArrayList<>();

    public void addSkill(Skill skill) {
        this.skills.add(skill);
        skill.getJobSeekers().add(this);
    }
}
