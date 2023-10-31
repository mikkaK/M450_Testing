package ch.tbz.testing.domain.userprofile;

import ch.tbz.testing.core.generic.AbstractService;

import java.util.List;
import java.util.UUID;

public interface UserProfileService extends AbstractService<UserProfile> {
    List<UserProfile> findAllUserProfiles(int page, int pageSize, String sortBy, boolean asc);

    UserProfile findUserProfileById(UUID id);

    UserProfile createUserProfile(UserProfile userProfile);

    UserProfile updateUserProfile(UUID id, UserProfile userProfile);

    void deleteUserProfile(UUID id);
}
