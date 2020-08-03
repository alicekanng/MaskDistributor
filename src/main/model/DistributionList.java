package model;

import exceptions.CustomerDidNotReceiveMasksException;
import exceptions.CustomerNotInListException;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class DistributionList {
    public static final int MASK_DISTRIBUTED = 15;

    public List<Customer> queue;

    //EFFECTS: constructs new DistributionList
    public DistributionList() {
        queue = new LinkedList<>();
    }

    //MODIFIES: this
    //EFFECTS: add given c into this
    public void addCustomer(Customer c) {
        queue.add(c);
    }

    //REQUIRES: c is in this
    //EFFECTS: return the number of masks c will receive
    public int getNumberOfMasksCustomer(Customer c) throws CustomerNotInListException {
        if (!queue.contains(c)) {
            throw new CustomerNotInListException();
        }

        for (Customer customer : queue) {
            if (customer.getAge() < 14 || customer.getAge() > 64) {
                customer.setMasks(MASK_DISTRIBUTED + 10);
            } else {
                customer.setMasks(MASK_DISTRIBUTED);
            }

            int num = customer.getMasks();
            if (!customer.getConditions().contentEquals("None")) {
                customer.setMasks(num + 5);
            }
        }
        return c.getMasks();
    }

    //REQUIRES: c is in this
    //EFFECTS: return the number of days c will receive their masks in
    protected abstract int getDateCustomer(Customer c) throws CustomerNotInListException;

    //REQUIRES: c is in this
    //EFFECTS: return the given c's place in the distribution queue
    public int getPositionCustomer(Customer c) throws CustomerNotInListException {
        int position = 1;
        for (Customer customer : queue) {
            if (customer != c) {
                position++;
            } else {
                c.setPosition(position);
                return c.getPosition();
            }
        }
        throw new CustomerNotInListException();
    }

    //REQUIRES: c is in this
    //MODIFIES: this
    //EFFECTS: return true if customer who has already received masks was deleted from this, false otherwise
    public boolean deleteCustomer(Customer c) throws CustomerNotInListException, CustomerDidNotReceiveMasksException {
        if (!queue.contains(c)) {
            throw new CustomerNotInListException();
        }

        if (c.getMasks() == 0) {
            throw new CustomerDidNotReceiveMasksException();
        }

        queue.remove(c);
        return true;
    }

    //EFFECTS: return the distribution list
    public List<Customer> printList() {
        return queue;
    }

}
