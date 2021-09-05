package com.jewelcse045.Job.Scheduler.service;

import com.jewelcse045.Job.Scheduler.entities.CompanyEntity;
import com.jewelcse045.Job.Scheduler.model.CompanyRequestModel;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CompanyService {
    CompanyEntity saveCompany(CompanyRequestModel company);
    CompanyEntity updateCompany(CompanyEntity company);
    CompanyEntity getCompanyById(int companyId);

    Page<CompanyEntity> getAllCompany(int offSet, int pageSize);

    void removeCompanyById(int companyId);
}
