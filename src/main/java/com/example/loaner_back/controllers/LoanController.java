package com.example.loaner_back.controllers;

import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.service.LoanService;
import lombok.experimental.FieldDefaults;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/loans")
    public List<LoanEntity> getAllLoans() {
        return loanService.getAllLoans();
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('LENDER')")
    @GetMapping(value = "/loans?lender={lender}")
    public List<LoanEntity> getAllLoansFromLender(@PathVariable String lender) {
        return loanService.getAllLoansFromLender(lender);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/loans?lender={lender}&id={id}")
    public ResponseEntity<LoanEntity> getLoanDetails(@PathVariable String lender, @PathVariable long id) {
        try {
            return ResponseEntity.ok(loanService.getSingleLoanById(id).orElseThrow(RuntimeException::new));
        } catch (Exception ex) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
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
    public void editLoan() {
        throw new NotYetImplementedException();
    }

    @PreAuthorize("hasRole('LENDER')")
    @DeleteMapping(value = "/loans")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeLoan(@RequestBody long id) {
        loanService.deleteLoan(id);
    }



}
