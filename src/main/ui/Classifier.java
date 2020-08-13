package ui;

import exceptions.CustomerNotInListException;
import model.Customer;
import model.ForeignList;
import model.LocalList;

public class Classifier {
    private Handler handler;
    private Saver saver;
    private LocalList localList;
    private ForeignList foreignList;

    public Classifier(Handler handler) {
        this.handler = handler;
        saver = new Saver();
        localList = handler.getLocalList();
        foreignList = handler.getForeignList();
    }

    //EFFECTS: add the customer to a list depending on whether they are local or foreign
    public void addCustomerBasedOnAddress(Customer customer) {
        if (customer.getAddress().contains("BC")) {
            localList.addCustomer(customer);
            saver.saveLocalList(customer);
        } else {
            foreignList.addCustomer(customer);
            saver.saveForeignList(customer);
        }
    }

    //EFFECTS: gets number of masks a customer will receive from their appropriate list
    public void getMasksBasedOnAddress(Customer customer) throws CustomerNotInListException {
        if (customer.getAddress().contains("BC")) {
            localList.getNumberOfMasksCustomer(customer);
        } else {
            foreignList.getNumberOfMasksCustomer(customer);
        }
    }

    //EFFECTS: gets date a customer will receive their masks from their appropriate list
    public void getDateBasedOnAddress(Customer customer) throws CustomerNotInListException {
        if (customer.getAddress().contains("BC")) {
            localList.getDateCustomer(customer);
        } else {
            foreignList.getDateCustomer(customer);
        }
    }

    //EFFECTS: gets position of customer in the queue of their appropriate list
    public void getPositionBasedOnAddress(Customer customer) throws CustomerNotInListException {
        if (customer.getAddress().contains("BC")) {
            localList.getPositionCustomer(customer);
        } else {
            foreignList.getPositionCustomer(customer);
        }
    }

    //EFFECTS: deletes customer from their appropriate list
    public void deleteCustomerBasedOnAddress(Customer customer) throws CustomerNotInListException {
        if (customer.getAddress().contains("BC")) {
            localList.deleteCustomer(customer);
            saver.saveLocalList(customer);
        } else {
            foreignList.deleteCustomer(customer);
            saver.saveForeignList(customer);
        }
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
            handler.handlePrintList();
        }
    }
}
