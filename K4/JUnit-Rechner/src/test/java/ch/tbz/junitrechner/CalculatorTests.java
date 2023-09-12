package ch.tbz.junitrechner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTests {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Simple addition should work")
    void testAdd() {
        assertEquals(2, calculator.add(1, 1), "Regular addition should work");
    }

    @Test
    @DisplayName("Simple subtraction should work")
    void testSubstract() {
        assertEquals(1, calculator.substract(2, 1), "Regular subtraction should work");
    }

    @Test
    @DisplayName("Simple division should work")
    void testDivide() {
        assertEquals(2, calculator.divide(4, 2), "Regular division should work");
    }

    @Test
    @DisplayName("Simple multiplication should work")
    void testMultiply() {
        assertEquals(20, calculator.multiply(4, 5),
                "Regular multiplication should work");
    }

    @RepeatedTest(5)
    @DisplayName("Ensure correct handling of zero")
    void testMultiplyWithZero() {
        assertEquals(0, calculator.multiply(0, 5), "Multiple with zero should be zero");
        assertEquals(0, calculator.multiply(5, 0), "Multiple with zero should be zero");
    }

    @Test
    @DisplayName("Simple modulo should work")
    void testModulo() {
        assertEquals(1, calculator.modulo(5, 2), "Regular modulo should work");
    }

    @Test
    @DisplayName("Simple power should work")
    void testPower() {
        assertEquals(8, calculator.power(2, 3), "Regular power should work");
    }

    @Test
    @DisplayName("Simple root should work")
    void testRoot() {
        assertEquals(2, calculator.root(4, 2), "Regular root should work");
    }

    @Test
    @DisplayName("Simple factorial should work")
    void testFactorial() {
        assertEquals(120, calculator.factorial(5), "Regular factorial should work");
    }
}
