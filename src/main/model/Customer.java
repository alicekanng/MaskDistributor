package model;

public class Customer {
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

}
