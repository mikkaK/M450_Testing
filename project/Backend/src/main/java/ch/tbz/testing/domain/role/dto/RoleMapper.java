package ch.tbz.testing.domain.role.dto;

import ch.tbz.testing.domain.role.Role;
import ch.tbz.testing.core.generic.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper extends AbstractMapper<Role, RoleDTO> {
}
