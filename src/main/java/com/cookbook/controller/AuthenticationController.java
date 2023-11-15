package com.cookbook.controller;

import com.cookbook.domain.dto.LoginResponseDTO;
import com.cookbook.domain.dto.RegistrationDTO;
import com.cookbook.domain.dto.UserDTO;
import com.cookbook.domain.entity.UserEntity;
import com.cookbook.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserEntity registerUser(@RequestBody RegistrationDTO body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
