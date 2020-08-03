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
        System.out.println("Welcome to the Mask Distributor application!");
        Application app = new Application();
        app.handleUserInput();
    }
}
