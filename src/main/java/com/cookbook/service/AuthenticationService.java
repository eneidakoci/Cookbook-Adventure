package com.cookbook.service;

import com.cookbook.domain.dto.LoginResponseDTO;
import com.cookbook.domain.dto.UserDTO;
import com.cookbook.domain.entity.UserEntity;

public interface AuthenticationService {
    UserDTO registerUser(String username, String password);
    LoginResponseDTO loginUser(String username, String password);
}
