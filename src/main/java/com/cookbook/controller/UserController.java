package com.cookbook.controller;

import com.cookbook.configuration.AdminAccess;
import com.cookbook.domain.dto.UserDTO;
import com.cookbook.domain.mapper.UserMapper;
import com.cookbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @AdminAccess
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAllUsers() {
        List<UserDTO> userDTOs = userRepository.findAll().stream().map(UserMapper::userEntityToDto).toList();

        if (userDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping("/{userId}")
    @AdminAccess
    public ResponseEntity<UserDTO> findUserById(@PathVariable Integer userId) {
        UserDTO user = UserMapper.userEntityToDto(userRepository.findUserEntityByUserId(userId));

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @AdminAccess
    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Integer userId) {
        UserDTO deletedUser = UserMapper.userEntityToDto(userRepository.deleteUserByUserId(userId));
        if (deletedUser != null) {
            return ResponseEntity.ok(deletedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
