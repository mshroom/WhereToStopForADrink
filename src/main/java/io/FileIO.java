package io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * FileIO reads and writes textfiles.
 * @author mshroom
 */
public class FileIO implements IO {
    
    private String file;
    private Scanner scanner;
    
    /**
     * Assigns the given file to the FileIO.
     * @param file The file to be read or written into.
     * @throws Exception if the file is not found.
     */
    public void setFile(String file) throws Exception {
        this.scanner = new Scanner(new File(file));
        this.file = file;
    }
    
    /**
     * Clears the current file.
     */
    public void clear() {
        try {
            FileWriter writer = new FileWriter(this.file);
        } catch (IOException ex) {
            System.out.println("An error occurred while writing data");
        }
    }

    /**
     * Writes the given text line to the current file.
     * @param print The text to be written.
     */
    @Override
    public void printLine(String print) {
        try {
            FileWriter writer = new FileWriter(this.file, true);
            writer.append(print + "||");
            writer.close();
        } catch (IOException ex) {
            System.out.println("An error occurred while writing data");
        }
    }

    /**
     * Reads and returns the next line in the file.
     * @param instruction Any String (it will not be used)
     * @return 
     */
    @Override
    public String readLine(String instruction) {
        if (scanner.hasNextLine()) {
            return scanner.nextLine(); 
        }
        return "";
    }
    
    /**
     * Method is not supported.
     * @param instruction Any String.
     * @return 0
     */
    @Override
    public int readInt(String instruction) {   
        return 0;
    }
    
    
    
}
