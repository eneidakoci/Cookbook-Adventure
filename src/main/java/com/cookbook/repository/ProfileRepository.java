//package com.cookbook.repository;
//
//import com.cookbook.domain.entity.ProfileEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
//    Optional<ProfileEntity> findByProfileLink(String profileLink);
//}