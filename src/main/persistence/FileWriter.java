package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileWriter {
    private PrintWriter printWriter;

    //EFFECTS: constructs a new writer that will write data to file
    public FileWriter(File file) throws FileNotFoundException {
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
