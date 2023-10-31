package ch.tbz.demo.domain.addressbook;

import org.apache.tomcat.jni.Address;

import java.util.Comparator;

public class LastnameFirstnameRegistrationDatecomparator implements Comparator<Addressbook> {

	@Override
	public int compare(Addressbook a1, Addressbook a2) {
		// Compare last names
		int lastNameComparison = a1.getLastName().compareTo(a2.getLastName());
		if (lastNameComparison != 0) {
			return lastNameComparison;
		}

		// If last names are the same, compare first names
		int firstNameComparison = a1.getFirstName().compareTo(a2.getFirstName());
		if (firstNameComparison != 0) {
			return firstNameComparison;
		}

		return 0;
	}
	

}
