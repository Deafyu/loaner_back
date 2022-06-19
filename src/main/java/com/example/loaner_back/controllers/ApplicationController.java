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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@FieldDefaults(makeFinal = true)
@RestController
@RequestMapping("/application")
public class ApplicationController {
    ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/lenders")
    public ResponseEntity<String> sendLenderApplication(@RequestBody LenderApplicationDto lenderApplicationDto) {
        try {
            applicationService.createNewLenderApplication(lenderApplicationDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/loans")
    public ResponseEntity<String> sendLoanApplication(@RequestBody LoanApplicationDto loanApplicationDto) {
        try {
            applicationService.createNewLoanApplication(loanApplicationDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER') ")
    @GetMapping("/lenders/{id}")
    public ResponseEntity<ApplicationEntity> getLenderApplication(@PathVariable long id) {
        try {
            return new ResponseEntity(applicationService.getApplicationEntity(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')or hasRole('LENDER')")
    @GetMapping("/loans/{id}")
    public ResponseEntity<ApplicationEntity> getLoanApplication(@PathVariable long id) {
        try {
            return new ResponseEntity(applicationService.getApplicationEntity(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('LENDER') or hasRole('ADMIN')")
    @GetMapping("/loans")
    public ResponseEntity<List<ApplicationEntity>> getAllLoansApplications() {
        try {
            return new ResponseEntity(applicationService.getAllApplicationEntities(Type.LOAN), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lenders")
    public ResponseEntity<List<ApplicationEntity>> getAllLenderApplications() {
        try {
            return new ResponseEntity(applicationService.getAllApplicationEntities(Type.LENDER), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/lenders/{id}")
    public ResponseEntity<String> answerLenderApplication(@RequestBody ApplicationAnswerDto applicationAnswerDto, @PathVariable Long id) {
        return new ResponseEntity(applicationService.answerApplication(applicationAnswerDto), HttpStatus.OK);
//        try {
//            System.out.println("a");
//        } catch (Exception ex) {
//            System.out.println(ex);
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
    }

    @PreAuthorize("hasRole('LENDER')")
    @PostMapping("/loans/{id}")
    public ResponseEntity<String> answerLoanApplication(@RequestBody ApplicationAnswerDto applicationAnswerDto, @PathVariable Long id) {
//        try {
            return new ResponseEntity(applicationService.answerApplication(applicationAnswerDto), HttpStatus.OK);
//        } catch (Exception ex) {
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
    }
}
