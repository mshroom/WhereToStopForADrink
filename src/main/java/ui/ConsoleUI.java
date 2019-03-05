package ui;

import control.AlgorithmController;
import control.GraphController;
import control.GraphStore;
import io.IO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A console user interface.
 *
 * @author mshroom
 */
public class ConsoleUI {

    private IO io;
    private AlgorithmController algo;
    private GraphController graphs;
    private GraphStore testGraphs;
    private boolean customGraphIsSet;

    /**
     * Creates the UI with given IO object.
     *
     * @param io IO object for user input/output.
     */
    public ConsoleUI(IO io) {
        this.io = io;
        this.algo = new AlgorithmController();
        this.graphs = new GraphController();
        this.testGraphs = new GraphStore();
        this.customGraphIsSet = false;
    }

    public ConsoleUI(IO io, AlgorithmController algo, GraphController graphs, GraphStore testGraphs) {
        this.io = io;
        this.algo = algo;
        this.graphs = graphs;
        this.testGraphs = testGraphs;
        this.customGraphIsSet = false;
    }

    /**
     * Starts the UI.
     */
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

    /**
     * Main menu.
     */
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

    /**
     * Compare shortest path algorithms menu.
     *
     * @return false if user wants to quit the application, otherwise true.
     */
    private boolean shortestPath() {
        printPathInstructions();
        while (true) {
            String command = io.readLine("\nNext command: ");
            if (command.equals("back")) {
                return true;
            } else if (command.equals("quit")) {
                return false;
            } else if (command.equals("simple")) {
                this.comparePathAlgorithmsWithSimpleTestGraphs();
            } else if (command.equals("random")) {
                this.comparePathAlgorithmsWithRandomTestGraph();
            } else if (command.equals("custom")) {
                this.comparePathAlgorithmsWithCustomGraph();
            } else if (command.equals("find")) {
                this.findPlace();
            } else {
                io.printLine("Unknown command");
                printPathInstructions();
            }
        }
    }

    private void printPathInstructions() {
        this.io.printLine("\nCompare shortest path algorithms\n");
        this.io.printLine("simple = Compare algorithms with simple test graphs");
        this.io.printLine("random = Compare algorithms with a random test graph");
        this.io.printLine("custom = Compare algorithms with an imported graph");
        this.io.printLine("find = Find a path to a specific place in custom graph");
        this.io.printLine("back = Go back to main menu");
        this.io.printLine("quit = Quit the application");
    }

    /**
     * Compare shortest route algorithms menu.
     *
     * @return false if user wants to quit the application, otherwise true.
     */
    private boolean shortestRoute() {
        printRouteInstructions();
        while (true) {
            String command = io.readLine("\nNext command: ");
            if (command.equals("back")) {
                return true;
            } else if (command.equals("quit")) {
                return false;
            } else if (command.equals("random")) {
                this.compareRouteAlgorithmsWithRandomTestGraph();
            } else if (command.equals("simple")) {
                this.compareRouteAlgorithmsWithSimpleTestGraph();
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
        this.io.printLine("simple = Compare algorithms with simple test graphs");
        this.io.printLine("random = Compare algorithms with a randomized test graph");
        this.io.printLine("custom = Compare algorithms with an imported graph");
        this.io.printLine("back = Go back to main menu");
        this.io.printLine("quit = Quit the application");
    }

    /**
     * Settings menu.
     *
     * @return false if user wants to quit the application, otherwise true.
     */
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

    private void comparePathAlgorithmsWithSimpleTestGraphs() {
        int[][] graphToTest = new int[1][1];
        int maxIndex = 0;
        while (true) {
            String which = this.io.readLine("small = Use a small test graph\n"
                    + "big = Use a big test graph");
            if (which.equals("small")) {
                graphToTest = testGraphs.createSmallGraphForPathfinding2();
                maxIndex = 4;                
            } else if (which.equals("big")) {
                graphToTest = testGraphs.createBigSimpleGraphForPathfinding();
                maxIndex = 99;
            } else {
                this.io.printLine("Invalid command.\n");
                continue;
            }
            int node = this.io.readInt("Choose the index of the node (1-" + maxIndex + ")");
            if (node < 1 || node > maxIndex) {
                this.io.printLine("Not a valid index.");
                return;
            }
            try {                
                this.io.printLine(algo.compareShortestPathAlgorithms(graphToTest, node, testGraphs.createFakeDistancesForAStarGraph(graphToTest, node)));
            } catch (Throwable ex) {
                this.io.printLine("There was an error somewhere.");
            }
            break;
        }

    }

    private void comparePathAlgorithmsWithRandomTestGraph() {
        int size = this.io.readInt("Choose the size of the graph");
        if (size < 1) {
            this.io.printLine("Not a valid index.");
            return;
        }
        try {
            int[][] bigGraph = testGraphs.createRandomGraphForPathfinding(size);
            this.io.printLine(algo.compareShortestPathAlgorithms(bigGraph, size - 1, new int[size]));
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }
    }

    private void compareRouteAlgorithmsWithRandomTestGraph() {
        int size = this.io.readInt("Give the graph size.\nWith more than 17 nodes only the approximation algorithm will be used.");
        if (size < 2) {
            this.io.printLine("Invalid size.");
            return;
        }
        boolean all = false;
        if (size <= 17) {
            all = true;
        }
        try {
            this.io.printLine(algo.compareShortestRouteAlgorithms(testGraphs.createRandomCompleteGraph(size), all));
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }
    }

    private void compareRouteAlgorithmsWithSimpleTestGraph() {
        boolean all = false;
        while (true) {
            String command = this.io.readLine("Choose a graph:\n"
                    + "small = a small simple graph"
                    + "\nbig = a big simple graph");
            if (command.equals("small")) {
                try {
                    this.io.printLine(algo.compareShortestRouteAlgorithms(testGraphs.createSmallCompleteGraph(), true));
                } catch (Throwable ex) {
                    this.io.printLine("There was an error somewhere.");
                }
                break;
            } else if (command.equals("big")) {
                try {
                    this.io.printLine(algo.compareShortestRouteAlgorithms(testGraphs.createBigCompleteGraph(), true));

                } catch (Throwable ex) {
                    this.io.printLine("There was an error somewhere.");
                }
                break;
            } else {
                this.io.printLine("Not a valid command.");
            }
        }
    }

    /**
     * Imports addresses from a text file.
     */
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

    /**
     * Imports places that have been saved.
     */
    private void readMemory() {
        try {
            graphs.useSaved("data/userData/savedPlaces.txt", "data/userData/savedGraph.txt");
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
            warning = "\nWith more than 15 places only the approximation algorithm will be used.";
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
            if (howMany > 15) {
                this.io.printLine(algo.compareShortestRouteAlgorithms(graphs.getSmallerGraph(howMany), false));
                this.printRoute(false);
            } else {
                this.io.printLine(algo.compareShortestRouteAlgorithms(graphs.getSmallerGraph(howMany), true));
                this.printRoute(true);
            }

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
        this.customPathSearch(node);
    }

    private void customPathSearch(int node) {
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

    /**
     * Saves current graph if there is one.
     */
    private void saveData() {
        if (!this.customGraphIsSet) {
            this.io.printLine("You have not imported any graph.");
            return;
        }
        try {
            this.graphs.save("data/userData/savedPlaces.txt", "data/userData/savedGraph.txt");
            this.io.printLine("Data was saved successfully.");
        } catch (Exception ex) {
            this.io.printLine("Could not save data.");
        }
    }

    /**
     * Defines a new home address.
     */
    private void setHome() {
        if (!this.customGraphIsSet) {
            this.io.printLine("You have not imported any graph.");
            return;
        }
        String confirm = io.readLine("Current home address is " + graphs.getHomeAddress() + ".\n"
                + "Enter yes to change the address, leave empty to cancel:");
        if (!confirm.equals("yes")) {
            return;
        }
        String newAddress = io.readLine("New address: (leave empty to skip)");
        if (newAddress.equals("")) {
            return;
        }
        io.printLine("Getting data for new home address...");
        try {
            graphs.changeHomeAddress(newAddress);
            io.printLine("New home address was changed successfully.");
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

    private void printRoute(boolean all) {
        this.io.printLine("\nDo you wish to print the route?");
        if (all) {
            this.io.printLine("\ne = Print TspExact path");
        }
        this.io.printLine("n = Print TspNearestNeighbour path");
        this.io.printLine("Press enter or any other key to go back.");
        while (true) {
            String command = this.io.readLine("\nPrint path: ");
            if (command.equals("e") && all) {
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

    /**
     * Adds a new place to current graph if there is one.
     */
    private void addPlace() {
        if (!this.customGraphIsSet) {
            this.io.printLine("You have not imported any graph.");
            return;
        }
        String name = this.io.readLine("Enter the name of the place: (leave empty to cancel)");
        if (name.equals("")) {
            return;
        }
        String address = this.io.readLine("Enter the address: (leave empty to cancel)");
        if (address.equals("")) {
            return;
        }
        this.io.printLine("Getting data for new address...");
        try {
            this.graphs.addPlace(name, address);
            this.io.printLine("New place was added successfully.");
        } catch (Throwable ex) {
            this.io.printLine("There was an error somewhere.");
        }
    }

    private void findPlace() {
        if (!this.customGraphIsSet) {
            this.io.printLine("You have not imported any graph.");
            return;
        }
        String search = this.io.readLine("Search by name or the address: (leave empty to cancel)");
        if (search.equals("")) {
            return;
        }
        String found = graphs.findPlaces(search);
        if (found.equals("")) {
            this.io.printLine("Place was not found.");
            return;
        }
        this.io.printLine("Search results:\n");
        this.io.printLine(found);
        int index = this.io.readInt("Choose an index: ");
        this.customPathSearch(index);
    }
}
