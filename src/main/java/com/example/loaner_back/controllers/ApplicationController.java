package com.example.loaner_back.controllers;

import com.example.loaner_back.dto.ApplicationDto;
import com.example.loaner_back.service.ApplicationService;
import lombok.experimental.FieldDefaults;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

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
    @PostMapping("/lender")
    public ResponseEntity sendLenderApplication() {
        throw new NotYetImplementedException();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/loan")
    public void sendLoanApplication(@RequestBody ApplicationDto applicationDto) {
        applicationService.createNewApplication(applicationDto);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/lender")
    public ResponseEntity getLenderApplication() {
        throw new NotYetImplementedException();

    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/loan")
    public ResponseEntity getLoanApplication() {
        throw new NotYetImplementedException();

    }
}
