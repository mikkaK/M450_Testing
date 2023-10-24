package ch.schule.bank.junit5;

import ch.schule.SalaryAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests der Klasse SalaryAccount.
 *
 * @author XXX
 * @version 1.1
 */
public class SalaryAccountTests
{
	/**
	 * Der Test.
	 */
	@Test
	public void test()
	{
		SalaryAccount account = new SalaryAccount("12", 2000);
		Assertions.assertNotNull(account);
	}
}
