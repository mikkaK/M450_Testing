package ch.schule.bank.junit5;

import ch.schule.Booking;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests für die Klasse Booking.
 *
 * @author Luigi Cavuoti
 * @version 1.1
 */
public class BookingTests
{
	/**
	 * Tests f�r die Erzeugung von Buchungen.
	 */
	@Test
	public void testInitialization()
	{
		Booking booking = new Booking(123456789, 2000);

		assertNotNull(booking);

		assertEquals(2000, 	booking.getAmount());
	}

	/**
	 * Experimente mit print().
	 */
	@Test
	public void testPrint()
	{
		Booking booking = new Booking(123456789, 2000);

		booking.print(2000);
	}
}
