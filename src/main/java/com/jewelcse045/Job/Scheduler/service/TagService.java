package com.jewelcse045.Job.Scheduler.service;

import com.jewelcse045.Job.Scheduler.entities.TagEntity;
import com.jewelcse045.Job.Scheduler.model.TagRequestModel;

import java.util.List;

public interface TagService {
    TagEntity saveTag(TagRequestModel tagRequestModel);
    List<TagEntity> getTags();
    TagEntity getById(int tagId);

    TagEntity updateTag(TagEntity updateTagEntity);

    void removeTagById(int tagId);
}
