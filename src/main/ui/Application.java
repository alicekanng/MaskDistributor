package ui;

import exceptions.CustomerNotInListException;
import model.Customer;
import model.ForeignList;
import model.LocalList;
import persistence.FileReader;
import persistence.FileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//code referenced from FitLifeGymKiosk practice project and TellerApp project
public class Application {

    private static final String ADD_COMMAND = "add";
    private static final String GET_MASKS_COMMAND = "get masks";
    private static final String GET_DATE_COMMAND = "get date";
    private static final String GET_POSITION_COMMAND = "get position";
    private static final String DELETE_COMMAND = "delete";
    private static final String PRINT_COMMAND = "print";
    private static final String QUIT_COMMAND = "quit";
    private static final String LOCAL_LIST_FILE = "./data/localList.txt";
    private static final String FOREIGN_LIST_FILE = "./data/foreignList.txt";

    private Scanner sc;
    private ForeignList foreignList;
    private LocalList localList;
    private Customer customer;
    private boolean running;
    private FileReader fileReader;

    //EFFECTS: constructs new Application
    public Application() {
        sc = new Scanner(System.in);
        foreignList = new ForeignList();
        localList = new LocalList();
        running = true;
        fileReader = new FileReader();
    }

    //EFFECTS: accepts user input and carries out operations while this is running
    public void handleUserInput() {
        System.out.println("\nWhat task would you like to do?");
        printInstructions();
        String str;

        loadDistributionLists();

        while (running) {
            if (sc.hasNext()) {
                str = sc.nextLine();
                parseInput(str);
            } else {
                endProgram();
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: prints options of operations for user, if quit command is inputted, this stops running
    private void parseInput(String str) {
        if (str.length() > 0) {
            printGetOptions(str);
            switch (str) {
                case ADD_COMMAND:
                    handleAddCustomer();
                    break;
                case DELETE_COMMAND:
                    handleDelete();
                    break;
                case PRINT_COMMAND:
                    handlePrintList();
                    break;
                case QUIT_COMMAND:
                    endProgram();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }

    //EFFECTS: prints options of operations that get fields of the customer
    private void printGetOptions(String str) {
        switch (str) {
            case GET_MASKS_COMMAND:
                handleGetMasks();
                break;
            case GET_DATE_COMMAND:
                handleGetDate();
                break;
            case GET_POSITION_COMMAND:
                handleGetPosition();
                break;
        }
    }

    //EFFECTS: prints instructions for user in order to call a certain operation
    private void printInstructions() {
        System.out.println("\nEnter '" + ADD_COMMAND
                + "' to add a customer into the distribution list.");

        System.out.println("Enter '" + GET_MASKS_COMMAND
                + "' to get a number of masks a customer will receive.");

        System.out.println("Enter '" + GET_DATE_COMMAND
                + "' to get the date at which a customer will receive their masks by.");

        System.out.println("Enter '" + GET_POSITION_COMMAND
                + "' to get a customer's current position in the queue.");

        System.out.println("Enter '" + DELETE_COMMAND
                + "' to delete a customer from the queue.");

        System.out.println("Enter '" + PRINT_COMMAND
                + "' to print the distribution list.");

        System.out.println("Enter '" + QUIT_COMMAND
                + "' to quit the application.");
    }

    //EFFECTS: gets customer information by the user's input
    public void getCustomerInfo() {
        System.out.println("\nEnter customer information (making sure to capitalize!):");

        System.out.println("Please enter the customer's name:");
        String name = sc.nextLine();

        System.out.println("Please enter the customer's address in the format 'City, Province':");
        String address = sc.nextLine();

        System.out.println("Please enter the customer's age:");
        int age = sc.nextInt();

        System.out.println("Please enter the customer's medical conditions, if none, enter 'None':");
        String conditions = sc.next();

        customer = new Customer(name, address, age, conditions);
        findCustomerInDistributionList(address);
    }

    //EFFECTS: for each c in the corresponding list, if c equals given customer, set customer to c
    // otherwise, do nothing
    public void findCustomerInDistributionList(String address) {
        if (address.contains("BC")) {
            for (Customer c : localList.queue) {
                if (c.equals(customer)) {
                    customer = c;
                }
            }
        } else {
            for (Customer c : foreignList.queue) {
                if (c.equals(customer)) {
                    customer = c;
                }
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: loads customers in both LOCAL_LIST_FILE and FOREIGN_LIST_FILE if they exist
    // otherwise, do nothing
    public void loadDistributionLists() {
        try {
            localList.queue = fileReader.readCustomers(new File(LOCAL_LIST_FILE));
            foreignList.queue = fileReader.readCustomers(new File(FOREIGN_LIST_FILE));
        } catch (IOException e) {
            System.out.println("There are currently no saved lists.");
        }
    }

    //EFFECTS: save edited local distribution list to LOCAL_LIST_FILE
    public void saveLocalList(Customer customer) {
        try {
            FileWriter fileWriter = new FileWriter(new File(LOCAL_LIST_FILE));
            fileWriter.write(customer);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file to save to!");
        }
    }

    //EFFECTS: save edited local distribution list to LOCAL_LIST_FILE
    public void saveForeignList(Customer customer) {
        try {
            FileWriter fileWriter = new FileWriter(new File(FOREIGN_LIST_FILE));
            fileWriter.write(customer);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file to save to!");
        }
    }


    //EFFECTS: add the customer to a list depending on whether they are local or foreign
    public void addCustomerBasedOnAddress(Customer customer) {
        if (customer.getAddress().contains("BC")) {
            localList.addCustomer(customer);
            saveLocalList(customer);
        } else {
            foreignList.addCustomer(customer);
            saveForeignList(customer);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds customer to distribution list when prompted
    public void handleAddCustomer() {
        getCustomerInfo();
        addCustomerBasedOnAddress(customer);
        System.out.println("Successfully added customer into distribution list.");
        System.out.println("Distribution list saved.");
        printInstructions();
    }

    //EFFECTS: gets number of masks a customer will receive from their appropriate list
    public void getMasksBasedOnAddress(Customer customer) throws CustomerNotInListException {
        if (customer.getAddress().contains("BC")) {
            localList.getNumberOfMasksCustomer(customer);
        } else {
            foreignList.getNumberOfMasksCustomer(customer);
        }
    }

    //EFFECTS: gets number of masks customer will receive when prompted
    public void handleGetMasks() {
        getCustomerInfo();
        try {
            getMasksBasedOnAddress(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        }
        System.out.println("The number of masks given to customer "
                + customer.getName() + " is " + customer.getMasks() + ".");
        printInstructions();
    }

    //EFFECTS: gets date a customer will receive their masks from their appropriate list
    public void getDateBasedOnAddress(Customer customer) throws CustomerNotInListException {
        if (customer.getAddress().contains("BC")) {
            localList.getDateCustomer(customer);
        } else {
            foreignList.getDateCustomer(customer);
        }
    }

    //EFFECTS: gets the date customer will receive their masks when prompted
    public void handleGetDate() {
        getCustomerInfo();
        try {
            getDateBasedOnAddress(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        }
        System.out.println("Customer " + customer.getName() + " will receive their masks in "
                + customer.getReceiveDate() + " days.");
        printInstructions();
    }

    //EFFECTS: gets position of customer in the queue of their appropriate list
    public void getPositionBasedOnAddress(Customer customer) throws CustomerNotInListException {
        if (customer.getAddress().contains("BC")) {
            localList.getPositionCustomer(customer);
        } else {
            foreignList.getPositionCustomer(customer);
        }
    }

    //EFFECTS: gets position of customer in the queue when prompted
    public void handleGetPosition() {
        getCustomerInfo();
        try {
            getPositionBasedOnAddress(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        }
        System.out.println("Customer " + customer.getName() + " is currently at position "
                + customer.getPosition() + " in the queue.");
        printInstructions();
    }

    //EFFECTS: deletes customer from their appropriate list
    public void deleteCustomerBasedOnAddress(Customer customer) throws CustomerNotInListException {
        if (customer.getAddress().contains("BC")) {
            localList.deleteCustomer(customer);
            saveLocalList(customer);
        } else {
            foreignList.deleteCustomer(customer);
            saveForeignList(customer);
        }
    }

    //MODIFIES: this
    //EFFECTS: deletes customer from distribution list when prompted
    public void handleDelete() {
        getCustomerInfo();
        try {
            deleteCustomerBasedOnAddress(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        }
        System.out.println("Successfully deleted customer from the distribution list.");
        System.out.println("Distribution list saved.");
        printInstructions();
    }

    //EFFECTS: prints appropriate distribution list
    public void printListBasedOnAddress(String address) {
        if (address.toLowerCase().equals("local")) {
            if (localList.queue.isEmpty()) {
                System.out.println("The distribution list is currently empty!");
            }
            for (Customer c : localList.queue) {
                System.out.println(c.getName() + ": "
                        + c.getAddress() + " / " + c.getAge() + " / " + c.getConditions());
            }
        } else if (address.toLowerCase().equals("foreign")) {
            if (foreignList.queue.isEmpty()) {
                System.out.println("The distribution list is currently empty!");
            }
            for (Customer c : foreignList.queue) {
                System.out.println(c.getName() + ": "
                        + c.getAddress() + " / " + c.getAge() + " / " + c.getConditions());
            }
        } else {
            System.out.println("No corresponding list found! Please try again.");
            handlePrintList();
        }
    }

    //EFFECTS: prints distribution list when prompted
    public void handlePrintList() {
        System.out.println("Please specify whether you would like to print the local or foreign distribution list.");
        String address = sc.nextLine();
        printListBasedOnAddress(address);
        printInstructions();
    }

    //EFFECTS: terminates the program when prompted
    public void endProgram() {
        System.out.println("Ending application.");
        sc.close();
    }

}
