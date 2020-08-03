package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.FileReader;
import persistence.FileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//code referenced from TellerApp project
public class FileWriterTest {
    private static final String TEST_FILE = "./data/testFile.txt";
    private FileWriter testWriter;
    private Customer testLocalCustomer;
    private Customer testForeignCustomer;

    @BeforeEach
    public void runBefore() throws FileNotFoundException {
        testWriter = new FileWriter(new File(TEST_FILE));
        testLocalCustomer = new Customer("Alice Kang",
                "North Vancouver, BC",
                19,
                "None");
        testForeignCustomer = new Customer("Emma Stone",
                "New York City, NY",
                30,
                "Asthma");
    }

    @Test
    public void testWriteFile() {
        testWriter.write(testLocalCustomer);
        testWriter.write(testForeignCustomer);
        testWriter.close();

        try {
            FileReader testReader = new FileReader();
            List<Customer> testListMixed = testReader.readCustomers(new File(TEST_FILE));
            Customer testCheckLocal = testListMixed.get(0);
            assertEquals("Alice Kang", testCheckLocal.getName());
            assertEquals("North Vancouver, BC", testCheckLocal.getAddress());
            assertEquals(19, testCheckLocal.getAge());
            assertEquals("None", testCheckLocal.getConditions());

            Customer testCheckForeign = testListMixed.get(1);
            assertEquals("Emma Stone", testCheckForeign.getName());
            assertEquals("New York City, NY", testCheckForeign.getAddress());
            assertEquals(30, testCheckForeign.getAge());
            assertEquals("Asthma", testCheckForeign.getConditions());
        } catch (IOException e) {
            fail("Caught invalid IOException.");
        }
    }
}
