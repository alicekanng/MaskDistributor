package persistence;

import model.Customer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// Reader that reads info from saved files in data folder
// code referenced from TellerApp project
public class FileReader {
    public static final String NAME_DELIM = ": ";
    public static final String INFO_DELIM = " / ";

    //EFFECTS: construct a new FileReader
    public FileReader() {
    }

    //EFFECTS: general reading method--returns list of customers parsed from given file
    // throw IOException if an exception is raised when opening or reading given file
    public List<Customer> readCustomers(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseCustomers(fileContent);
    }

    //EFFECTS: returns list of strings which correspond to each row of the given file
    // throw IOException if an exception is raised when opening or reading given file
    public List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    //EFFECTS: returns list of customers from list of strings parsed from the file
    public List<Customer> parseCustomers(List<String> fileContent) {
        List<Customer> customers = new LinkedList<>();
        for (String line : fileContent) {
            String nameInEachLine = splitName(line).get(0);
            List<String> infoInEachLine = splitInfo(line);
            customers.add(parseAndCreateCustomer(nameInEachLine, infoInEachLine));
        }
        return customers;
    }

    //EFFECTS: return a list of customer names parsed from the rest of the customer info on NAME_DELIM
    public List<String> splitName(String line) {
        String[] nameSplits = line.split(NAME_DELIM);
        List<String> splitNames = new ArrayList<>(Arrays.asList(nameSplits));
        return splitNames;
    }

    //EFFECTS: return a list of customer info parsed from the list of strings on INFO_DELIM
    public List<String> splitInfo(String line) {
        line = splitName(line).get(1);
        String[] infoSplits = line.split(INFO_DELIM);
        List<String> listOfSplitInfo = new ArrayList<>(Arrays.asList(infoSplits));
        return listOfSplitInfo;
    }

    //REQUIRES: input list of strings has size 4, where
    // element 0 is the name of the customer
    // element 1 is the customer's address in the format 'City, Province'
    // element 2 is the age of the customer
    // element 3 is the customer's medical conditions, if none, 'None'
    //EFFECTS: returns a customer constructed from the elements of the input list of strings
    public Customer parseAndCreateCustomer(String name, List<String> components) {
        String address = components.get(0);
        int age = Integer.parseInt(components.get(1));
        String condition = components.get(2);
        return new Customer(name, address, age, condition);
    }
}
