package ch.bbw.addressbook;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressDAO_Memory implements AddressDAO {
		
	private List<Address> addresses = new ArrayList<>();

	@Override
	public boolean isAvailable() {
		return false;
	}

	@Override
	public int getUniqueId() {
		return 0;
	}

	// CRUD Commands: Create Read Update Delete
	public void create(Address address) {
		address.setId(addresses.size()+1);
		addresses.add(address);
	}
	
	public Address read(int id) {
		return addresses.get(id-1);
	}
	public List<Address> readAll() {
		return addresses;
	}

	public void update(Address address) {
		// TODO: update, not implemented yet
	}
	
	public void delete(int id) {
		// TODO: delete, not implemented yet
	}

}
