package com.example.demo.domain.userprofile;

import com.example.demo.core.generic.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends AbstractRepository<UserProfile> {

    void deleteById(UUID id);

    Optional<UserProfile> findUserProfileByUser_Id(UUID id);

    Page<UserProfile> findAll(Pageable pageable);
}
