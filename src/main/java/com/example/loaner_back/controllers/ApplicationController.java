package com.example.loaner_back.controllers;

import com.example.loaner_back.dto.Application.ApplicationAnswerDto;
import com.example.loaner_back.dto.Application.LenderApplicationDto;
import com.example.loaner_back.dto.Application.LoanApplicationDto;
import com.example.loaner_back.entity.ApplicationEntity;
import com.example.loaner_back.service.ApplicationService;
import com.example.loaner_back.utils.Type;
import lombok.experimental.FieldDefaults;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@FieldDefaults(makeFinal = true)
@RestController
@Transactional
@RequestMapping("/application")
public class ApplicationController {
    ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/lenders")
    public void sendLenderApplication(@RequestBody LenderApplicationDto lenderApplicationDto) {
        applicationService.createNewLenderApplication(lenderApplicationDto);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/loans")
    public void sendLoanApplication(@RequestBody LoanApplicationDto loanApplicationDto) {
        applicationService.createNewLoanApplication(loanApplicationDto);
    }

    @PreAuthorize("hasRole('USER') ")
    @GetMapping("/lenders/{id}")
    public ApplicationEntity getLenderApplication(@PathVariable long id) {
        return applicationService.getApplicationEntity(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')or hasRole('LENDER')")
    @GetMapping("/loans/{id}")
    public ApplicationEntity getLoanApplication(@PathVariable long id) {
        return applicationService.getApplicationEntity(id);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/loans")
    public List<ApplicationEntity> getAllLoansApplications() {
        return applicationService.getAllApplicationEntities(Type.LOAN);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lenders")
    public List<ApplicationEntity> getAllLenderApplications() {
        return applicationService.getAllApplicationEntities(Type.LENDER);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/lenders/{id}")
    public String answerLenderApplication(@RequestBody ApplicationAnswerDto applicationAnswerDto, @PathVariable Long id) {
        return applicationService.answerApplication(applicationAnswerDto);
    }

    @PreAuthorize("hasRole('LENDER')")
    @PostMapping("/loans/{id}")
    public String answerLoanApplication(@RequestBody ApplicationAnswerDto applicationAnswerDto, @PathVariable Long id) {
        return applicationService.answerApplication(applicationAnswerDto);
    }
}
