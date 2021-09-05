package com.jewelcse045.Job.Scheduler.serviceImpl;

import com.jewelcse045.Job.Scheduler.entities.CompanyEntity;
import com.jewelcse045.Job.Scheduler.exception.ApplicationException;
import com.jewelcse045.Job.Scheduler.exception.CompanyNotFoundException;
import com.jewelcse045.Job.Scheduler.model.CompanyRequestModel;
import com.jewelcse045.Job.Scheduler.repository.CompanyRepository;
import com.jewelcse045.Job.Scheduler.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyEntity saveCompany(CompanyRequestModel company) {

        if (company.getCompanyName() == null || company.getCompanyName() == ""){
            throw new ApplicationException("Company Can't be Empty");
        }

        if (company.getCompanyEmail() == null || company.getCompanyEmail() ==""){
            throw new ApplicationException("Company Email can't be Empty");
        }

        if (company.getCompanyPhone() == null || company.getCompanyPhone() ==""){
            throw new ApplicationException("Company Phone can't be Empty");
        }
        if (company.getCompanyAddress() == null || company.getCompanyAddress() == ""){
            throw  new ApplicationException("Company Address can't be Empty");
        }


        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCompanyName(company.getCompanyName());
        companyEntity.setCompanyEmail(company.getCompanyEmail());
        companyEntity.setCompanyAddress(company.getCompanyAddress());
        companyEntity.setCompanyPhone(company.getCompanyPhone());
        return companyRepository.save(companyEntity);
    }

    @Override
    public CompanyEntity updateCompany(CompanyEntity company) {
        return companyRepository.save(company);

    }

    @Override
    public CompanyEntity getCompanyById(int companyId) {
        return companyRepository.getById(companyId);
    }

    @Override
    public Page<CompanyEntity> getAllCompany(int offSet, int pageSize) {
        Page<CompanyEntity> companyEntities = companyRepository.findAll(PageRequest.of(offSet,pageSize));
        return companyEntities;
    }

    @Override
    public void removeCompanyById(int companyId) {
        Optional<CompanyEntity> companyEntity = companyRepository.findById(companyId);
        if (companyEntity.isEmpty()){
            throw new CompanyNotFoundException("Company not Found for id "+companyId);
        }
        companyRepository.deleteById(companyId);
    }


}
