package com.jewelcse045.Job.Scheduler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="jobs")
public class JobEntity implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="job_id" ,nullable = false)
    private int jobId; // primary key

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "job_category_id")
    private int jobCategoryId; // foreign key

    @Column(name = "job_type")
    private String jobType;

    @Column(name = "job_number_of_vacancy")
    private int jobNumberOfVacancy;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "job_post_date")
    private String jobPostDate;

    @Column(name = "job_deadline_time")
    private String jobDeadLineTime;

    @Column(name = "job_is_active")
    private boolean jobIsActive;

    @Column(name = "company_id")
    private int companyId;

    @ManyToOne(targetEntity =CompanyEntity.class ,cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_id",referencedColumnName = "company_id",nullable = false,insertable=false, updatable=false)
    private CompanyEntity companyEntity;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "job_tags",
            joinColumns = { @JoinColumn(name = "job_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<TagEntity> tags = new HashSet<>();

}
