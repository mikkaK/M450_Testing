package ch.tbz.demo.domain.addressbook.dto;


import ch.tbz.demo.core.generic.AbstractMapper;
import ch.tbz.demo.domain.addressbook.Addressbook;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressbookMapper extends AbstractMapper<Addressbook, AddressbookDTO> {

}
