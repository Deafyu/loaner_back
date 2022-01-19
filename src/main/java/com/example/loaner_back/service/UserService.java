package com.example.loaner_back.service;

import com.example.loaner_back.dto.UserDto;
import com.example.loaner_back.entity.UserEntity;
import com.example.loaner_back.exception.UserAlreadyExistException;
import com.example.loaner_back.repository.LoanRepository;
import com.example.loaner_back.repository.RoleRepository;
import com.example.loaner_back.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {
    LoanRepository loanRepository;
    UserRepository userRepository;
    RoleRepository roleRepository;
    @Autowired
    public UserService(LoanRepository loanRepository, UserRepository userRepository, RoleRepository roleRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> getSingleUserById(long id) {
        return userRepository.findById(id);
    }

    public long getSingleUserByNameAndReturnId(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        return user.getId();
    }
    public Optional<List<UserEntity>> getUsersByRole(String roleName) {
        return userRepository.findByRoles(roleRepository.findByName(roleName));
    }

    public void createUser(UserEntity UserEntity) {
        userRepository.save(UserEntity);
    }

    boolean emailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
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
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserEntity user = new UserEntity();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        System.out.println(roleRepository.findByName("ROLE_USER").getName());
        user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));

        return userRepository.save(user);
    }

    public Optional<UserEntity> getUserByEmail(String name) {
        return userRepository.findByEmail(name);
    }
}
