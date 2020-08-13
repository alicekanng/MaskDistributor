package persistence;

import java.io.PrintWriter;

// interface that allows its subclasses to be saved by the FileWriter class
// code referenced from TellerApp project
public interface Saveable {

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
