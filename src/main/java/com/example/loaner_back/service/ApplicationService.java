package com.example.loaner_back.service;

import com.example.loaner_back.dto.ApplicationDto;
import com.example.loaner_back.entity.ApplicationEntity;
import com.example.loaner_back.repository.ApplicationRepository;
import com.example.loaner_back.repository.LoanRepository;
import com.example.loaner_back.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void createNewApplication(ApplicationDto applicationDto) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setApplicationCreator(userRepository
                .findByEmail(applicationDto
                        .getUserEmail())
                .orElseThrow());
        applicationEntity
                .setApplicationReceiver(userRepository
                        .findByEmail(applicationDto
                                .getUserEmail())
                        .orElseThrow());
        applicationEntity.setDescription(applicationDto.getDescription());
        applicationEntity.setName(applicationDto.getName());
        applicationRepository.save(applicationEntity);
    }
}
