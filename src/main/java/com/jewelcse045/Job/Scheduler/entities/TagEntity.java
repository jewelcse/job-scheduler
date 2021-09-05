package com.jewelcse045.Job.Scheduler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tags")
public class TagEntity implements Serializable {

    private static final long serialVersionUID = 34555555L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id",nullable = false)
    private int tagId;

    @Column(name = "tag_name")
    private String tagName;


}
