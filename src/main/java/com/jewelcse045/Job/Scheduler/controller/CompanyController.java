package com.jewelcse045.Job.Scheduler.controller;


import com.jewelcse045.Job.Scheduler.entities.CompanyEntity;
import com.jewelcse045.Job.Scheduler.entities.JobEntity;
import com.jewelcse045.Job.Scheduler.exception.ApplicationException;
import com.jewelcse045.Job.Scheduler.model.CompanyRequestModel;
import com.jewelcse045.Job.Scheduler.service.CompanyService;
import com.jewelcse045.Job.Scheduler.service.JobService;
import com.jewelcse045.Job.Scheduler.utils.ResponseMessage;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private JobService jobService;

    @PostMapping("/company/save")
    public ResponseEntity<CompanyEntity> saveCompany(@RequestBody CompanyRequestModel company){
        CompanyEntity companyRequestModel = companyService.saveCompany(company);
        return new ResponseEntity<>(companyRequestModel, HttpStatus.CREATED);
    }

    @PutMapping("/company/update/{companyId}")
    public ResponseEntity<CompanyEntity> updateCompany(@RequestBody CompanyRequestModel company,@PathVariable int companyId){

        if (company.getCompanyName() == null || company.getCompanyName().equals("")){
            throw new ApplicationException("Company Can't be Empty");
        }

        if (company.getCompanyEmail() == null || company.getCompanyEmail().equals("")){
            throw new ApplicationException("Company Email can't be Empty");
        }

        if (company.getCompanyPhone() == null || company.getCompanyPhone().equals("")){
            throw new ApplicationException("Company Phone can't be Empty");
        }
        if (company.getCompanyAddress() == null || company.getCompanyAddress().equals("")){
            throw  new ApplicationException("Company Address can't be Empty");
        }

        CompanyEntity updatedCompany = companyService.getCompanyById(companyId);

        updatedCompany.setCompanyId(companyId);
        updatedCompany.setCompanyName(company.getCompanyName());
        updatedCompany.setCompanyEmail(company.getCompanyEmail());
        updatedCompany.setCompanyPhone(company.getCompanyPhone());
        updatedCompany.setCompanyAddress(company.getCompanyAddress());
        CompanyEntity companyEntity = companyService.updateCompany(updatedCompany);
        System.out.println(company + "  "+ companyId);
        return new ResponseEntity<>(companyEntity, HttpStatus.OK);
    }

    @GetMapping("/company/jobs/{companyId}")
    public ResponseEntity<List<JobEntity>> getCompanyJobs(@PathVariable int companyId){
        return new ResponseEntity<>(jobService.getJobs(companyId),HttpStatus.OK);
    }

    @GetMapping("/companies/{offSet}/{pageSize}")
    public ResponseEntity<List<CompanyEntity>> getCompanies(@PathVariable int offSet, @PathVariable int pageSize){
        return new ResponseEntity<>(companyService.getAllCompany(offSet,pageSize).getContent(),HttpStatus.OK);
    }

    @DeleteMapping("/company/remove/{companyId}")
    public ResponseEntity<String> removeCompany(@PathVariable int companyId) throws JSONException {
        if (companyId<=0){
            throw new ApplicationException("Invalid Company Id " + companyId);
        }
        companyService.removeCompanyById(companyId);

        return new ResponseEntity<>(ResponseMessage.voidSuccessResponse("Company Remove Success",HttpStatus.OK),HttpStatus.OK);
    }




}
