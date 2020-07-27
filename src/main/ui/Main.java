package ui;

import exceptions.CustomerDidNotReceiveMasksException;
import exceptions.CustomerNotInListException;
import model.Customer;
import model.ForeignList;
import model.LocalList;

public class Main {
    public static void main(String[] args) {
        LocalList localList = new LocalList();
        ForeignList foreignList = new ForeignList();

        Customer alice = new Customer("Alice Kang",
                "North Vancouver, BC", 19, "None");
        Customer jennifer = new Customer("Jennifer Aniston",
                "Toronto, ON", 30, "Pregnant");
        Customer steve = new Customer("Steve Martin",
                "Kitsilano, BC", 80, "None");
        Customer robert = new Customer("Robert Deniro",
                "San Jose, CA", 78, "Alzheimer's");
        Customer martin = new Customer("Martin Freeman",
                "Richmond, BC", 10, "None");
        Customer emily = new Customer("Emily Blunt",
                "Surrey, BC", 12, "Asthma");


        //add customers to a distribution list according to their address
        System.out.println("Adding customers into appropriate distribution lists.");
        localList.addCustomer(alice);
        localList.addCustomer(steve);
        localList.addCustomer(martin);
        foreignList.addCustomer(jennifer);
        foreignList.addCustomer(robert);
        foreignList.addCustomer(emily);
        System.out.println("Local customers in BC have been added to the local list.");
        System.out.println("Foreign customers out of BC have been added to the foreign list.");

        //get number of masks given to alice, jennifer, robert
        try {
            localList.getNumberOfMasksCustomer(alice);
            foreignList.getNumberOfMasksCustomer(jennifer);
            foreignList.getNumberOfMasksCustomer(robert);
        } catch (CustomerNotInListException e) {
            System.err.println("It looks like the given customer is not in this list.");
        }
        System.out.println("The number of masks given to customer "
                + alice.getName() + " is " + alice.getMasks() + ".");

        System.out.println("The number of masks given to customer "
                + jennifer.getName() + " is " + jennifer.getMasks() + ".");

        System.out.println("The number of masks given to customer "
                + robert.getName() + " is " + robert.getMasks() + ".");

        //get date alice and robert will receive their masks by
        try {
            localList.getDateCustomer(alice);
            foreignList.getDateCustomer(robert);
        } catch (CustomerNotInListException e) {
            System.err.println("It looks like the given customer is not in this list.");
        }
        System.out.println("Customer " + alice.getName() + " will receive their masks in "
                + alice.getReceiveDate() + " days.");

        System.out.println("Customer " + robert.getName() + " will receive their masks in "
                + robert.getReceiveDate() + " days.");

        //delete alice and robert from their lists
        try {
            localList.deleteCustomer(alice);
            foreignList.deleteCustomer(robert);
        } catch (CustomerNotInListException e) {
            System.err.println("It looks like the given customer is not in this list.");
        } catch (CustomerDidNotReceiveMasksException e) {
            System.err.println("It looks like the given customer has not received their masks yet.");
        }
        System.out.println("Customer " + alice.getName() + " has been removed from the distribution list.");
        System.out.println("Customer " + robert.getName() + " has been removed from the distribution list.");

        //print list
        System.out.println("Printing the local list:");
        localList.printList();
    }
}
