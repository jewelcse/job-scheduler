package com.jewelcse045.Job.Scheduler.config;


import com.jewelcse045.Job.Scheduler.entities.JobEntity;
import com.jewelcse045.Job.Scheduler.service.JobService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;


@Component
public class MyScheduledTasks {


    @Autowired
    private JobService jobService;

    LocalDateTime currentTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    @Scheduled(cron = "0 0 12 * * ?") // 12 pm every day
    public void buildJobDead() {
        List<JobEntity> jobEntities  = jobService.getAllActiveJob();
        jobEntities.forEach(jobEntity -> {
            LocalDateTime deadline = LocalDateTime.parse(jobEntity.getJobDeadLineTime(), formatter);
            if (currentTime.isAfter(deadline)){
                jobService.buildInActive(jobEntity.getJobId());
            }
        });
    }



}