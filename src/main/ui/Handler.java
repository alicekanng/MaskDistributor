package ui;

import exceptions.CustomerNotInListException;
import model.Customer;
import model.ForeignList;
import model.LocalList;

import java.util.Scanner;

// Handles all input of customer details and output of the corresponding customer's info
public class Handler {
    private Scanner sc;
    private LocalList localList;
    private ForeignList foreignList;
    private Customer customer;
    private Application app;
    private Classifier classifier;

    public Handler(Application app) {
        sc = app.getSc();
        this.app = app;
        localList = app.getLocalList();
        foreignList = app.getForeignList();
        classifier = new Classifier(this);
    }

    //EFFECTS: gets customer information from the user's input, constructs the customer,
    // iterates through both lists to find if the customer is already in the list
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
    //EFFECTS: adds customer to distribution list when prompted
    public void handleAddCustomer() {
        getCustomerInfo();
        classifier.addCustomerBasedOnAddress(customer);
        System.out.println("Successfully added customer into distribution list.");
        System.out.println("Distribution list saved.");
        app.printInstructions();
    }

    //EFFECTS: gets number of masks customer will receive when prompted
    public void handleGetMasks() {
        getCustomerInfo();
        try {
            classifier.getMasksBasedOnAddress(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        }
        System.out.println("The number of masks given to customer "
                + customer.getName() + " is " + customer.getMasks() + ".");
        app.printInstructions();
    }

    //EFFECTS: gets the date customer will receive their masks when prompted
    public void handleGetDate() {
        getCustomerInfo();
        try {
            classifier.getDateBasedOnAddress(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        }
        System.out.println("Customer " + customer.getName() + " will receive their masks in "
                + customer.getReceiveDate() + " days.");
        app.printInstructions();
    }

    //EFFECTS: gets position of customer in the queue when prompted
    public void handleGetPosition() {
        getCustomerInfo();
        try {
            classifier.getPositionBasedOnAddress(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        }
        System.out.println("Customer " + customer.getName() + " is currently at position "
                + customer.getPosition() + " in the queue.");
        app.printInstructions();
    }

    //MODIFIES: this
    //EFFECTS: deletes customer from distribution list when prompted
    public void handleDelete() {
        getCustomerInfo();
        try {
            classifier.deleteCustomerBasedOnAddress(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        }
        System.out.println("Successfully deleted customer from the distribution list.");
        System.out.println("Distribution list saved.");
        app.printInstructions();
    }

    //EFFECTS: prints distribution list when prompted
    public void handlePrintList() {
        System.out.println("Please specify whether you would like to print the local or foreign distribution list.");
        String address = sc.next();
        classifier.printListBasedOnAddress(address);
        app.printInstructions();
    }

    //EFFECTS: terminates the program when prompted
    public void endProgram() {
        System.out.println("Ending application.");
        sc.close();
        System.exit(0);
    }

    //getters:
    public LocalList getLocalList() {
        return localList;
    }

    public ForeignList getForeignList() {
        return foreignList;
    }

}
