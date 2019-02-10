package ui;

import control.AlgorithmController;
import control.GraphController;
import control.GraphStore;
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
    private GraphController graphs;
    private GraphStore testGraphs;

    public TextUI(IO io) {
        this.io = io;
        this.algo = new AlgorithmController();
        this.graphs = new GraphController();
        this.testGraphs = new GraphStore();
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
            this.io.printLine("import = Import places");
            String command = io.readLine("\nNext command: ");
            if (command.equals("quit")) {
                break;
            } else if (command.equals("path")) {
                this.shortestPath();
            } else if (command.equals("route")) {
                this.shortestRoute();
            } else if (command.equals("import")) {
                this.importData();
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
            this.io.printLine("custom = Compare algorithms with an imported graph");
            String command = io.readLine("\nNext command: ");
            if (command.equals("back")) {
                break;
            } else if (command.equals("small")) {
                this.comparePathAlgorithmsWithSmallTestGraph();
            } else if (command.equals("big")) {
                this.comparePathAlgorithmsWithBigTestGraph();
            } else if (command.equals("custom")) {
                this.comparePathAlgorithmsWithCustomGraph();
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
            this.io.printLine("custom = Compare algorithms with an imported graph");
            String command = io.readLine("\nNext command: ");
            if (command.equals("back")) {
                break;
            } else if (command.equals("small")) {
                this.compareRouteAlgorithmsWithSmallTestGraph();
            } else if (command.equals("big")) {
                io.printLine("Processing...");
                this.compareRouteAlgorithmsWithBigTestGraph();
            } else if (command.equals("custom")) {
                this.compareRouteAlgorithmsWithCustomGraph();
            } else {
                io.printLine("Unknown command");
            }
        }
    }

    private void importData() {
        while (true) {
            this.io.printLine("\nImport places to the application\n");
            this.io.printLine("back = Go back to main menu");
            this.io.printLine("new = Import places from a text file");
            this.io.printLine("memory = Use saved data");
            this.io.printLine("save = Save current places");
            String command = io.readLine("\nNext command: ");
            if (command.equals("back")) {
                break;
            } else if (command.equals("new")) {
                this.newFile();
            } else if (command.equals("memory")) {
                this.readMemory();
            } else if (command.equals("save")) {
                this.saveData();
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
            this.io.printLine(algo.compareShortestPathAlgorithms(testGraphs.createSmallGraphForAStar(), node, testGraphs.createDistancesForSmallGraphForAStar(node)));
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
            this.io.printLine(algo.compareShortestPathAlgorithms(testGraphs.createBigGraphForPathfinding(), node, testGraphs.createDistancesForBigGraphForPathFinding()));
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }
    }

    private void compareRouteAlgorithmsWithSmallTestGraph() {
        try {
            this.io.printLine(algo.compareShortestRouteAlgorithms(testGraphs.createSmallCompleteGraph()));
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }
    }

    private void compareRouteAlgorithmsWithBigTestGraph() {
        try {
            this.io.printLine(algo.compareShortestRouteAlgorithms(testGraphs.createBigCompleteGraph()));
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }
    }

    private void newFile() {
        String file = this.io.readLine("Give the name of the file");
        try {
            graphs.importPlaces(file);
            this.io.printLine("Data was imported successfully.");
        } catch (Exception ex) {
            this.io.printLine("Failed to import data.");
        }
    }

    private void readMemory() {
        try {
            graphs.useSaved("data/saved.txt", "data/savedGraph.txt");
            this.io.printLine("Data was imported successfully.");
        } catch (Exception ex) {
            this.io.printLine("Failed to import data.");
        }
    }

    private void compareRouteAlgorithmsWithCustomGraph() {
        int max = graphs.getSizeOfCurrentGraph();
        int howMany = this.io.readInt("How many places do you want to use? "
                + "(maximum for this graph is " + max + ")");
        if (howMany < 2 || howMany > max) {
            this.io.printLine("Not a valid number.");
            return;
        }
        try {
            this.io.printLine(algo.compareShortestRouteAlgorithms(graphs.getSmallerGraph(howMany)));
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }
    }

    private void comparePathAlgorithmsWithCustomGraph() {
        int maxIndex = graphs.getSizeOfCurrentGraph() - 1;
        int node = this.io.readInt("Enter the index of your destination "
                + "(maximum for this graph is " + maxIndex + ")");
        if (node < 1 || node > maxIndex) {
            this.io.printLine("Not a valid number.");
            return;
        }
        int maxDistance = this.io.readInt("Enter the maximum walking distance between two points (meters)");
        try {
            this.io.printLine(algo.compareShortestPathAlgorithms(graphs.getReducedGraph(maxDistance), node, graphs.getDistances(node)));
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }
    }

    private void saveData() {
        try {
            this.graphs.save("data/saved.txt", "data/savedGraph.txt");
            this.io.printLine("Data was saved successfully.");
        } catch (Exception ex) {
            this.io.printLine("Could not save data.");
        }
    }
}
