package com.jewelcse045.Job.Scheduler.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 6L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private int user_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "email_is_verified")
    private boolean emailIsVerified;

    @Column(name = "contact_number")
    private Long contactNumber;

    @Column(name = "contact_number_is_verified")
    private Boolean contactNumberIsVerified;

    @Column(name = "status")
    private boolean status;

    @Column(name = "login_tries")
    private int loginTries;

    @Column(name = "login_time")
    private String loginTime;

    @Column(name = "address")
    private String address;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_skills",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") })
    private Set<SkillEntity> skills = new HashSet<>();




}
