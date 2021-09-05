package com.jewelcse045.Job.Scheduler.entities;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="skills")
public class SkillEntity implements Serializable {

    private static final long serialVersionUID = 3L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="skill_id",nullable = false)
    private int skillId;

    @Column(name="skill_name")
    private String skillName;


}
