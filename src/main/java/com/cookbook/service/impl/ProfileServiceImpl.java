package com.cookbook.service.impl;

import com.cookbook.domain.dto.ProfileDTO;
import com.cookbook.domain.entity.ProfileEntity;
import com.cookbook.domain.mapper.impl.ProfileMapperImpl;
import com.cookbook.repository.ProfileRepository;
import com.cookbook.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Optional<ProfileDTO> findByProfileLink(String profileLink) {
        Optional<ProfileEntity> profileEntityOptional = profileRepository.findByProfileLink(profileLink);

        return profileEntityOptional.map(ProfileMapperImpl::profileEntityToDto);
    }
}
