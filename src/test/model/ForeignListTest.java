package model;

import exceptions.CustomerNotInListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static model.ForeignList.FOREIGN_DATES;
import static org.junit.jupiter.api.Assertions.*;

// tests for ForeignList class
public class ForeignListTest {
    private ForeignList testList;
    private Customer testAdultNoCondition;
    private List<Customer> testQueue;

    @BeforeEach
    public void runBefore() {
        testList = new ForeignList();
        testAdultNoCondition = new Customer("Alice Kang",
                "New York City, NY",
                19,
                "None");
        testQueue = new LinkedList<>();
        testList.queue = testQueue;
    }

    @Test
    protected void testGetDateCustomerNoException() {
        testList.addCustomer(testAdultNoCondition);
        try {
            testList.getDateCustomer(testAdultNoCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(FOREIGN_DATES, testAdultNoCondition.getReceiveDate());
    }

    @Test
    protected void testGetDateCustomerException() {
        try {
            testList.getDateCustomer(testAdultNoCondition);
        } catch (CustomerNotInListException e) {
            //nothing
        }
        assertEquals(0, testAdultNoCondition.getReceiveDate());
    }
}
