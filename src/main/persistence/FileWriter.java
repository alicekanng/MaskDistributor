package persistence;

import java.io.*;

// Writer that writes info into saved files in data folder
// code referenced from TellerApp project
public class FileWriter {
    private PrintWriter printWriter;

    //EFFECTS: constructs a new writer that will write data to file
    public FileWriter(FileOutputStream file) {
        printWriter = new PrintWriter(file);
    }

    //MODIFIES: this
    //EFFECTS: writes saveable to file
    public void write(Saveable data) {
        data.save(printWriter);
    }

    // MODIFIES: this
    // EFFECTS: close print writer
    public void close() {
        printWriter.close();
    }
}
