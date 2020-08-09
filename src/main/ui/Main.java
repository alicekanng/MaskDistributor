package ui;

import model.Customer;
import model.DistributionList;

import java.util.Scanner;

public class Main {

    private Scanner sc;
    private DistributionList list;
    private Customer customer;

    public static void main(String[] args) {
        System.out.println("Welcome to the Mask Distributor application!");
        Application app = new Application();
        app.handleUserInput();
    }
}
