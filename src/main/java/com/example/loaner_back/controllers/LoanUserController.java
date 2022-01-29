package com.example.loaner_back.controllers;

import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.service.LoanService;
import com.example.loaner_back.threads.LoanThread;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@FieldDefaults(makeFinal = true)
@RestController
@Transactional
public class LoanUserController {
    LoanService loanService;

    @Autowired
    public LoanUserController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/loans")
    public List<LoanEntity> getAllLoans() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        List<LoanThread> callableList = new ArrayList<>();
        callableList.add(new LoanThread(loanService));
        List<Future<List<LoanEntity>>> list = service.invokeAll(callableList);

        return list.get(0).get();
    }

    @PreAuthorize("hasRole('LENDER') or hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/loans/loan")
    public List<LoanEntity> getAllLoansFromLender(@RequestParam String lender) {
        return loanService.getAllLoansFromLender(lender);
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping(value = "/loans/loan/details")
    public ResponseEntity<LoanEntity> getLoanDetails(@RequestParam long id) {
        try {
            return ResponseEntity.ok(loanService.getSingleLoanById(id).orElseThrow(RuntimeException::new));
        } catch (Exception ex) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }

}
