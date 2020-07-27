package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    private Customer testCustomer;

    @BeforeEach
    public void runBefore() {
        testCustomer = new Customer("Alice Kang",
                "Vancouver, BC",
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
        assertEquals("Vancouver, BC", testCustomer.getAddress());
    }
}
