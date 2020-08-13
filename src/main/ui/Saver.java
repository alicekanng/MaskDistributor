package ui;

import model.Customer;
import persistence.FileWriter;

import java.io.*;

public class Saver {
    private static final String LOCAL_LIST_FILE = "./data/localList.txt";
    private static final String FOREIGN_LIST_FILE = "./data/foreignList.txt";

    public Saver() {
    }

    //EFFECTS: save edited local distribution list to LOCAL_LIST_FILE
    public void saveLocalList(Customer customer) {
        try {
            FileWriter fileWriter = new FileWriter(new FileOutputStream(new File(LOCAL_LIST_FILE), true));
            fileWriter.write(customer);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file to save to!");
        }
    }

    //EFFECTS: save edited foreign distribution list to FOREIGN_LIST_FILE
    public void saveForeignList(Customer customer) {
        try {
            FileWriter fileWriter = new FileWriter(new FileOutputStream(new File(FOREIGN_LIST_FILE), true));
            fileWriter.write(customer);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file to save to!");
        }
    }
}
