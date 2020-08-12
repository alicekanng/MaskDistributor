package ui;

import exceptions.CustomerNotInListException;
import model.Customer;
import model.ForeignList;
import model.LocalList;

import java.util.Scanner;

public class Handler {

    private Scanner sc;
    private LocalList localList;
    private ForeignList foreignList;
    private Customer customer;
    private Application app;

    public Handler(Application app) {
        sc = app.getSc();
        this.app = app;
        localList = app.getLocalList();
        foreignList = app.getForeignList();
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

    //EFFECTS: add the customer to a list depending on whether they are local or foreign
    public void addCustomerBasedOnAddress(Customer customer) {
        if (customer.getAddress().contains("BC")) {
            localList.addCustomer(customer);
            app.saveLocalList(customer);
        } else {
            foreignList.addCustomer(customer);
            app.saveForeignList(customer);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds customer to distribution list when prompted
    public void handleAddCustomer() {
        getCustomerInfo();
        addCustomerBasedOnAddress(customer);
        System.out.println("Successfully added customer into distribution list.");
        System.out.println("Distribution list saved.");
        app.printInstructions();
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
        app.printInstructions();
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
        app.printInstructions();
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
        app.printInstructions();
    }

    //EFFECTS: deletes customer from their appropriate list
    public void deleteCustomerBasedOnAddress(Customer customer) throws CustomerNotInListException {
        if (customer.getAddress().contains("BC")) {
            localList.deleteCustomer(customer);
            app.saveLocalList(customer);
        } else {
            foreignList.deleteCustomer(customer);
            app.saveForeignList(customer);
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
        app.printInstructions();
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
        String address = sc.next();
        printListBasedOnAddress(address);
        app.printInstructions();
    }

    //EFFECTS: terminates the program when prompted
    public void endProgram() {
        System.out.println("Ending application.");
        sc.close();
        System.exit(0);
    }

}
