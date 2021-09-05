package com.jewelcse045.Job.Scheduler.model;


import com.jewelcse045.Job.Scheduler.entities.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequestModel {

    private String jobTitle;
    private int jobCategoryId;
    private String jobType;
    private int jobNumberOfVacancy;
    private String jobDescription;
    private String jobDeadLineTime;
    private int[] tags;
    private int companyId;

}
