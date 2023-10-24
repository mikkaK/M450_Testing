package com.example.demo.domain.userprofile;

import com.example.demo.core.generic.AbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.NonUniqueResultException;
import java.util.*;

@Service
public class UserProfileServiceImpl extends AbstractServiceImpl<UserProfile> implements UserProfileService{

    //Initialize repositories
    private final UserProfileRepository userProfileRepository;

    //Autowire repositories
    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        super(userProfileRepository);
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED) //Allow dirty reads
    public List<UserProfile> findAllUserProfiles(int page, int pageSize, String sortBy, boolean asc) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).ascending()); //Declare and initialize page request for results ascending
        if (!asc){ // is asc false
            pageable = PageRequest.of(page, pageSize, Sort.by(sortBy).descending()); //Assign new page request for results descending
        }
        return userProfileRepository.findAll(pageable).stream().toList(); //return result list
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED) //Read only committed, Dirty read not allowed
    public UserProfile findUserProfileById(UUID id) {
        return userProfileRepository.findUserProfileByUser_Id(id).orElseThrow(NoSuchElementException::new); //return profile by user id or throw NoSuchElementException
    }

    @Override
    @Transactional
    public UserProfile createUserProfile(UserProfile userProfile) {
        if (userProfileRepository.findUserProfileByUser_Id(userProfile.getUser().getId()).isEmpty()){ //check if user doesn't have a profile
            userProfile.setCreatedDate(new Date()); // Set created date on profile
            return userProfileRepository.save(userProfile); //save and return created user profile
        } else { //if user already has profile
            throw new NonUniqueResultException();  //throw NonUniqueResultException
        }
    }

    @Override
    @Transactional
    public UserProfile updateUserProfile(UUID id, UserProfile userProfile) {
        Optional<UserProfile> previousProfile = userProfileRepository.findUserProfileByUser_Id(id); //find optional of previous profile by user id
        if (previousProfile.isPresent()) { //If user profile exists
            UserProfile updatedProfile = previousProfile.get(); //declare and initialize updated profile with previous profile
            updatedProfile.setProfilePictureURL(userProfile.getProfilePictureURL()); //set updated profilePictureURL
            updatedProfile.setAddress(userProfile.getAddress()); //set updated address
            updatedProfile.setAge(userProfile.getAge()); //set updated age
            updatedProfile.setBirthDate(userProfile.getBirthDate()); // set updated birthday
            updatedProfile.setLastModifiedDate(new Date()); // set modified date
            return userProfileRepository.save(updatedProfile); //save and return updated profile
        } else { //if user doesn't have a profile
            throw new NoSuchElementException(); //throw NoSuchElementException
        }
    }

    @Override
    @Transactional
    public void deleteUserProfile(UUID id) { //find user profile by user id, delete user profile by profile id or throw NoSuchElementException
        userProfileRepository.deleteById(userProfileRepository.findUserProfileByUser_Id(id).orElseThrow(NoSuchElementException::new).getId());
    }
}
