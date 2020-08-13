package model;

import exceptions.CustomerNotInListException;

// constructs local distribution list
public class LocalList extends DistributionList {
    public static final int LOCAL_DATES = 7;

    //EFFECTS: return the number of days c will receive their masks in
    // throw CustomerNotInListException if c is not already in this
    @Override
    public int getDateCustomer(Customer c) throws CustomerNotInListException {
        if (!queue.contains(c)) {
            throw new CustomerNotInListException();
        } else {
            c.setReceiveDate(LOCAL_DATES);
        }
        return c.getReceiveDate();
    }
}
