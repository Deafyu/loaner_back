package com.example.loaner_back.service;

import com.example.loaner_back.dto.Application.ApplicationAnswerDto;
import com.example.loaner_back.dto.Application.LenderApplicationDto;
import com.example.loaner_back.dto.Application.LoanApplicationDto;
import com.example.loaner_back.entity.ApplicationEntity;
import com.example.loaner_back.entity.UserEntity;
import com.example.loaner_back.repository.ApplicationRepository;
import com.example.loaner_back.repository.LoanRepository;
import com.example.loaner_back.repository.RoleRepository;
import com.example.loaner_back.repository.UserRepository;
import com.example.loaner_back.utils.Type;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ApplicationService {
    ApplicationRepository applicationRepository;
    LoanRepository loanRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, LoanRepository loanRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.applicationRepository = applicationRepository;
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void createNewLoanApplication(LoanApplicationDto loanApplicationDto) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        applicationEntity.setApplicationCreator(userRepository
                .findByEmail(loanApplicationDto
                        .getSenderEmail())
                .orElseThrow());
        applicationEntity
                .setApplicationReceiver(userRepository
                        .findByEmail(loanApplicationDto
                                .getReceiverEmail())
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
        ApplicationEntity applicationEntity = applicationRepository.findById(dto.getApplicationId()).orElseThrow(NullPointerException::new);
        applicationEntity.setAccepted(dto.isAccepted());
        UserEntity user = userRepository.findById(applicationEntity.getApplicationCreator().getId()).orElseThrow(NullPointerException::new);
        user.setRoles(Set.of(roleRepository.findByName("ROLE_LENDER")));
        userRepository.save(user);
        return dto.getMessage();
    }
}
