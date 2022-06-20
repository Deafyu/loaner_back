package com.example.loaner_back.controllers;

import com.example.loaner_back.Responses.JwtResponse;
import com.example.loaner_back.dto.LoginDto;
import com.example.loaner_back.dto.UserDto;
import com.example.loaner_back.exception.UserAlreadyExistException;
import com.example.loaner_back.jwt.JwtUtils;
import com.example.loaner_back.security.UserDetailsImpl;
import com.example.loaner_back.service.UserService;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(makeFinal = true)
@RestController
public class HomeController {
    UserService userService;
    AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;

    @Autowired
    public HomeController(UserService userService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles));
        } catch (Exception ex) {
            return new ResponseEntity(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity showRegisterForm(@RequestBody UserDto userEntity) throws UserAlreadyExistException {
        try {
            userService.registerNewUserAccount(userEntity);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
