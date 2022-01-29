package com.example.loaner_back.threads;

import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.service.LoanService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.concurrent.Callable;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LoanThread implements Callable<List<LoanEntity>> {

    LoanService loanService;

    public LoanThread(LoanService loanService) {
        this.loanService = loanService;
    }

    @Override
    public List<LoanEntity> call() throws Exception {
        return loanService.getAllLoans();
    }
}
