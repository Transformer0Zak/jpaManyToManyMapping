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
@Table (name = "jobSeeker")
public class JobSeeker {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "jobSeekerId")
    private int jobSeekerId;
    @Column (name = "jobSeeker")
    private String jobSeekerName;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "jobSeeker_skill", joinColumns = {@JoinColumn(name = "jobSeeker_id")},
    inverseJoinColumns = {@JoinColumn (name = "skill_id")})
    private List<Skill> skills;
}
