package model;

import exceptions.CustomerNotInListException;

public class LocalList extends DistributionList {
    public static final int LOCAL_DATES = 7;

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
