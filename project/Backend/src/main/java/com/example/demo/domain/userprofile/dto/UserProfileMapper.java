package com.example.demo.domain.userprofile.dto;

import com.example.demo.core.generic.AbstractMapper;
import com.example.demo.domain.userprofile.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

//Maps target and source property using spring dependencies, ignores unmapped targets
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper extends AbstractMapper<UserProfile, UserProfileDTO> {
}
