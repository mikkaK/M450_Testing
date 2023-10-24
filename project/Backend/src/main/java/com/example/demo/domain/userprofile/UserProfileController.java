package com.example.demo.domain.userprofile;

import com.example.demo.domain.userprofile.dto.UserProfileDTO;
import com.example.demo.domain.userprofile.dto.UserProfileMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2 //Logging
@RequestMapping("/userprofile") //Controller base URL
public class UserProfileController {

    //initialize Services and Mappers
    private final UserProfileService userProfileService;
    private final UserProfileMapper userProfileMapper;

    //Autowire Services and mappers (done by Spring boot)
    @Autowired
    public UserProfileController(UserProfileService userProfileService, UserProfileMapper userProfileMapper) {
        this.userProfileService = userProfileService;
        this.userProfileMapper = userProfileMapper;
    }

    //Get request for List of User profiles
    @GetMapping("") //Request url "localhost:8080/userprofile
    @PreAuthorize("hasAuthority('USER_MODIFY') or hasAuthority('USER_DELETE')") //Pre-authorized user needs authority USER_MODIFY or USER_DELETE
    @Operation(summary = "Get a page of user profiles sorted and filter") //Swagger endpoint description
    @ApiResponse(responseCode = "200", description = "Found List of user profiles",content = {@Content(mediaType ="application/json")}) //Swagger endpoint details
    public ResponseEntity<List<UserProfileDTO>> getAllUserProfiles(@RequestParam int pageSize, //Required param page size
                                                                   @RequestParam int page,     //Required page index
                                                                   @RequestParam String sortBy,//Required param sort by argument
                                                                   @RequestParam boolean asc){ //Required param return list ascending or descending
        log.info("User: " + SecurityContextHolder.getContext().getAuthentication() + "requested Page: " + page + " Page size: " + pageSize + " Sort by: " + sortBy + " Order Ascending: " + asc);
        List<UserProfileDTO> userProfileDTOs = userProfileMapper.toDTOs(userProfileService.findAllUserProfiles(page, pageSize, sortBy, asc)); //returns mapped dto list of user profile
        return new ResponseEntity<>(userProfileDTOs, HttpStatus.OK); //return response entity with list as body
    }

    //Get request for user profile by user id
    @GetMapping("/{id}") //Request url "localhost:8080/userprofile/{id}
    @PreAuthorize("hasAuthority('USER_MODIFY') or hasAuthority('USER_DELETE') or @userPermissionEvaluator.isOwnProfile(authentication.principal.user,#id)") //Authorities USER_MODIFY/USER_DELETE needed or user id for own profile
    @Operation(summary = "Find a user profile by user id")
    @ApiResponse(responseCode = "200", description = "Found user profile by id",content = {@Content(mediaType ="application/json")})
    public ResponseEntity<UserProfileDTO> getUserProfileById(@PathVariable UUID id){ //Required Param user id
        log.info("User: " + SecurityContextHolder.getContext().getAuthentication() + "requested user profile for user id: " + id);
        UserProfile userProfile = userProfileService.findUserProfileById(id); //returns user profile found on provided id
        return new ResponseEntity<>(userProfileMapper.toDTO(userProfile), HttpStatus.OK); //return response entity with user profile dto
    }

    //Create request for new user profile
    @PostMapping("")//Request url "localhost:8080/userprofile
    @Operation(summary = "Save a user profile")
    @PreAuthorize("@userPermissionEvaluator.isOwnProfile(authentication.principal.user,#userProfileDTO.user.id)") //Only own user profile can be created
    @ApiResponse(responseCode = "201", description = "Created user profile",content = {@Content(mediaType ="application/json")})
    public ResponseEntity<UserProfileDTO> createUserProfile(@RequestBody UserProfileDTO userProfileDTO){ //Required Body userProfileDTO
        log.info("User: " + SecurityContextHolder.getContext().getAuthentication() + "requested to create user profile");
        UserProfile userProfile = userProfileService.createUserProfile(userProfileMapper.fromDTO(userProfileDTO)); //returns mapped created user profile
        return new ResponseEntity<>(userProfileMapper.toDTO(userProfile), HttpStatus.CREATED); //return response entity with created profile dto
    }

    //Update request for updating a profile
    @PutMapping("/{id}") //Request url "localhost:8080/userprofile
    @PreAuthorize("hasAuthority('USER_MODIFY') or hasAuthority('USER_DELETE') or @userPermissionEvaluator.isOwnProfile(authentication.principal.user,#id)") //Authorities USER_MODIFY/USER_DELETE needed or user id for own profile
    @Operation(summary = "Update a user profile with user id")
    @ApiResponse(responseCode = "200", description = "Updated user profile by profile id",content = {@Content(mediaType ="application/json")})
    public ResponseEntity<UserProfileDTO> updateUserProfile(@PathVariable UUID id, //Required Param user id
                                                            @RequestBody UserProfileDTO userProfileDTO){ //Required Body updated profile dto
        log.info("User: " + SecurityContextHolder.getContext().getAuthentication() + "requested to update user profile for user id :" + id);
        UserProfile userProfile = userProfileService.updateUserProfile(id, userProfileMapper.fromDTO(userProfileDTO)); //returns mapped updated profile dto
        return new ResponseEntity<>(userProfileMapper.toDTO(userProfile), HttpStatus.OK); //return response entity with updated profile dto
    }

    //Delete request for deleting a profile
    @DeleteMapping("/{id}") //Request url "localhost:8080/userprofile
    @PreAuthorize("hasAuthority('USER_MODIFY') or hasAuthority('USER_DELETE') or @userPermissionEvaluator.isOwnProfile(authentication.principal.user,#id)") //Authorities USER_MODIFY/USER_DELETE needed or user id for own profile
    @Operation(summary = "Delete a user profile by user id")
    @ApiResponse(responseCode = "204", description = "Deleted user profile",content = {@Content(mediaType ="application/json")})
    public ResponseEntity<Void> deleteUserProfile(@PathVariable UUID id){ //Required Param user id
        log.info("User: " + SecurityContextHolder.getContext().getAuthentication() + "requested to delete user profile for user id :" + id);
        userProfileService.deleteUserProfile(id); //delete user profile
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //return response entity
    }
}
