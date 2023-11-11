package com.cookbook.controller;

import com.cookbook.domain.dto.ProfileDTO;
import com.cookbook.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{profileLink}")
    public ResponseEntity<ProfileDTO> findByProfileLink(@PathVariable String profileLink) {
        Optional<ProfileDTO> profileDTOOptional = profileService.findByProfileLink(profileLink);
        return profileDTOOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}