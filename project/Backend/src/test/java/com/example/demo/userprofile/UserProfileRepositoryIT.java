package com.example.demo.userprofile;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserService;
import com.example.demo.domain.userprofile.UserProfile;
import com.example.demo.domain.userprofile.UserProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS) //Recreates database
@SpringBootTest
@ActiveProfiles("test")
@Sql("/test-data-repository.sql")
class UserProfileRepositoryIT {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserService userService;

    @Test
    public void getPageOfUserProfilesTest(){
        Pageable pageable = PageRequest.of(0, 2, Sort.by("age").ascending());
        List<UserProfile> userProfiles = userProfileRepository.findAll(pageable).stream().toList();

        Assertions.assertNotEquals(0, userProfiles.size());
        Assertions.assertTrue(userProfiles.get(0).getAge() <= userProfiles.get(1).getAge());
    }

    @Test
    public void getUserProfileByIdTest(){
        UUID id = UUID.fromString("2ce9437a-b8de-11ed-afa1-0242ac120002");
        Optional<UserProfile> userProfile = userProfileRepository.findUserProfileByUser_Id(id);

        Assertions.assertTrue(userProfile.isPresent());
        Assertions.assertEquals(id, userProfile.get().getUser().getId());
        Assertions.assertEquals("James", userProfile.get().getUser().getFirstName());
        Assertions.assertEquals("Bond", userProfile.get().getUser().getLastName());
        Assertions.assertEquals("admin2@example.com", userProfile.get().getUser().getEmail());
        Assertions.assertEquals("Admin Address", userProfile.get().getAddress());
        Assertions.assertEquals(12, userProfile.get().getAge());
        Assertions.assertEquals("http://profile-picture.com", userProfile.get().getProfilePictureURL());
    }

    @Test
    public void updateUserProfile(){
        UUID id = UUID.fromString("020a4514-b8de-11ed-afa1-0242ac120002");
        Optional<UserProfile> userProfile = userProfileRepository.findUserProfileByUser_Id(id);
        Date date = new Date();

        Assertions.assertTrue(userProfile.isPresent());
        Assertions.assertEquals(id, userProfile.get().getUser().getId());
        Assertions.assertEquals("Tyler", userProfile.get().getUser().getFirstName());
        Assertions.assertEquals("Durden", userProfile.get().getUser().getLastName());
        Assertions.assertEquals("user2@example.com", userProfile.get().getUser().getEmail());
        Assertions.assertEquals("User Address", userProfile.get().getAddress());
        Assertions.assertEquals(12, userProfile.get().getAge());
        Assertions.assertEquals("http://profile-picture.com", userProfile.get().getProfilePictureURL());

        userProfile.get().setProfilePictureURL("Updated User Profile as test");
        userProfile.get().setAge(69);
        userProfile.get().setAddress("Updated User Address");
        userProfile.get().setBirthDate(date);

        userProfileRepository.save(userProfile.get());

        Optional<UserProfile> updatedProfile = userProfileRepository.findUserProfileByUser_Id(id);

        Assertions.assertEquals("Updated User Profile as test", updatedProfile.get().getProfilePictureURL());
        Assertions.assertEquals(69, updatedProfile.get().getAge());
        Assertions.assertEquals("Updated User Address", updatedProfile.get().getAddress());
        Assertions.assertEquals(date, updatedProfile.get().getBirthDate());
    }


    @Test
    void createUserProfileTest(){
        UUID userUUID = UUID.fromString("ac2b5e04-add8-4558-bd61-c7e869a1d872");
        User user = userService.findById(userUUID);

        UserProfile newProfile = new UserProfile(
                "http://newProfileURL.com",
                12,
                "Created address",
                new Date(),
                new Date(),
                new Date(),
                user
        );

        userProfileRepository.save(newProfile);

        Optional<UserProfile> savedProfile = userProfileRepository.findUserProfileByUser_Id(userUUID);

        Assertions.assertTrue(savedProfile.isPresent());
        Assertions.assertEquals("Testi", savedProfile.get().getUser().getFirstName());
        Assertions.assertEquals("Create", savedProfile.get().getUser().getLastName());
        Assertions.assertEquals("create@example.com", savedProfile.get().getUser().getEmail());
        Assertions.assertEquals("Created address", savedProfile.get().getAddress());
        Assertions.assertEquals(12, savedProfile.get().getAge());
        Assertions.assertEquals("http://newProfileURL.com", savedProfile.get().getProfilePictureURL());
    }

    @Test
    void deleteUserProfileTest(){
        UUID userId = UUID.fromString("d0e514f0-9546-4abf-9500-9a63634b28e1");
        Optional<UserProfile> existingProfile = userProfileRepository.findUserProfileByUser_Id(userId);
        Assertions.assertTrue(existingProfile.isPresent());

        userProfileRepository.deleteById(existingProfile.get().getId());

        Optional<UserProfile> deletedProfile = userProfileRepository.findUserProfileByUser_Id(userId);

        Assertions.assertFalse(deletedProfile.isPresent());
    }
}
