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
public class ConsoleUI {

    private IO io;
    private AlgorithmController algo;
    private GraphController graphs;
    private GraphStore testGraphs;
    private boolean customGraphIsSet;

    public ConsoleUI(IO io) {
        this.io = io;
        this.algo = new AlgorithmController();
        this.graphs = new GraphController();
        this.testGraphs = new GraphStore();
        this.customGraphIsSet = false;
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
        boolean goOn = true;
        while (goOn) {
            printMenuInstructions();
            String command = io.readLine("\nNext command: ");
            if (command.equals("quit")) {
                goOn = false;
            } else if (command.equals("path")) {
                goOn = this.shortestPath();
            } else if (command.equals("route")) {
                goOn = this.shortestRoute();
            } else if (command.equals("settings")) {
                goOn = this.graphSettings();            
            } else {
                io.printLine("Unknown command");
            }
        }
    }

    private void printMenuInstructions() {
        this.io.printLine("\nThis is the main menu. What would you like to do?\n");
        this.io.printLine("path = Compare shortest path algorithms");
        this.io.printLine("route = Compare shortest route algorithms");
        this.io.printLine("settings = Settings for custom graph");
        this.io.printLine("quit = Quit the application");
    }

    private boolean shortestPath() {
        printPathInstructions();
        while (true) {
            String command = io.readLine("\nNext command: ");
            if (command.equals("back")) {
                return true;
            } else if (command.equals("quit")) {
                return false;
            } else if (command.equals("small")) {
                this.comparePathAlgorithmsWithSmallTestGraph();
            } else if (command.equals("big")) {
                this.comparePathAlgorithmsWithBigTestGraph();
            } else if (command.equals("custom")) {
                this.comparePathAlgorithmsWithCustomGraph();
            } else {
                io.printLine("Unknown command");
                printPathInstructions();
            }
        }
    }

    private void printPathInstructions() {
        this.io.printLine("\nCompare shortest path algorithms\n");
        this.io.printLine("small = Compare algorithms with a small test graph");
        this.io.printLine("big = Compare algorithms with a big test graph");
        this.io.printLine("custom = Compare algorithms with an imported graph");
        this.io.printLine("back = Go back to main menu");
        this.io.printLine("quit = Quit the application");
    }

    private boolean shortestRoute() {
        printRouteInstructions();
        while (true) {
            String command = io.readLine("\nNext command: ");
            if (command.equals("back")) {
                return true;
            } else if (command.equals("quit")) {
                return false;
            } else if (command.equals("small")) {
                this.compareRouteAlgorithmsWithSmallTestGraph();
            } else if (command.equals("big")) {
                this.compareRouteAlgorithmsWithBigTestGraph();
            } else if (command.equals("custom")) {
                this.compareRouteAlgorithmsWithCustomGraph();
            } else {
                io.printLine("Unknown command");
                printRouteInstructions();
            }
        }
    }

    private void printRouteInstructions() {
        this.io.printLine("\nCompare shortest route algorithms\n");
        this.io.printLine("small = Compare algorithms with a small test graph");
        this.io.printLine("big = Compare algorithms with a big test graph");
        this.io.printLine("custom = Compare algorithms with an imported graph");
        this.io.printLine("back = Go back to main menu");
        this.io.printLine("quit = Quit the application");
    }

    private boolean graphSettings() {
        printSettingsInstructions();
        while (true) {
            String command = io.readLine("\nNext command: ");
            if (command.equals("back")) {
                return true;
            } else if (command.equals("quit")) {
                return false;
            } else if (command.equals("new")) {
                this.newFile();
            } else if (command.equals("memory")) {
                this.readMemory();
            } else if (command.equals("save")) {
                this.saveData();
            } else if (command.equals("home")) {
                this.setHome();
            } else if (command.equals("add")) {
                this.addPlace();    
            } else {
                io.printLine("Unknown command");
                printSettingsInstructions();
            }
        }
    }

    private void printSettingsInstructions() {
        this.io.printLine("\nSettings for a custom graph\n");
        this.io.printLine("new = Import places from a text file");
        this.io.printLine("memory = Use saved data");
        this.io.printLine("save = Save current places");
        this.io.printLine("home = Change home address for current graph");
        this.io.printLine("add = Add a place to the current graph");
        this.io.printLine("back = Go back to main menu");
        this.io.printLine("quit = Quit the application");
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
        io.printLine("Processing...");
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
            this.customGraphIsSet = true;
        } catch (Exception ex) {
            this.io.printLine("Failed to import data.");
        }
    }

    private void readMemory() {
        try {
            graphs.useSaved("data/saved.txt", "data/savedGraph.txt");
            this.io.printLine("Data was imported successfully.");
            this.customGraphIsSet = true;
        } catch (Exception ex) {
            this.io.printLine("Failed to import data.");
        }
    }

    private void compareRouteAlgorithmsWithCustomGraph() {
        if (!this.customGraphIsSet) {
            this.io.printLine("You have not imported any graph.");
            return;
        }
        int max = graphs.getSizeOfCurrentGraph();
        String warning = "";
        if (max > 15) {
            warning = "\nNote that TspExact will run VERY slow with more than 15 places.";
        }
        int howMany = this.io.readInt("How many places do you want to use? "
                + "(maximum for this graph is " + max + ") " + warning);
        if (howMany < 2 || howMany > max) {
            this.io.printLine("Not a valid number.");
            return;
        }
        if (howMany > 10) {
            this.io.printLine("Processing...");
        }
        try {
            this.io.printLine(algo.compareShortestRouteAlgorithms(graphs.getSmallerGraph(howMany)));
            this.printRoute();
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }
    }

    private void comparePathAlgorithmsWithCustomGraph() {
        if (!this.customGraphIsSet) {
            this.io.printLine("You have not imported any graph.");
            return;
        }
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
            if (algo.pathWasFound(node)) {
                this.printPath(node);
            }
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }
    }

    private void saveData() {
        if (!this.customGraphIsSet) {
            this.io.printLine("You have not imported any graph.");
            return;
        }
        try {
            this.graphs.save("data/saved.txt", "data/savedGraph.txt");
            this.io.printLine("Data was saved successfully.");
        } catch (Exception ex) {
            this.io.printLine("Could not save data.");
        }
    }
    
    private void setHome() {
        if (!this.customGraphIsSet) {
            this.io.printLine("You have not imported any graph.");
            return;
        }
        io.printLine("Current home address is " + graphs.getHomeAddress());
        String newAddress = io.readLine("New address: (leave empty to skip)");
        if (newAddress.equals("")) {
            return;
        }
        try {
            graphs.changeHomeAddress(newAddress);
        } catch (Throwable ex) {
            io.printLine("There was an error somewhere.");
        }
    }

    private void printPath(int goal) throws Throwable {
        this.io.printLine("\nDo you wish to print the path?");
        this.io.printLine("\nd = Print Dijkstra path");
        this.io.printLine("a = Print AStar path");
        this.io.printLine("b = Print Bfs path");
        this.io.printLine("Press enter or any other key to go back.");
        while (true) {
            String command = this.io.readLine("\nPrint path: ");
            if (command.equals("d")) {
                int[] path = algo.getCurrentDijkstraPath();
                this.io.printLine(graphs.printPlaces(path, goal));
            } else if (command.equals("a")) {
                int[] path = algo.getCurrentAStarPath();
                this.io.printLine(graphs.printPlaces(path, goal));
            } else if (command.equals("b")) {
                int[] path = algo.getCurrentBfsPath();
                this.io.printLine(graphs.printPlaces(path, goal));
            } else {
                break;
            }
        }
    }

    private void printRoute() {
        this.io.printLine("\nDo you wish to print the route?");
        this.io.printLine("\ne = Print TspExact path");
        this.io.printLine("n = Print TspNearestNeighbour path");
        this.io.printLine("Press enter or any other key to go back.");
        while (true) {
            String command = this.io.readLine("\nPrint path: ");
            if (command.equals("e")) {
                int[] route = algo.getCurrentTspExactRoute();
                this.io.printLine(graphs.printPlaces(route));
            } else if (command.equals("n")) {
                int[] path = algo.getCurrentTspNearestNeighbourRoute();
                this.io.printLine(graphs.printPlaces(path));
            } else {
                break;
            }
        }
    }
    
    private void addPlace() {
        if (!this.customGraphIsSet) {
            this.io.printLine("You have not imported any graph.");
            return;
        }
        String name = this.io.readLine("Enter the name of the place: (leave empty to cancel)");
        if (name.equals("")) return;
        String address = this.io.readLine("Enter the address: (leave empty to cancel)");
        if (address.equals("")) return;
        try {
            this.graphs.addPlace(name, address);
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }
    }
}
