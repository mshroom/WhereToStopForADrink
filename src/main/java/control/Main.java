package control;

import io.ConsoleIO;
import io.IO;
import java.util.Arrays;
import ui.ConsoleUI;

/**
 *
 * @author mshroom
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception, Throwable {    
        IO io = new ConsoleIO();
        ConsoleUI ui = new ConsoleUI(io);
        ui.start();        
    }
}
