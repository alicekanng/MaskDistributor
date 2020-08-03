package persistence;

import java.io.PrintWriter;

//code referenced from TellerApp project
public interface Saveable {

    // MODIFIES: printWriter
    // EFFECTS: writes the saveable to printWriter
    void save(PrintWriter printWriter);
}
