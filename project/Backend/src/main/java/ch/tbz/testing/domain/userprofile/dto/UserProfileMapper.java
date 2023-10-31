package ch.tbz.testing.domain.userprofile.dto;

import ch.tbz.testing.core.generic.AbstractMapper;
import ch.tbz.testing.domain.userprofile.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

//Maps target and source property using spring dependencies, ignores unmapped targets
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper extends AbstractMapper<UserProfile, UserProfileDTO> {
}
