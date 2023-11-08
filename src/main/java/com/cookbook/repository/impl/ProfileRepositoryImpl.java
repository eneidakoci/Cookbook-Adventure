package com.cookbook.repository.impl;
import com.cookbook.domain.entity.ProfileEntity;
import com.cookbook.repository.ProfileRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {
    @Override
    public Optional<ProfileEntity> findByProfileLink(String profileLink) {
        return Optional.empty();
    }
}
