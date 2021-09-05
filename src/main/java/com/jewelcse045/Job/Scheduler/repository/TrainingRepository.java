package com.jewelcse045.Job.Scheduler.repository;

import com.jewelcse045.Job.Scheduler.entities.TrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<TrainingEntity,Integer> {
}
