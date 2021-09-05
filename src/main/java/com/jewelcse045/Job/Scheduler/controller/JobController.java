package com.jewelcse045.Job.Scheduler.controller;

import com.jewelcse045.Job.Scheduler.entities.JobEntity;
import com.jewelcse045.Job.Scheduler.exception.ApplicationException;
import com.jewelcse045.Job.Scheduler.exception.JobNotFoundException;
import com.jewelcse045.Job.Scheduler.model.JobRequestModel;
import com.jewelcse045.Job.Scheduler.model.JobResponseModel;
import com.jewelcse045.Job.Scheduler.service.JobService;
import com.jewelcse045.Job.Scheduler.utils.ResponseMessage;
import org.codehaus.jettison.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/job/post")
    public ResponseEntity<JobEntity> postJob(@RequestBody JobRequestModel jobRequestModel){
        return new ResponseEntity<>(jobService.postJob(jobRequestModel), HttpStatus.CREATED);
    }

    @GetMapping("/job/posts/{offset}/{pageSize}") //int offset, int pageSize
    public List<JobResponseModel> getAllJob(@PathVariable int offset, @PathVariable int pageSize){
        Page<JobEntity> jobsList = jobService.getAllJob(offset,pageSize);

        return jobsList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private JobResponseModel convertToDto(JobEntity jobEntity) {
        JobResponseModel jobDto = modelMapper.map(jobEntity, JobResponseModel.class);
        jobDto.setJobId(jobEntity.getJobId());
        jobDto.setCompanyId(jobEntity.getCompanyId());
        jobDto.setJobTitle(jobEntity.getJobTitle());
        jobDto.setJobCategoryId(jobEntity.getJobCategoryId());
        jobDto.setJobType(jobEntity.getJobType());
        jobDto.setJobPostTime(jobEntity.getJobPostDate());
        jobDto.setJobDeadLineTime(jobEntity.getJobDeadLineTime());
        jobDto.setJobDescription(jobEntity.getJobDescription());
        jobDto.setJobNumberOfVacancy(jobEntity.getJobNumberOfVacancy());
        jobDto.setTags(jobEntity.getTags());
        jobDto.setActive(jobEntity.isJobIsActive());
        return jobDto;
    }

    @DeleteMapping("/job/remove/{jobId}")
    public ResponseEntity<String> removeJob(@PathVariable int jobId) throws JSONException {
        if (jobId<=0){
            throw new ApplicationException("Invalid Job id "+jobId);
        }

        jobService.removeJobById(jobId);
        return new ResponseEntity<>(ResponseMessage.voidSuccessResponse("Deleted Success",HttpStatus.OK),HttpStatus.OK);
    }

    @GetMapping("/job/active/posts")
    public List<JobResponseModel> getAllActiveJob(){
        List<JobEntity> jobsList = jobService.getAllActiveJob();

        return jobsList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/job/inactive/posts")
    public List<JobResponseModel> getAllInActiveJob(){
        List<JobEntity> jobsList = jobService.getAllInActiveJob();

        return jobsList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }



}
