package com.example.loaner_back.service;

import com.example.loaner_back.dto.Application.ApplicationAnswerDto;
import com.example.loaner_back.dto.Application.LenderApplicationDto;
import com.example.loaner_back.dto.Application.LoanApplicationDto;
import com.example.loaner_back.entity.ApplicationEntity;
import com.example.loaner_back.repository.ApplicationRepository;
import com.example.loaner_back.repository.LoanRepository;
import com.example.loaner_back.repository.UserRepository;
import com.example.loaner_back.utils.Type;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ApplicationService {
    ApplicationRepository applicationRepository;
    LoanRepository loanRepository;
    UserRepository userRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, LoanRepository loanRepository, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    public void createNewLoanApplication(LoanApplicationDto loanApplicationDto) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setApplicationCreator(userRepository
                .findByEmail(loanApplicationDto
                        .getUserEmail())
                .orElseThrow());
        applicationEntity
                .setApplicationReceiver(userRepository
                        .findByEmail(loanApplicationDto
                                .getUserEmail())
                        .orElseThrow());
        applicationEntity.setType(Type.LOAN);
        applicationEntity.setDescription(loanApplicationDto.getDescription());
        applicationEntity.setName(loanApplicationDto.getName());
        applicationRepository.save(applicationEntity);
    }

    public void createNewLenderApplication(LenderApplicationDto lenderApplicationDto) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setApplicationCreator(userRepository
                .findByEmail(lenderApplicationDto
                        .getUserEmail())
                .orElseThrow());
        applicationEntity.setType(Type.LENDER);
        applicationEntity.setDescription(lenderApplicationDto.getDescription());
        applicationEntity.setName(lenderApplicationDto.getName());
        applicationRepository.save(applicationEntity);
    }

    public ApplicationEntity getApplicationEntity(long id) {
        return applicationRepository.getById(id);
    }

    public List<ApplicationEntity> getAllApplicationEntities(Type type) {
        return applicationRepository.findAllByType(type);
    }

    public String answerApplication(ApplicationAnswerDto dto) {
        ApplicationEntity applicationEntity = applicationRepository.findById(dto.getApplicationId()).orElseThrow();

        applicationEntity.setAccepted(dto.isAccepted());
        return dto.getMessage();
    }
}
