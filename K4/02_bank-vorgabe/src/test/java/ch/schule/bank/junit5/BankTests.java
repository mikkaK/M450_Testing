package ch.schule.bank.junit5;

import ch.schule.Bank;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests f�r die Klasse 'Bank'.
 *
 * @author xxxx
 * @version 1.0
 */
public class BankTests {

    static Bank ubs = new Bank();

    /**
     * Tests to create new Accounts
     */
    @Test
    public void testCreate() {

        Bank bank = new Bank();

        assertNotNull(bank);
    }
    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        ubs.print("1000");
    }
    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        Bank bank = new Bank();

        Long balanceBefore = bank.getBalance("1000");

        bank.withdraw("1000", 123456789, 2300);

        assertEquals(balanceBefore - 1000, bank.getBalance("1000"));
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        fail("toDo");
    }

    /**
     * Experimente mit print(year, month).
     */
    @Test
    public void testMonthlyPrint() {
        fail("toDo");
    }

    /**
     * Testet den Gesamtkontostand der Bank.
     */
    @Test
    public void testBalance() {
        fail("toDo");
    }

    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testTop5() {
        fail("toDo");
    }

    /**
     * Tested die Ausgabe der "top 5" konten.
     */
    @Test
    public void testBottom5() {
        fail("toDo");
    }

}
