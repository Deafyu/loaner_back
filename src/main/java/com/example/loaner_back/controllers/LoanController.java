package com.example.loaner_back.controllers;

import com.example.loaner_back.entity.LoanEntity;
import lombok.experimental.FieldDefaults;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FieldDefaults(makeFinal = true)
@RestController
public class LoanController {

    @GetMapping(value = "/loans")
    public List<LoanEntity> getAllLoans(){
        throw new NotYetImplementedException();
    }

    @GetMapping(value = "/loans?lender={lender}")
    public List<LoanEntity> getAllLoansFromLender(@PathVariable String lender){
        throw new NotYetImplementedException();
    }

    @GetMapping(value = "/loans?lender={lender}&id={id}")
    public LoanEntity getLoanDetails(@PathVariable String lender, @PathVariable long id){
        throw new NotYetImplementedException();
    }

    @PostMapping(value = "/loans")
    @ResponseStatus(value = HttpStatus.OK)
    public void addLoan(){

    }
    @PutMapping(value = "/loans")
    @ResponseStatus(value = HttpStatus.OK)
    public void editLoan(){

    }
    @DeleteMapping(value = "/loans")
    @ResponseStatus(value = HttpStatus.OK)
    public void removeLoan(){

    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/loans?lender={lender}&id={id}")
    public void getLoan(@PathVariable String lender, @PathVariable long id){
        throw new NotYetImplementedException();
    }

}
