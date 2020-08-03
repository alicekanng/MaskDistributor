package model;

import exceptions.CustomerNotInListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static model.DistributionList.MASK_DISTRIBUTED;
import static model.LocalList.LOCAL_DATES;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class LocalListTest {
    private LocalList testList;
    private Customer testAdultNoCondition;
    private Customer testAdultCondition;
    private Customer testSeniorNoCondition;
    private Customer testSeniorCondition;
    private Customer testChildNoCondition;
    private Customer testChildCondition;
    private List<Customer> testQueue;

    @BeforeEach
    public void runBefore() {
        testList = new LocalList();
        testAdultNoCondition = new Customer("Alice Kang",
                "North Vancouver, BC",
                19,
                "None");
        testAdultCondition = new Customer("Jennifer Aniston",
                "West Vancouver, BC",
                30,
                "Pregnant");
        testSeniorNoCondition = new Customer("Steve Martin",
                "Kitsilano, BC",
                80,
                "None");
        testSeniorCondition = new Customer("Robert Deniro",
                "Kamloops, BC",
                78,
                "Alzheimer's");
        testChildNoCondition = new Customer("Martin Freeman",
                "Richmond, BC",
                10,
                "None");
        testChildCondition = new Customer("Emily Blunt",
                "Surrey, BC",
                12,
                "Asthma");
        testQueue = new LinkedList<>();
        testList.queue = testQueue;
    }

    @Test
    public void testAddCustomer(){
        testList.addCustomer(testAdultNoCondition);
        assertEquals(testQueue, testList.printList());
    }

    /************************************************/
    //tests for getNumberOfMasks without exception
    @Test
    public void testGetNumberOfMasksAdultNoConditionNoException(){
        testList.addCustomer(testAdultNoCondition);
        try {
            testList.getNumberOfMasksCustomer(testAdultNoCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(MASK_DISTRIBUTED, testAdultNoCondition.getMasks());
    }

    @Test
    public void testGetNumberOfMasksAdultConditionNoException(){
        testList.addCustomer(testAdultCondition);
        try {
            testList.getNumberOfMasksCustomer(testAdultCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(MASK_DISTRIBUTED + 5, testAdultCondition.getMasks());
    }

    @Test
    public void testGetNumberOfMasksSeniorNoConditionNoException(){
        testList.addCustomer(testSeniorNoCondition);
        try {
            testList.getNumberOfMasksCustomer(testSeniorNoCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(MASK_DISTRIBUTED + 10, testSeniorNoCondition.getMasks());
    }

    @Test
    public void testGetNumberOfMasksSeniorConditionNoException(){
        testList.addCustomer(testSeniorCondition);
        try {
            testList.getNumberOfMasksCustomer(testSeniorCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(MASK_DISTRIBUTED + 15, testSeniorCondition.getMasks());
    }

    @Test
    public void testGetNumberOfMasksChildNoConditionNoException(){
        testList.addCustomer(testChildNoCondition);
        try {
            testList.getNumberOfMasksCustomer(testChildNoCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(MASK_DISTRIBUTED + 10, testChildNoCondition.getMasks());
    }

    @Test
    public void testGetNumberOfMasksChildConditionNoException(){
        testList.addCustomer(testChildCondition);
        try {
            testList.getNumberOfMasksCustomer(testChildCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(MASK_DISTRIBUTED + 15, testChildCondition.getMasks());
    }

    /************************************************/
    //tests for getNumberOfMasks with exception
    @Test
    public void testGetNumberOfMasksWithException(){
        try {
            testList.getNumberOfMasksCustomer(testAdultNoCondition);
        } catch (CustomerNotInListException e) {
            //nothing
        }
        assertEquals(0, testAdultNoCondition.getMasks());
    }

    /************************************************/
    //tests for getDate
    @Test
    protected void testGetDateCustomerNoException() {
        testList.addCustomer(testAdultNoCondition);
        try {
            testList.getDateCustomer(testAdultNoCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(LOCAL_DATES, testAdultNoCondition.getReceiveDate());
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

    /************************************************/
    //tests for getPosition
    @Test
    public void testGetPositionCustomerNoException(){
        testList.addCustomer(testAdultNoCondition);
        testList.addCustomer(testSeniorCondition);
        testList.addCustomer(testChildCondition);
        try {
            testList.getPositionCustomer(testSeniorCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(2, testSeniorCondition.getPosition());
    }

    @Test
    public void testGetPositionFirstCustomerNoException(){
        testList.addCustomer(testAdultNoCondition);
        testList.addCustomer(testSeniorCondition);
        testList.addCustomer(testChildCondition);
        try {
            testList.getPositionCustomer(testAdultNoCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(1, testAdultNoCondition.getPosition());
    }

    @Test
    public void testGetPositionLastCustomerNoException(){
        testList.addCustomer(testAdultNoCondition);
        testList.addCustomer(testSeniorCondition);
        testList.addCustomer(testChildCondition);
        try {
            testList.getPositionCustomer(testChildCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(3, testChildCondition.getPosition());
    }

    @Test
    public void testGetPositionCustomerException(){
        testList.addCustomer(testSeniorCondition);
        testList.addCustomer(testChildCondition);
        try {
            testList.getPositionCustomer(testAdultNoCondition);
        } catch (CustomerNotInListException e) {
            //nothing
        }
        assertEquals(0, testAdultNoCondition.getPosition());
    }

    /************************************************/
    //tests for deleteCustomer
    @Test
    public void testDeleteCustomerNoException(){
        testAdultNoCondition.setMasks(15);
        testList.addCustomer(testAdultNoCondition);
        testList.addCustomer(testSeniorCondition);
        testList.addCustomer(testChildCondition);
        try {
            testList.deleteCustomer(testAdultNoCondition);
        } catch (CustomerNotInListException e) {
            fail("Caught invalid exception");
        }
        assertEquals(testQueue, testList.printList());
    }

    @Test
    public void testDeleteCustomerNotInListException(){
        testAdultNoCondition.setMasks(15);
        testList.addCustomer(testAdultNoCondition);
        testList.addCustomer(testSeniorCondition);
        testList.addCustomer(testChildCondition);
        try {
            testList.deleteCustomer(testAdultCondition);
        } catch (CustomerNotInListException e) {
            //nothing
        }
        assertEquals(testQueue, testList.printList());
    }

    /************************************************/
    //tests for printList
    @Test
    public void testPrintList(){
        testList.addCustomer(testAdultNoCondition);
        testList.addCustomer(testSeniorCondition);
        testList.addCustomer(testChildCondition);
        assertEquals(testQueue, testList.printList());
    }

}