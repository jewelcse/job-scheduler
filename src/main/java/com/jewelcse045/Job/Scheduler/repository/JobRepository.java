package com.jewelcse045.Job.Scheduler.repository;

import com.jewelcse045.Job.Scheduler.entities.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<JobEntity,Integer> {
    List<JobEntity> findAllByCompanyId(int companyId);

    @Query(value = "SELECT * from jobs WHERE job_is_active = 1",nativeQuery = true)
    List<JobEntity> findAllActiveJob();

    @Query(value = "SELECT * from jobs WHERE job_is_active = 0",nativeQuery = true)
    List<JobEntity> findAllInActiveJob();

    @Transactional
    @Modifying
    @Query(value = "UPDATE jobs SET job_is_active = false WHERE job_id =:jobId",nativeQuery = true)
    void makeInActive(@Param("jobId") int jobId);
}
