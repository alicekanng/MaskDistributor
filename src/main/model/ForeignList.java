package model;

import exceptions.CustomerNotInListException;

// constructs foreign distribution list
public class ForeignList extends DistributionList {
    public static final int FOREIGN_DATES = 30;

    //EFFECTS: return the number of days c will receive their masks in
    // throw CustomerNotInListException if c is not already in this
    @Override
    public int getDateCustomer(Customer c) throws CustomerNotInListException {
        if (!queue.contains(c)) {
            throw new CustomerNotInListException();
        } else {
            c.setReceiveDate(FOREIGN_DATES);
        }
        return c.getReceiveDate();
    }
}