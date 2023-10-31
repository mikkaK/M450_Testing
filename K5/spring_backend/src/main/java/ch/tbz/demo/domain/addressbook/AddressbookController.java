package ch.tbz.demo.domain.addressbook;

import ch.tbz.demo.domain.addressbook.dto.AddressbookDTO;
import ch.tbz.demo.domain.addressbook.dto.AddressbookMapper;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/adressbook")
public class AddressbookController {

  private final AddressbookService addressbookService;
  private final AddressbookMapper addressbookMapper;

  @Autowired
  public AddressbookController(AddressbookService addressbookService, AddressbookMapper addressbookMapper) {
    this.addressbookService = addressbookService;
    this.addressbookMapper = addressbookMapper;
  }

  @GetMapping("/{id}")
  public ResponseEntity<AddressbookDTO> retrieveById(@PathVariable UUID id) {
    Addressbook addressbook = addressbookService.findById(id);
    return new ResponseEntity<>(addressbookMapper.toDTO(addressbook), HttpStatus.OK);
  }

  @GetMapping({"", "/"})
  public ResponseEntity<List<AddressbookDTO>> retrieveAll() {
    List<Addressbook> addressbooks = addressbookService.findAll();
    return new ResponseEntity<>(addressbookMapper.toDTOs(addressbooks), HttpStatus.OK);
  }

  @PostMapping("/registerUser")
  public ResponseEntity<AddressbookDTO> registerWithoutPassword(@Valid @RequestBody AddressbookDTO addressbookDTO) {
    Addressbook addressbook = addressbookService.register(addressbookMapper.fromDTO(addressbookDTO));
    return new ResponseEntity<>(addressbookMapper.toDTO(addressbook), HttpStatus.CREATED);
  }
  @PutMapping("/{id}")
  public ResponseEntity<AddressbookDTO> updateById(@PathVariable UUID id, @Valid @RequestBody AddressbookDTO addressbookDTO) {
    Addressbook addressbook = addressbookService.updateById(id, addressbookMapper.fromDTO(addressbookDTO));
    return new ResponseEntity<>(addressbookMapper.toDTO(addressbook), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
    addressbookService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
