package com.example.loaner_back.controllers;

import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.service.LoanService;
import lombok.experimental.FieldDefaults;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@FieldDefaults(makeFinal = true)
@RestController
@Transactional
public class LoanLenderController {

    LoanService loanService;

    @Autowired
    public LoanLenderController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PreAuthorize("hasRole('LENDER')")
    @PostMapping(value = "/loans")
    @ResponseStatus(value = HttpStatus.OK)
    public void addLoan(@RequestBody LoanEntity loanEntity) {
        loanService.createLoan(loanEntity);
    }

    @PreAuthorize("hasRole('LENDER')")
    @PutMapping(value = "/loans")
    @ResponseStatus(value = HttpStatus.OK)
    public void editLoan(long id, LoanEntity loanEntity) {
        throw new NotYetImplementedException();
    }

    @PreAuthorize("hasRole('LENDER')")
    @DeleteMapping(value = "/loans")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeLoan(@RequestBody long id) {
        loanService.deleteLoan(id);
    }


}
