package ui;

import exceptions.CustomerDidNotReceiveMasksException;
import exceptions.CustomerNotInListException;
import model.Customer;
import model.ForeignList;

import java.util.Scanner;

public class ForeignApplication {

    private static final String ADD_COMMAND = "add";
    private static final String GET_MASKS_COMMAND = "get masks";
    private static final String GET_DATE_COMMAND = "get date";
    private static final String GET_POSITION_COMMAND = "get position";
    private static final String DELETE_COMMAND = "delete";
    private static final String PRINT_COMMAND = "print";
    private static final String QUIT_COMMAND = "quit";

    private Scanner sc;
    private ForeignList foreignList;
    private Customer customer;
    private boolean running;

    //EFFECTS: constructs new LocalApplication
    public ForeignApplication(Customer customer) {
        sc = new Scanner(System.in);
        foreignList = new ForeignList();
        this.customer = customer;
        running = true;
    }

    //EFFECTS: accepts user input and carries out operations while this is running
    public void handleUserInput() {
        System.out.println("\nWhat task would you like to do?");
        printInstructions();
        String str;

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
                + "' to add the customer into the distribution list.");

        System.out.println("Enter '" + GET_MASKS_COMMAND
                + "' to get the number of masks the customer will receive.");

        System.out.println("Enter '" + GET_DATE_COMMAND
                + "' to get the date at which the customer will receive their masks by.");

        System.out.println("Enter '" + GET_POSITION_COMMAND
                + "' to get the customer's current position in the queue.");

        System.out.println("Enter '" + DELETE_COMMAND
                + "' to delete the customer from the queue.");

        System.out.println("Enter '" + PRINT_COMMAND
                + "' to print the distribution list.");

        System.out.println("Enter '" + QUIT_COMMAND
                + "' to quit the application.");
    }

    //MODIFIES: this
    //EFFECTS: adds customer to distribution list when prompted
    public void handleAddCustomer() {
        foreignList.addCustomer(customer);
        System.out.println("Successfully added customer into distribution list.");
        printInstructions();
    }

    //EFFECTS: gets number of masks customer will receive when prompted
    public void handleGetMasks() {
        try {
            foreignList.getNumberOfMasksCustomer(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        }
        System.out.println("The number of masks given to customer "
                + customer.getName() + " is " + customer.getMasks() + ".");
        printInstructions();
    }

    //EFFECTS: gets the date customer will receive their masks when prompted
    public void handleGetDate() {
        try {
            foreignList.getDateCustomer(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        }
        System.out.println("Customer " + customer.getName() + " will receive their masks in "
                + customer.getReceiveDate() + " days.");
        printInstructions();
    }

    //EFFECTS: gets position of customer in the queue when prompted
    public void handleGetPosition() {
        try {
            foreignList.getPositionCustomer(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        }
        System.out.println("Customer " + customer.getName() + " is currently at position "
                + customer.getPosition() + " in the queue.");
        printInstructions();
    }

    //MODIFIES: this
    //EFFECTS: deletes customer from distribution list when prompted
    public void handleDelete() {
        try {
            foreignList.deleteCustomer(customer);
        } catch (CustomerNotInListException e) {
            System.err.println("Given customer is not in the distribution list.");
        } catch (CustomerDidNotReceiveMasksException e) {
            System.err.println("Given customer has not received any masks yet.");
        }
        System.out.println("Successfully deleted customer from the distribution list.");
        printInstructions();
    }

    //EFFECTS: prints distribution list when prompted
    public void handlePrintList() {
        if (foreignList.queue.isEmpty()) {
            System.out.println("The distribution list is currently empty!");
        }
        for (Customer c : foreignList.queue) {
            System.out.println(c.getName() + ": " + c.getAddress() + ", " + c.getAge() + ", " + c.getConditions());
        }
        printInstructions();
    }

    //EFFECTS: terminates the program when prompted
    public void endProgram() {
        System.out.println("Ending application.");
        sc.close();
    }

}

