package com.jewelcse045.Job.Scheduler.repository;

import com.jewelcse045.Job.Scheduler.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity,Integer> {

    @Query(value=" select * from tags" , nativeQuery = true)
    List<TagEntity> findAllTags();
}
