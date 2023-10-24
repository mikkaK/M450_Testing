package ch.schule.bank.junit5;

import ch.schule.Account;
import ch.schule.Booking;
import ch.schule.SalaryAccount;
import ch.schule.SavingsAccount;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Tests für die Klasse Account.
 *
 * @author xxxx
 * @version 1.0
 */
public class AccountTests {
    /**
     * Tested die Initialisierung eines Kontos.
     */
    @Test
    public void testInit() {
        Account account = new Account("12");

        assertNotNull(account);
    }

    /**
     * Testet das Einzahlen auf ein Konto.
     */
    @Test
    public void testDeposit() {
        Account account = new Account("12");

        assertEquals(0, account.getBalance());

        account.deposit(1234566789, 2300);

        assertEquals(2300, account.getBalance());
    }

    /**
     * Testet das Abheben von einem Konto.
     */
    @Test
    public void testWithdraw() {
        Account account = new Account("12");

        assertEquals(0, account.getBalance());

        account.withdraw(1234566789, 2300);

        assertEquals(-2300, account.getBalance());
    }


    /**
     * teste the canTransact Flag
     */
    @Test
    public void testCanTransact() {
        Account account = new Account("12");

        assertEquals(0, account.getBalance());

        account.withdraw(1234566789, -2300);

        assertEquals(0, account.getBalance());

        account.deposit(123456789, -2300);

        assertEquals(0, account.getBalance());
    }

    /**
     * Experimente mit print().
     */
    @Test
    public void testPrint() {
        Account account = new Account("12");

        account.print();
    }

    /**
     * Experimente mit print(year,month).
     */
    @Test
    public void testMonthlyPrint() {
        Account account = new Account("12");

        account.print(2020, 3);
    }

}
