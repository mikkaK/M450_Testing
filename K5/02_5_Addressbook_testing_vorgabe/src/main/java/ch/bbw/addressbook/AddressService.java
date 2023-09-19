package ch.bbw.addressbook;


import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressService {

	// field injectiuon for heavier testing
	// @Autowired
	private AddressDAO addressDAO;

	// constructor injection for easy to test
	public AddressService(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}

	public List<Address> getAllAddresses() {
		List<Address> addresses = addressDAO.readAll();

		// TODO: Hier Sortierung einbauen
		
		// TODO: Order by lastname
//		// Einfache Sortiervariante ohne externen eigenständigen Comparator
//		addresses.sort( (a1, a2) -> { 
//			return a1.getLastname().compareTo(a2.getLastname()); 
//		});
		
		addresses.sort(new LastnameFirstnameRegistrationDatecomparator());
		
		return addresses;
	}
	
	public void registerAddress(Address address) {
		// TODO: Hier Registrierungsdatum setzen
		address.setRegistrationDate(new Date());
		addressDAO.create(address);
	}

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}


	public boolean query(String query) {
		return addressDAO.isAvailable();
	}
	@Override
	public String toString() {
		return "Using database with id: " + String.valueOf(addressDAO.getUniqueId());
	}
}
