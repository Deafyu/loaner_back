package com.example.loaner_back.controllers;

import com.example.loaner_back.dto.LoanDto;
import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.service.LoanService;
import lombok.experimental.FieldDefaults;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity addLoan(@RequestBody LoanDto loanEntity) {
        try {
            loanService.createLoan(loanEntity);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('LENDER')")
    @PutMapping(value = "/loans")
    @ResponseStatus(value = HttpStatus.OK)
    public void editLoan(long id, LoanEntity loanEntity) {
        throw new NotYetImplementedException();
    }

    @PreAuthorize("hasRole('LENDER')")
    @DeleteMapping(value = "/loans/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity removeLoan(@PathVariable long id) {
        try {
            loanService.deleteLoan(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
