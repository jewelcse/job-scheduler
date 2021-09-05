package com.jewelcse045.Job.Scheduler.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="company")
public class CompanyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="company_id")
    private int companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_email")
    private String companyEmail;

    @Column(name = "company_phone")
    private String companyPhone;

    @Column(name = "company_address")
    private String companyAddress;


}
