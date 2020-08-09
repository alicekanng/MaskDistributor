package ui;

import model.Customer;
import model.ForeignList;
import model.LocalList;
import persistence.FileReader;
import persistence.FileWriter;
import ui.tabs.ListTab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    private boolean running;
    private FileReader fileReader;
    private Handler handler;
    private DistributionListUI listUI;

    //EFFECTS: constructs new Application
    public Application() {
        sc = new Scanner(System.in);
        foreignList = new ForeignList();
        localList = new LocalList();
        running = true;
        fileReader = new FileReader();
        handler = new Handler(this);
        listUI = new DistributionListUI();

        //Create and set up the content pane.
//        JComponent newContentPane = localTab;
//        newContentPane.setOpaque(true);
//        frame.setContentPane(newContentPane);
//        frame.setVisible(true);
    }

    //EFFECTS: accepts user input and carries out operations while this is running
    public void handleUserInput() {
        System.out.println("\nWhat task would you like to do?");
        printInstructions();
        String str;

        loadDistributionLists();

        ListTab localListTab = listUI.getLocalTab();
        ListTab foreignListTab = listUI.getForeignTab();
        localListTab.addCustomersToListModel(localList);
        foreignListTab.addCustomersToListModel(foreignList);

        while (running) {
            if (sc.hasNext()) {
                str = sc.nextLine();
                parseInput(str);
            } else {
                handler.endProgram();
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
                    handler.handleAddCustomer();
                    break;
                case DELETE_COMMAND:
                    handler.handleDelete();
                    break;
                case PRINT_COMMAND:
                    handler.handlePrintList();
                    break;
                case QUIT_COMMAND:
                    handler.endProgram();
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
                handler.handleGetMasks();
                break;
            case GET_DATE_COMMAND:
                handler.handleGetDate();
                break;
            case GET_POSITION_COMMAND:
                handler.handleGetPosition();
                break;
        }
    }

    //EFFECTS: prints instructions for user in order to call a certain operation
    public void printInstructions() {
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
            FileWriter fileWriter = new FileWriter(new FileOutputStream(new File(LOCAL_LIST_FILE), true));
            fileWriter.write(customer);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file to save to!");
        }
    }

    //EFFECTS: save edited local distribution list to LOCAL_LIST_FILE
    public void saveForeignList(Customer customer) {
        try {
            FileWriter fileWriter = new FileWriter(new FileOutputStream(new File(FOREIGN_LIST_FILE), true));
            fileWriter.write(customer);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file to save to!");
        }
    }

    public LocalList getLocalList() {
        return localList;
    }

    public ForeignList getForeignList() {
        return foreignList;
    }

    public Scanner getSc() {
        return sc;
    }
}
