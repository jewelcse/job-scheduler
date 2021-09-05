package com.jewelcse045.Job.Scheduler.service;

import com.jewelcse045.Job.Scheduler.entities.JobEntity;
import com.jewelcse045.Job.Scheduler.model.JobRequestModel;
import com.jewelcse045.Job.Scheduler.model.JobResponseModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JobService {
    JobEntity postJob(JobRequestModel jobRequestModel);
    List<JobEntity> getJobs(int companyId);

    Page<JobEntity> getAllJob(int offset, int pageSize);

    void removeJobById(int jobId);

    List<JobEntity> getAllActiveJob();

    List<JobEntity> getAllInActiveJob();

    void buildInActive(int jobId);
}
