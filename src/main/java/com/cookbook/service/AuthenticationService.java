package com.cookbook.service;

import com.cookbook.domain.dto.LoginResponseDTO;
import com.cookbook.domain.entity.UserEntity;

public interface AuthenticationService {
    UserEntity registerUser(String username, String password);
    LoginResponseDTO loginUser(String username, String password);
}
