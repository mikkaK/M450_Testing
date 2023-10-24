package ch.schule.bank.junit5;

import ch.schule.PromoYouthSavingsAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Tests für das Promo-Jugend-Sparkonto.
 *
 * @author XXXX
 * @version 1.0
 */
public class PromoYouthSavingsAccountTests
{
	/**
	 * Der Test.
	 */
	@Test
	public void test()
	{
		PromoYouthSavingsAccount promoYouthSavingsAccount = new PromoYouthSavingsAccount("12");

		Assertions.assertNotNull(promoYouthSavingsAccount);
	}
}
