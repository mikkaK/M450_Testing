package ch.tbz.testing.domain.authority.dto;

import ch.tbz.testing.domain.authority.Authority;
import ch.tbz.testing.core.generic.AbstractMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorityMapper extends AbstractMapper<Authority, AuthorityDTO> {
}

