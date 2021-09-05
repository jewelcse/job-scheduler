package com.jewelcse045.Job.Scheduler.controller;

import com.jewelcse045.Job.Scheduler.entities.TagEntity;
import com.jewelcse045.Job.Scheduler.exception.ApplicationException;
import com.jewelcse045.Job.Scheduler.model.TagRequestModel;
import com.jewelcse045.Job.Scheduler.service.TagService;
import com.jewelcse045.Job.Scheduler.utils.ResponseMessage;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {

    @Autowired
    private TagService tagService;

    @PostMapping("/tag/save")
    public ResponseEntity<TagEntity> saveTag(@RequestBody TagRequestModel tagRequestModel){
        return new ResponseEntity<>(tagService.saveTag(tagRequestModel), HttpStatus.CREATED);
    }
    @PutMapping("/tag/update/{tagId}")
    public ResponseEntity<TagEntity> updateTag(@RequestBody TagRequestModel tagRequestModel,@PathVariable int tagId){
        if (tagRequestModel.getTagName() == null || tagRequestModel.getTagName().equals("")){
            throw new ApplicationException("Tag name can't be Empty");
        }
        TagEntity updateTagEntity = tagService.getById(tagId);
        updateTagEntity.setTagId(updateTagEntity.getTagId());
        updateTagEntity.setTagName(tagRequestModel.getTagName());
        return  new ResponseEntity<>(tagService.updateTag(updateTagEntity),HttpStatus.OK);

    }
    @GetMapping("/tag/all")
    public ResponseEntity<List<TagEntity>> getAllTag(){
        return new ResponseEntity<>(tagService.getTags(),HttpStatus.OK);
    }

    @DeleteMapping("/tag/remove/{tagId}")
    public ResponseEntity<String> removeTag(@PathVariable int tagId) throws JSONException {
        if (tagId <=0){
            throw new ApplicationException("Invalid Tag id" + tagId);
        }
        tagService.removeTagById(tagId);
        return new ResponseEntity<>(ResponseMessage.voidSuccessResponse("Remove Tag Successful",HttpStatus.OK),HttpStatus.OK);
    }
}
