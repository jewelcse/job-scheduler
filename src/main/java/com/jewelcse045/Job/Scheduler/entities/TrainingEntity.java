package com.jewelcse045.Job.Scheduler.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="trainings")
public class TrainingEntity implements Serializable {

    private static final long serialVersionUID = 4L;


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "training_id",nullable = false)
    private int trainingId;

    @Column(name = "training_title")
    private String trainingTitle;

    @Column(name = "training_institute_name")
    private String trainingInstituteName;

    @Column(name = "training_description")
    private String trainingDescription;

    @Column(name = "training_start_date")
    private String trainingStartDate;

    @Column(name = "training_end_Date")
    private String trainingEndDate;

    @Column(name = "training_status")
    private boolean status;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;


}
