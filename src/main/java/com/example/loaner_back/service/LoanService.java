package com.example.loaner_back.service;

import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.entity.UserEntity;
import com.example.loaner_back.repository.LoanRepository;
import com.example.loaner_back.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LoanService {
    LoanRepository loanRepository;
    UserRepository userRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    public List<LoanEntity> getAllLoans() {
        return loanRepository.findAll();
    }

    public Optional<LoanEntity> getSingleLoanById(long id) {
        return loanRepository.findById(id);
    }

    public long getSingleLoanByNameAndReturnId(String name) {
        LoanEntity loan = loanRepository.findByName(name).orElseThrow(RuntimeException::new);
        return loan.getId();
    }

    public List<LoanEntity> getAllLoansFromLender(String name) {
        return loanRepository
                .findByLoanCreator
                        (userRepository
                                .findByEmail(name)
                                .orElseThrow(RuntimeException::new)
                        )
                .orElseGet(Collections::emptyList);
    }

    public void createLoan(LoanEntity LoanEntity) {
        loanRepository.save(LoanEntity);
    }

    public void deleteLoan(long id) {
        if (loanRepository.existsById(id)) {
            loanRepository.deleteById(id);
        }
    }
}
