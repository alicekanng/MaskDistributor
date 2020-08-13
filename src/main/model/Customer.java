package model;

import persistence.FileReader;
import persistence.Saveable;

import java.io.PrintWriter;
import java.util.Objects;

// constructs customer object with their name, address, age, conditions, receive date, position and number of masks
public class Customer implements Saveable {
    private String name;
    private String address;
    private int age;
    private String conditions;
    private int receiveDate;
    private int position;
    private int masks;

    //EFFECTS: construct new customer with given name, address, age, gender, conditions
    public Customer(String name, String address, int age, String conditions) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.conditions = conditions;
        this.masks = 0;
        this.receiveDate = 0;
        this.position = 0;
    }

    //getters:
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getConditions() {
        return conditions;
    }

    public int getReceiveDate() {
        return receiveDate;
    }

    public int getPosition() {
        return position;
    }

    public int getMasks() {
        return masks;
    }

    //setters:
    public void setReceiveDate(int date) {
        this.receiveDate = date;
    }

    public void setPosition(int pos) {
        this.position = pos;
    }

    public void setMasks(int masks) {
        this.masks = masks;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(name);
        printWriter.print(FileReader.NAME_DELIM);
        printWriter.print(address);
        printWriter.print(FileReader.INFO_DELIM);
        printWriter.print(age);
        printWriter.print(FileReader.INFO_DELIM);
        printWriter.print(conditions + "\n");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        boolean ageEquals = age == customer.age;
        boolean nameEquals = Objects.equals(name, customer.name);
        boolean addressEquals = Objects.equals(address, customer.address);
        boolean conditionEquals = Objects.equals(conditions, customer.conditions);
        return ageEquals && nameEquals && addressEquals && conditionEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, age, conditions);
    }
}
