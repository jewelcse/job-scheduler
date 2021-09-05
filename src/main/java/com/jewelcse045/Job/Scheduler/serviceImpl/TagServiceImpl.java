package com.jewelcse045.Job.Scheduler.serviceImpl;

import com.jewelcse045.Job.Scheduler.entities.TagEntity;
import com.jewelcse045.Job.Scheduler.exception.ApplicationException;
import com.jewelcse045.Job.Scheduler.exception.TagNotFoundException;
import com.jewelcse045.Job.Scheduler.model.TagRequestModel;
import com.jewelcse045.Job.Scheduler.repository.TagRepository;
import com.jewelcse045.Job.Scheduler.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;
    @Override
    public TagEntity saveTag(TagRequestModel tagRequestModel) {
        if (tagRequestModel.getTagName() == null || tagRequestModel.getTagName().equals("")){
            throw new ApplicationException("Tag name can't be Empty");
        }
        TagEntity tagEntity = new TagEntity();
        tagEntity.setTagName(tagRequestModel.getTagName());
        return tagRepository.save(tagEntity);
    }

    @Override
    public List<TagEntity> getTags() {
        return tagRepository.findAllTags();
    }

    @Override
    public TagEntity getById(int tagId) {
        return tagRepository.findById(tagId).get();
    }

    @Override
    public TagEntity updateTag(TagEntity updateTagEntity) {
        return tagRepository.save(updateTagEntity);
    }

    @Override
    public void removeTagById(int tagId) {
        Optional<TagEntity> tagEntity = tagRepository.findById(tagId);
        if ((tagEntity.isEmpty())) {
            throw new TagNotFoundException("Tag not found for id "+tagId);
        }
    }
}
