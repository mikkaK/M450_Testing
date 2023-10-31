package ch.tbz.testing.domain.user.dto;


import ch.tbz.testing.domain.user.User;
import ch.tbz.testing.core.generic.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends AbstractMapper<User, UserDTO> {
  User fromUserRegisterDTO(UserRegisterDTO dto);
}
