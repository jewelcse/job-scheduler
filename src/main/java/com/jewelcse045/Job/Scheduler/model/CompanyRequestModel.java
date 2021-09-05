package com.jewelcse045.Job.Scheduler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestModel {

    private String companyName;
    private String companyEmail;
    private String companyPhone;
    private String companyAddress;
}
