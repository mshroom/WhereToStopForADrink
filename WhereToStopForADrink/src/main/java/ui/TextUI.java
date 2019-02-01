package ui;

import domain.AlgorithmController;
import io.IO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mshroom
 */
public class TextUI {

    private IO io;
    private AlgorithmController algo;

    public TextUI(IO io) {
        this.io = io;   
        this.algo = new AlgorithmController();
    }

    public void start() {
        this.printGreeting();
        this.menu();
        this.printGoodBye();
    }

    private void printGreeting() {
        this.io.printLine("\nWelcome!");
    }

    private void printGoodBye() {
        this.io.printLine("Thank you and see you soon!");
    }

    private void menu() {
        while (true) {
        this.io.printLine("\nThis is the main menu. What would you like to do?\n");
        this.io.printLine("quit = Quit the application");
        this.io.printLine("path = Compare shortest path algorithms");
        this.io.printLine("route = Compare shortest route algorithms");            
            String command = io.readLine("\nNext command: ");
            if (command.equals("quit")) {
                break;
            } else if (command.equals("path")) {
                this.shortestPath();
            } else if (command.equals("route")) {
                this.shortestRoute();
            } else {
                io.printLine("Unknown command");
            }
        }
    }

    private void shortestPath() {
        while (true) {
            this.io.printLine("\nCompare shortest path algorithms\n");
            this.io.printLine("back = Go back to main menu");
            this.io.printLine("small = Compare algorithms with a small test graph");
            this.io.printLine("big = Compare algorithms with a big test graph");            
            String command = io.readLine("\nNext command: ");
            if (command.equals("back")) {
                break;
            } else if (command.equals("small")) {
                this.comparePathAlgorithmsWithSmallTestGraph();
            } else if (command.equals("big")) {
                this.comparePathAlgorithmsWithBigTestGraph();
            } else {
                io.printLine("Unknown command");
            }
        }        
    }

    private void shortestRoute() {
        while (true) {
            this.io.printLine("\nCompare shortest route algorithms\n");
            this.io.printLine("back = Go back to main menu");
            this.io.printLine("small = Compare algorithms with a small test graph");
            this.io.printLine("big = Compare algorithms with a big test graph");            
            String command = io.readLine("\nNext command: ");
            if (command.equals("back")) {
                break;
            } else if (command.equals("small")) {
                this.compareRouteAlgorithmsWithSmallTestGraph();
            } else if (command.equals("big")) {
                io.printLine("Processing...");
                this.compareRouteAlgorithmsWithBigTestGraph();
            } else {
                io.printLine("Unknown command");
            }
        }          
    }

    private void comparePathAlgorithmsWithSmallTestGraph() {
        int node = this.io.readInt("Choose the index of the node (1-4)");
        if (node < 1 || node > 4) {
            this.io.printLine("Not a valid index.");
            return;
        }
        try {
            this.io.printLine(algo.compareShortestPathAlgorithmsWithSmallTestGraph(node));
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }        
    }

    private void comparePathAlgorithmsWithBigTestGraph() {
        int node = this.io.readInt("Choose the index of the node (1-99)");
        if (node < 1 || node > 99) {
            this.io.printLine("Not a valid index.");
            return;
        }
        try {
            this.io.printLine(algo.compareShortestPathAlgorithmsWithBigTestGraph(node));
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }          
    }

    private void compareRouteAlgorithmsWithSmallTestGraph() {
        this.io.printLine(algo.compareShortestRouteAlgorithmsWithSmallTestGraph());
    }

    private void compareRouteAlgorithmsWithBigTestGraph() {
        this.io.printLine(algo.compareShortestRouteAlgorithmsWithBigTestGraph());
    }

}
