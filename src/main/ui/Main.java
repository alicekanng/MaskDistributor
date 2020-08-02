package ui;

import exceptions.CustomerDidNotReceiveMasksException;
import exceptions.CustomerNotInListException;
import model.Customer;
import model.DistributionList;
import model.ForeignList;
import model.LocalList;

import java.util.Scanner;

public class Main {

    private Scanner sc;
    private DistributionList list;
    private Customer customer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Mask Distribution application! Press enter to continue.");
        Application app = new Application();
        app.handleUserInput();
    }
}


//    private Scanner sc;
//    private DistributionList list;
//    private Customer customer;
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter customer information (making sure to capitalize!):");
//
//        System.out.println("Please enter the customer's name:");
//        String name = sc.nextLine();
//
//        System.out.println("Please enter the customer's address in the format 'City, Province':");
//        String address = sc.nextLine();
//
//        System.out.println("Please enter the customer's age:");
//        int age = sc.nextInt();
//
//        System.out.println("Please enter the customer's medical conditions, if none, enter 'None':");
//        String conditions = sc.next();
//
//        System.out.println("Creating a new customer with given information:");
//        Customer customer = new Customer(name, address, age, conditions);
//
//        if (address.contains("BC")) {
//            LocalApplication localApp = new LocalApplication(customer);
//            localApp.handleUserInput();
//        } else {
//            ForeignApplication foreignApp = new ForeignApplication(customer);
//            foreignApp.handleUserInput();
//        }
//    }
//}
