package ch.bbw.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AddressViewController {
	
	@Autowired
	private AddressService addressService;
	
    private String firstname;
    private String lastname;
    private String phoneNumber;
    
    private String message;
    
    public AddressViewController() {
    	message = "";
	}

    private void clearFields() {
        firstname = "";
        lastname = "";
        phoneNumber = "";    	
    }
    public void saveAddress() {
        Address address = new Address(0, firstname, lastname, phoneNumber);
        addressService.registerAddress(address);
        message = "The address was saved successfully.";
        clearFields();
    }
    public List<Address> getAddresses() {
        return addressService.getAllAddresses();
    }
    
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
