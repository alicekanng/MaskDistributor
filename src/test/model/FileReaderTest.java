package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.FileReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// tests for FileReader class
//code referenced from TellerApp project
public class FileReaderTest {
    private static String LOCAL_PATHNAME = "./data/testLocalListFile.txt";
    private static String FOREIGN_PATHNAME = "./data/testForeignListFile.txt";
    private FileReader testReader;

    @BeforeEach
    public void runBefore() {
        testReader = new FileReader();
    }

    @Test
    public void testParseCustomersLocalListFile() {
        try {
            List<Customer> localCustomers = testReader.readCustomers(new File(LOCAL_PATHNAME));

            Customer local1 = localCustomers.get(0);
            assertEquals("Alice Kang", local1.getName());
            assertEquals("North Vancouver, BC", local1.getAddress());
            assertEquals(19, local1.getAge());
            assertEquals("None", local1.getConditions());

            Customer local2 = localCustomers.get(1);
            assertEquals("Emma Stone", local2.getName());
            assertEquals("Surrey, BC", local2.getAddress());
            assertEquals(30, local2.getAge());
            assertEquals("Asthma", local2.getConditions());
        } catch (IOException e) {
            fail("Caught invalid IOException.");
        }
    }

    @Test
    public void testParseCustomersForeignListFile() {
        try {
            List<Customer> foreignCustomers = testReader.readCustomers(new File(FOREIGN_PATHNAME));

            Customer foreign1 = foreignCustomers.get(0);
            assertEquals("Jennifer Aniston", foreign1.getName());
            assertEquals("New York City, NY", foreign1.getAddress());
            assertEquals(35, foreign1.getAge());
            assertEquals("Pregnant", foreign1.getConditions());

            Customer foreign2 = foreignCustomers.get(1);
            assertEquals("Steve Martin", foreign2.getName());
            assertEquals("Toronto, ON", foreign2.getAddress());
            assertEquals(78, foreign2.getAge());
            assertEquals("None", foreign2.getConditions());
        } catch (IOException e) {
            fail("Caught invalid IOException.");
        }
    }

    @Test
    public void testIOException() {
        try {
            testReader.readCustomers(new File("./data/weirdFile.txt"));
        } catch (IOException e) {
            //do nothing
        }
    }
}
