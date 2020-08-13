package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// tests for Customer class
public class CustomerTest {
    private Customer testCustomer;

    @BeforeEach
    public void runBefore() {
        testCustomer = new Customer("Alice Kang",
                "North Vancouver, BC",
                19,
                "None");
    }

    //tests for getters that are not called indirectly from other tests
    @Test
    public void testGetName() {
        assertEquals("Alice Kang", testCustomer.getName());
    }

    @Test
    public void testGetAddress() {
        assertEquals("North Vancouver, BC", testCustomer.getAddress());
    }

    //tests for overriden equals and hashcode
    @Test
    public void testEqualsHashCode() {
        Customer otherCustomer = new Customer("Alice Kang",
                "North Vancouver, BC",
                19,
                "None");
        assertTrue(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertEquals(testCustomer.hashCode(), otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentClasses() {
        String otherCustomer = "Alice Kang";
        assertFalse(testCustomer.equals(otherCustomer) && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentName() {
        Customer otherCustomer = new Customer("Jennifer Aniston",
                "North Vancouver, BC",
                19,
                "None");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentAge() {
        Customer otherCustomer = new Customer("Alice Kang",
                "North Vancouver, BC",
                30,
                "None");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentAddress() {
        Customer otherCustomer = new Customer("Alice Kang",
                "Surrey, BC",
                19,
                "None");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentConditions() {
        Customer otherCustomer = new Customer("Alice Kang",
                "North Vancouver, BC",
                19,
                "Asthma");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentNameAddress() {
        Customer otherCustomer = new Customer("Alex Kang",
                "Surrey, BC",
                19,
                "None");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentNameAge() {
        Customer otherCustomer = new Customer("Alex Kang",
                "North Vancouver, BC",
                30,
                "None");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentNameConditions() {
        Customer otherCustomer = new Customer("Alex Kang",
                "North Vancouver, BC",
                19,
                "Asthma");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentAddressAge() {
        Customer otherCustomer = new Customer("Alice Kang",
                "Surrey, BC",
                30,
                "None");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentAddressConditions() {
        Customer otherCustomer = new Customer("Alice Kang",
                "Surrey, BC",
                19,
                "Asthma");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentAgeConditions() {
        Customer otherCustomer = new Customer("Alice Kang",
                "North Vancouver, BC",
                30,
                "Asthma");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentNameAddressAge() {
        Customer otherCustomer = new Customer("Alex Kang",
                "Surrey, BC",
                30,
                "None");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentAddressAgeConditions() {
        Customer otherCustomer = new Customer("Alice Kang",
                "Surrey, BC",
                30,
                "Asthma");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentAgeConditionsName() {
        Customer otherCustomer = new Customer("Alex Kang",
                "North Vancouver, BC",
                30,
                "Asthma");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeDifferentConditionsNameAddress() {
        Customer otherCustomer = new Customer("Alex Kang",
                "Surrey, BC",
                19,
                "Asthma");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeAllDifferent() {
        Customer otherCustomer = new Customer("Jennifer Aniston",
                "Surrey, BC",
                30,
                "Asthma");
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
        assertFalse(testCustomer.hashCode() == otherCustomer.hashCode());
    }

    @Test
    public void testEqualsHashCodeNull() {
        Customer otherCustomer = null;
        assertFalse(testCustomer.equals(otherCustomer)
                && otherCustomer.equals(testCustomer));
    }
}
