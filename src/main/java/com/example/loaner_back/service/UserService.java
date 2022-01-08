package com.example.loaner_back.service;

import com.example.loaner_back.dto.UserDto;
import com.example.loaner_back.entity.LoanEntity;
import com.example.loaner_back.entity.UserEntity;
import com.example.loaner_back.exception.UserAlreadyExistException;
import com.example.loaner_back.repository.LoanRepository;
import com.example.loaner_back.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {
    LoanRepository loanRepository;
    UserRepository userRepository;

    @Autowired
    public UserService(LoanRepository loanRepository, UserRepository userRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getSingleUserById(long id) {
        return userRepository.findById(id);
    }

    public long getSingleUserByNameAndReturnId(String name) {
        UserEntity user = userRepository.findByName(name).orElseThrow(RuntimeException::new);
        return user.getId();
    }

    public void createUser(UserEntity UserEntity) {
        userRepository.save(UserEntity);
    }

    boolean emailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public void deleteUserById(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public UserEntity registerNewUserAccount(UserDto userDto) throws UserAlreadyExistException {
        if (emailExist(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        UserEntity user = new UserEntity();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        //   user.setRole("ROLE_USER");

        return userRepository.save(user);
    }

    public Optional<UserEntity> getUserByName(String name) {
        return userRepository.findByName(name);
    }
}
