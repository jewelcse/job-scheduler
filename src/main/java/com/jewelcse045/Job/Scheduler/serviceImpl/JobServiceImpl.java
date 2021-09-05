package com.jewelcse045.Job.Scheduler.serviceImpl;

import com.jewelcse045.Job.Scheduler.entities.CompanyEntity;
import com.jewelcse045.Job.Scheduler.entities.JobEntity;
import com.jewelcse045.Job.Scheduler.entities.TagEntity;
import com.jewelcse045.Job.Scheduler.exception.ApplicationException;
import com.jewelcse045.Job.Scheduler.exception.CompanyNotFoundException;
import com.jewelcse045.Job.Scheduler.exception.JobNotFoundException;
import com.jewelcse045.Job.Scheduler.model.JobRequestModel;
import com.jewelcse045.Job.Scheduler.model.JobResponseModel;
import com.jewelcse045.Job.Scheduler.repository.CompanyRepository;
import com.jewelcse045.Job.Scheduler.repository.JobRepository;
import com.jewelcse045.Job.Scheduler.repository.TagRepository;
import com.jewelcse045.Job.Scheduler.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class JobServiceImpl implements JobService {


    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private TagRepository tagRepository;
    @Override
    public JobEntity postJob(JobRequestModel jobRequestModel) {

        if (jobRequestModel.getJobTitle() == null || jobRequestModel.getJobTitle().equals("")){
            throw new ApplicationException("Job Title can't be Empty");
        }

        if (jobRequestModel.getJobCategoryId() <=0){
            throw new ApplicationException("Invalid Job Category Id");
        }

        if (jobRequestModel.getCompanyId() <=0){
            throw new ApplicationException("Invalid Company Id");
        }
        if (jobRequestModel.getJobDescription() == null || jobRequestModel.getJobDescription().equals("")){
            throw new ApplicationException("Job Description can't be Empty");
        }
        if (jobRequestModel.getJobNumberOfVacancy() <=0){
            throw new ApplicationException("Invalid Number Format");
        }

        if (jobRequestModel.getJobDeadLineTime() == null || jobRequestModel.getJobDeadLineTime().equals("")){
            throw new ApplicationException("Deadline date Can't Empty");
        }



        if (jobRequestModel.getTags().length <=0){
            throw new ApplicationException("Tag Can't Empty");
        }
        Set<TagEntity> tagEntities = new HashSet<>();
        for (int i=0; i<jobRequestModel.getTags().length;i++){
            Optional<TagEntity> tagEntity = tagRepository.findById(jobRequestModel.getTags()[i]);
            if (tagEntity.isEmpty()){
                throw new ApplicationException("Tag doesn't exit");
            }else {
                tagEntities.add(tagEntity.get());
            }

        }

        JobEntity jobEntity = new JobEntity();

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");




        LocalDateTime formatDateTime = LocalDateTime.parse(jobRequestModel.getJobDeadLineTime(), formatter);

        System.out.println("system time "+currentTime);
        System.out.println("deadline "+formatDateTime);

        if (formatDateTime.isBefore(currentTime)){
            throw new ApplicationException("Dead can't be end before post date");
        }



        jobEntity.setJobTitle(jobRequestModel.getJobTitle());
        jobEntity.setJobCategoryId(jobRequestModel.getJobCategoryId());
        jobEntity.setJobType(jobRequestModel.getJobType());
        jobEntity.setJobDescription(jobRequestModel.getJobDescription());
        jobEntity.setJobNumberOfVacancy(jobRequestModel.getJobNumberOfVacancy());
        jobEntity.setJobIsActive(true);

        jobEntity.setTags(tagEntities);

        if (jobRequestModel.getCompanyId() <= 0){
            throw new ApplicationException("Invalid Company Id");
        }

        Optional<CompanyEntity> companyEntity = companyRepository.findById(jobRequestModel.getCompanyId());

        if (companyEntity.isEmpty()){
            throw new CompanyNotFoundException("Company not found for id "+ jobRequestModel.getCompanyId());
        }

        jobEntity.setJobPostDate(currentTime.format(formatter));
        jobEntity.setJobDeadLineTime(jobRequestModel.getJobDeadLineTime());
        jobEntity.setCompanyId(jobRequestModel.getCompanyId());
        return jobRepository.save(jobEntity);
    }

    @Override
    public List<JobEntity> getJobs(int companyId) {
        if (companyId <=0){
            throw new ApplicationException("Invalid Company Id "+companyId);
        }
        Optional<CompanyEntity> companyEntity = companyRepository.findById(companyId);
        if (companyEntity.isEmpty()){
            throw new CompanyNotFoundException("Company not found for id"+companyId);
        }
        return jobRepository.findAllByCompanyId(companyId);
    }



    @Override
    public Page<JobEntity> getAllJob(int offset, int pageSize) {
        return jobRepository.findAll(PageRequest.of(offset,pageSize));
    }

    @Override
    public void removeJobById(int jobId) {

        Optional<JobEntity> jobEntity = jobRepository.findById(jobId);
        if (jobEntity.isEmpty()){
            throw new JobNotFoundException("Job not found for id "+jobId);
        }
         jobRepository.deleteById(jobId);
    }

    @Override
    public List<JobEntity> getAllActiveJob() {
        return jobRepository.findAllActiveJob();
    }

    @Override
    public List<JobEntity> getAllInActiveJob() {
        return jobRepository.findAllInActiveJob();
    }

    @Override
    public void buildInActive(int jobId) {
        jobRepository.makeInActive(jobId);
    }
}
