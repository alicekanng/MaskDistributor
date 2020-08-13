package model;

import exceptions.CustomerNotInListException;

// constructs foreign distribution list
public class ForeignList extends DistributionList {
    public static final int FOREIGN_DATES = 30;

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