package com.example.demo.userprofile;

import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserService;
import com.example.demo.domain.userprofile.UserProfile;
import com.example.demo.domain.userprofile.UserProfileService;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS) //Recreates database
@SpringBootTest
@ActiveProfiles("test")
@Sql("/test-data-service.sql")
class UserServiceIT {
    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @Test
    void getUserProfileListTest(){
        List<UserProfile> userProfiles = userProfileService.findAllUserProfiles(0, 4, "age", false);

        Assertions.assertEquals(3, userProfiles.size());
        Assertions.assertTrue(userProfiles.get(0).getAge() >= userProfiles.get(1).getAge());
        Assertions.assertTrue(userProfiles.get(1).getAge() >= userProfiles.get(2).getAge());
    }

    @Test
    void getUserProfileByIdTest(){
        UUID id = UUID.fromString("2ce9437a-b8de-11ed-afa1-0242ac120002");
        UserProfile userProfile = userProfileService.findUserProfileById(id);

        Assertions.assertEquals(id, userProfile.getUser().getId());
        Assertions.assertEquals("James", userProfile.getUser().getFirstName());
        Assertions.assertEquals("Bond", userProfile.getUser().getLastName());
        Assertions.assertEquals("admin2@example.com", userProfile.getUser().getEmail());
        Assertions.assertEquals("Admin Address", userProfile.getAddress());
        Assertions.assertEquals(12, userProfile.getAge());
        Assertions.assertEquals("http://profile-picture.com", userProfile.getProfilePictureURL());
    }

    @Test
    void updateUserProfileTest(){
        UUID id = UUID.fromString("020a4514-b8de-11ed-afa1-0242ac120002");
        UserProfile userProfile = userProfileService.findUserProfileById(id);
        Date date = new Date();

        Assertions.assertEquals(id, userProfile.getUser().getId());
        Assertions.assertEquals("Tyler", userProfile.getUser().getFirstName());
        Assertions.assertEquals("Durden", userProfile.getUser().getLastName());
        Assertions.assertEquals("user2@example.com", userProfile.getUser().getEmail());
        Assertions.assertEquals("User Address", userProfile.getAddress());
        Assertions.assertEquals(12, userProfile.getAge());
        Assertions.assertEquals("http://profile-picture.com", userProfile.getProfilePictureURL());

        userProfile.setProfilePictureURL("Updated User Profile as test");
        userProfile.setAge(69);
        userProfile.setAddress("Updated User Address");
        userProfile.setBirthDate(date);

        userProfileService.save(userProfile);

        UserProfile updatedProfile = userProfileService.findUserProfileById(id);

        Assertions.assertEquals("Updated User Profile as test", updatedProfile.getProfilePictureURL());
        Assertions.assertEquals(69, updatedProfile.getAge());
        Assertions.assertEquals("Updated User Address", updatedProfile.getAddress());
        Assertions.assertEquals(date, updatedProfile.getBirthDate());
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

        userProfileService.save(newProfile);

        UserProfile savedProfile = userProfileService.findUserProfileById(userUUID);

        Assertions.assertEquals("Testi", savedProfile.getUser().getFirstName());
        Assertions.assertEquals("Create", savedProfile.getUser().getLastName());
        Assertions.assertEquals("create@example.com", savedProfile.getUser().getEmail());
        Assertions.assertEquals("Created address", savedProfile.getAddress());
        Assertions.assertEquals(12, savedProfile.getAge());
        Assertions.assertEquals("http://newProfileURL.com", savedProfile.getProfilePictureURL());
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test()
    void deleteUserProfileTest(){
        UUID userId = UUID.fromString("d0e514f0-9546-4abf-9500-9a63634b28e1");
        UserProfile existingProfile = userProfileService.findUserProfileById(userId);
        Assertions.assertEquals(userId, existingProfile.getUser().getId());
        Assertions.assertEquals("Testi", existingProfile.getUser().getFirstName());
        Assertions.assertEquals("Delete", existingProfile.getUser().getLastName());
        Assertions.assertEquals("delete@example.com", existingProfile.getUser().getEmail());
        Assertions.assertEquals("Delete Address", existingProfile.getAddress());
        Assertions.assertEquals(12, existingProfile.getAge());
        Assertions.assertEquals("http://profile-picture.com", existingProfile.getProfilePictureURL());

        userProfileService.deleteById(existingProfile.getId());

        Assertions.assertThrows(NoSuchElementException.class, () -> userProfileService.findUserProfileById(userId));
    }
}
