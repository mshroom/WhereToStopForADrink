package io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author mshroom
 */
public class FileIO implements IO {
    
    private String file;
    private Scanner scanner;
    
    public FileIO(String file) throws Exception {
        this.scanner = new Scanner(new File(file));
        this.file = file;
    }
    
    public void clear() {
        try {
            FileWriter writer = new FileWriter(this.file);
        } catch (IOException ex) {
            System.out.println("An error occurred while writing data");
        }
    }

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

    @Override
    public String readLine(String instruction) {
        if (scanner.hasNextLine()) {
            return scanner.nextLine(); 
        }
        return "";
    }
    
    @Override
    public int readInt(String instruction) {
        return scanner.nextInt();   
    }
    
    
    
}
