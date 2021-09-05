package com.jewelcse045.Job.Scheduler.repository;

import com.jewelcse045.Job.Scheduler.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
}
