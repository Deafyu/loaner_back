package com.example.loaner_back.controllers;

import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.service.LoanService;
import lombok.experimental.FieldDefaults;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(makeFinal = true)
@RestController
public class LoanController {
    LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PreAuthorize("hasRole('USER_ROLE') or hasRole('ADMIN_ROLE')")
    @GetMapping(value = "/loans")
    public List<LoanEntity> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PreAuthorize("hasRole('USER_ROLE') or hasRole('ADMIN_ROLE') or hasRole('LENDER_ROLE')")
    @GetMapping(value = "/loans?lender={lender}")
    public List<LoanEntity> getAllLoansFromLender(@PathVariable String lender) {
        return loanService.getAllLoansFromLender(lender);
    }

    @PreAuthorize("hasRole('USER_ROLE') or hasRole('ADMIN_ROLE')")
    @GetMapping(value = "/loans?lender={lender}&id={id}")
    public LoanEntity getLoanDetails(@PathVariable String lender, @PathVariable long id) {
        throw new NotYetImplementedException();
    }

    @PreAuthorize("hasRole('LENDER_ROLE')")
    @PostMapping(value = "/loans")
    @ResponseStatus(value = HttpStatus.OK)
    public void addLoan(@RequestBody LoanEntity loanEntity) {
        loanService.createLoan(loanEntity);
    }

    @PreAuthorize("hasRole('LENDER_ROLE')")
    @PutMapping(value = "/loans")
    @ResponseStatus(value = HttpStatus.OK)
    public void editLoan() {
        throw new NotYetImplementedException();
    }

    @PreAuthorize("hasRole('LENDER_ROLE')")
    @DeleteMapping(value = "/loans")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeLoan(@RequestBody long id) {
        loanService.deleteLoan(id);
    }

    @PreAuthorize("hasRole('USER_ROLE') or hasRole('ADMIN_ROLE') or hasRole('LENDER_ROLE')")
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/loans?lender={lender}&id={id}")
    public void getLoan(@PathVariable String lender, @PathVariable long id) {
        throw new NotYetImplementedException();
    }

}
