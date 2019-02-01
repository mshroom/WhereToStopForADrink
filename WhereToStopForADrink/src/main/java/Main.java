
import io.IO;
import io.TextIO;
import java.util.Scanner;
import ui.TextUI;



/**
 *
 * @author mshroom
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IO io = new TextIO();
        TextUI ui = new TextUI(io);
        ui.start();
    }
}
