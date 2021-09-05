package com.jewelcse045.Job.Scheduler.model;


import com.jewelcse045.Job.Scheduler.entities.TagEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseModel {

    private int jobId;
    private String jobTitle;
    private int jobCategoryId;
    private String jobType;
    private int jobNumberOfVacancy;
    private String jobDescription;
    private String jobPostTime;
    private String jobDeadLineTime;
    private Set<TagEntity> tags;
    private int companyId;
    private boolean isActive;
}
