package com.cookbook.repository;

import com.cookbook.domain.entity.ProfileEntity;

import java.util.Optional;

public interface ProfileRepository {
    Optional<ProfileEntity> findByProfileLink(String profileLink);
}
