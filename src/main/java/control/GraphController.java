package control;

import dataStructures.Queue;
import dataStructures.Place;
import web.DistanceFinder;
import io.FileIO;
import java.util.Arrays;
import java.util.Random;

/**
 * GraphController controls the graph and the places belonging to the graph,
 * with the help of the PlaceController. Class contains methods to import and
 * export graph and place data.
 *
 * @author mshroom
 */
public class GraphController {

    private DistanceFinder finder;
    private PlaceController placeController;
    private FileIO io;
    private int[][] graph;
    private Place[] places;
    private Place[] smallerGraphPlaces;
    private int[] placesInSmallerGraph;
    private boolean smallerGraphIsSet;
    private int[][] smallerGraph;

    /**
     * Create a GraphController with an empty graph.
     */
    public GraphController() {
        this.finder = new DistanceFinder();
        this.placeController = new PlaceController();
        this.io = new FileIO();
    }

    /**
     * Create a GraphController with a custom DistanceFinder, placeController
     * and graph.
     *
     * @param finder DistanceFinder object to be used with this GraphController
     * @param placeController PlaceController object to be used with this
     * GraphControlelr
     * @param graph The graph in the form of a two-dimensional array. If the
     * graph is not empty it should correspond to the place list of the
     * placeController.
     */
    public GraphController(DistanceFinder finder, PlaceController placeController, int[][] graph) {
        this.finder = finder;
        this.placeController = placeController;
        this.places = placeController.getPlaces();
        this.graph = graph;
        this.io = new FileIO();
    }

    /**
     * Imports place data from a text file, finds the coordinates for the places
     * and the distances between the places and uses the data to initialize the
     * graph.
     *
     * @param file The name of the file containing place data. Each row in the
     * file must describe the place in the form "Name;Address" (without the
     * quotation marks).
     * @throws Exception If an error occurs while importing data.
     */
    public void importPlaces(String file) throws Exception {
        placeController.importPlaces(file);
        this.places = placeController.getPlaces();
        this.initializeGraph();
    }

    /**
     * Imports a saved graph and saved Place objects from text files.
     *
     * @param placeFile The name of the file containing saved Places. Each row
     * in the file must contain a toString representation of a Place object.
     * @param graphFile The name of the file containing the saved graph. The
     * file must be consistent with the placeFile.
     * @throws Exception If an error occurs while importing data.
     */
    public void useSaved(String placeFile, String graphFile) throws Exception {
        placeController.useSavedPlaces(placeFile);
        this.places = placeController.getPlaces();
        this.fetchSavedGraph(graphFile);
    }

    /**
     * Saves the current graph into text files. These two text files must be
     * used together next time when importing the saved data.
     *
     * @param placeFile The name of the file for places. Note that the file must
     * already exist.
     * @param graphFile The name of the file for the graph. Note that the file
     * must already exist.
     * @throws Exception If an error occurs while saving data.
     */
    public void save(String placeFile, String graphFile) throws Exception {
        placeController.savePlaces(placeFile);
        this.saveGraph(graphFile);
    }

    /**
     * Initializes the graph by finding the distances between places and
     * creating a two-dimensional array containing the distances.
     *
     * @throws Exception If an error occurs while getting the distances.
     */
    private void initializeGraph() throws Exception {
        System.out.println("Getting distances...");
        int total = places.length;
        this.graph = new int[places.length][places.length];
        for (int i = 0; i < graph.length; i++) {
            System.out.println("Progress: " + i + "/" + total);
            for (int j = 0; j < graph.length; j++) {
                Place firstPlace = places[i];
                Place otherPlace = places[j];
                if (graph[j][i] == 0 && i != j) {
                    int length = this.finder.findDistance(firstPlace.getX(), firstPlace.getY(), otherPlace.getX(), otherPlace.getY());
                    graph[i][j] = length;
                } else {
                    graph[i][j] = graph[j][i];
                }
            }
        }
    }

    /**
     * Saves the current graph to a text file.
     *
     * @param file The name of the file.
     * @throws Exception If an error occurs while saving data.
     */
    private void saveGraph(String file) throws Exception {
        this.io.setFile(file);
        io.clear();
        for (int i = 0; i < this.graph.length; i++) {
            String write = "";
            for (int j = 0; j < this.graph[i].length; j++) {
                write += this.graph[i][j];
                if (j < this.graph[i].length - 1) {
                    write += ";";
                }
            }
            io.printLine(write);
        }
    }

    /**
     * Imports a saved graph from a text file.
     *
     * @param file The name of the file.
     * @throws Exception If an error occurs while importing data.
     */
    private void fetchSavedGraph(String file) throws Exception {
        this.io.setFile(file);
        while (true) {
            String data = io.readLine("");
            if (data.equals("")) {
                break;
            }
            String[] rows = data.split("\\|\\|");
            int[][] savedGraph = new int[rows.length][rows.length];
            for (int i = 0; i < rows.length; i++) {
                String row = rows[i];
                String[] distances = row.split(";");
                for (int j = 0; j < distances.length; j++) {
                    savedGraph[i][j] = Integer.parseInt(distances[j]);
                }
            }
            this.graph = savedGraph;
        }
    }

    public int[][] getGraph() {
        return this.graph;
    }

    /**
     * Returns an array containing the distances from the given node to all
     * other nodes.
     *
     * @param index The index of the wanted node.
     * @return An array where the indexes of the array correspond to the indexes
     * of the nodes and in each index is the distance to the given node.
     */
    public int[] getDistances(int index) {
        return this.graph[index];
    }

    /**
     * Returns the place with the given index.
     *
     * @param index The index of the place
     * @return The Place object with that index, null if there is no such index.
     */
    public Place getPlace(int index) {
        if (index >= 0 && index < this.places.length) {
            return this.places[index];
        }
        return null;
    }

    /**
     * Creates a reduced graph from the current graph, where each edge that is
     * longer than the given maximum distance, will be removed.
     *
     * @param maximumDistance The maximum distance; longer edges will be
     * removed.
     * @return The reduced graph where longer edges have been removed.
     */
    public int[][] getReducedGraph(int maximumDistance) {
        int[][] ret = new int[this.graph.length][this.graph.length];
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret.length; j++) {
                if (this.graph[i][j] <= maximumDistance) {
                    ret[i][j] = this.graph[i][j];
                } else {
                    ret[i][j] = -1;
                }
            }
        }
        return ret;
    }

    /**
     * Creates a smaller graph consisting of given amount of nodes, chosen
     * randomly.
     *
     * @param maximumNumber The maximum amount of nodes.
     * @return The reduced graph in which the rest of the nodes have been
     * removed.
     */
    public int[][] getSmallerGraph(int maximumNumber) {
        if (!smallerGraphRequestIsPossible(maximumNumber)) {
            return this.graph;
        }        
        int[][] smaller = new int[maximumNumber][maximumNumber];
        this.chooseRandomPlaces(maximumNumber);

        for (int i = 0; i < smaller.length; i++) {
            for (int j = 0; j < smaller.length; j++) {
                int actualI = smallerGraphPlaces[i].getIndex();
                int actualJ = smallerGraphPlaces[j].getIndex();
                smaller[i][j] = graph[actualI][actualJ];
                smaller[j][i] = smaller[i][j];
            }
        }
        this.smallerGraph = smaller;
        return smaller;
    }

    /**
     * Checks if a smaller graph with the given number of places is possible and initializes the variables.
     * @param maximumNumber The amount of places in the smaller graph.
     * @return True if the request is possible and the variables were initialized, otherwise false.
     */
    private boolean smallerGraphRequestIsPossible(int maximumNumber) {
        if (maximumNumber >= this.graph.length) {
            this.smallerGraphIsSet = false;
            return false;
        }
        this.smallerGraphIsSet = true;
        this.smallerGraphPlaces = new Place[maximumNumber];
        this.placesInSmallerGraph = new int[places.length];
        return true;
    }

    /**
     * Chooses random places from current graph to the smaller graph with the
     * given amount of places. The place with the index 0 will always be
     * included in the smaller graph.
     *
     * @param howMany The amount of places.
     */
    private void chooseRandomPlaces(int howMany) {
        Random random = new Random();
        int first = 0;
        placesInSmallerGraph[0] = 1;
        int next = 0;
        for (int i = 0; i < howMany - 1; i++) {
            Place firstPlace = places[first];
            while (placesInSmallerGraph[next] == 1) {
                next = random.nextInt(places.length);
            }
            Place nextPlace = places[next];
            placesInSmallerGraph[next] = 1;
            smallerGraphPlaces[i] = firstPlace;
            smallerGraphPlaces[i + 1] = nextPlace;
            first = next;
        }
    }

    /**
     * Returns the amount of nodes in the current graph.
     *
     * @return The amount of nodes.
     */
    public int getSizeOfCurrentGraph() {
        return this.graph.length;
    }

    /**
     * Prints the places in the given path.
     *
     * @param path An array containing the path.
     * @param goal The index of the goal node.
     * @return A String describing the path.
     * @throws Throwable if an error occurs.
     */
    public String printPlaces(int[] path, int goal) throws Throwable {
        Integer[] q = new Integer[10];
        Queue<Integer> stack = new Queue<>(q);
        int previous = path[goal];
        if (previous == -1) {
            return "\nThere is no path";
        }
        while (previous != 0) {
            stack.push(previous);
            previous = path[previous];
        }
        String ret = "\n1. " + places[0].getName() + " (" + places[0].getAddress() + ")\n";
        int number = 2;
        int prev = 0;
        int sum = 0;
        while (!stack.isEmpty()) {
            int now = stack.poll();
            Place currentPlace = places[now];
            ret += "    >> Walk " + graph[prev][now] + " meters\n";
            ret += number + ". " + (currentPlace.getName() + " (" + currentPlace.getAddress() + ")\n");
            number++;
            sum += graph[prev][now];
            prev = now;
        }
        ret += "    >> Walk " + graph[prev][goal] + " meters\n";
        ret += number + ". " + (places[goal].getName() + " (" + places[goal].getAddress() + ")\n");
        sum += graph[prev][goal];
        ret += "\nTotal length: " + sum + " meters";
        return ret;
    }

    /**
     * Prints the places in the given route.
     *
     * @param route An array containing the route.
     * @return A String describing the route.
     */
    public String printPlaces(int[] route) {
        Place[] placesToUse = this.places;
        int[][] graphToUse = this.graph;
        if (this.smallerGraphIsSet) {
            placesToUse = this.smallerGraphPlaces;
            graphToUse = this.smallerGraph;
        }
        String ret = "";
        int sum = 0;
        for (int i = 0; i < route.length - 1; i++) {
            int place = route[i];
            int next = route[i + 1];
            ret += (i + 1) + ". " + placesToUse[place].getName() + " (" + placesToUse[place].getAddress() + ")\n";
            ret += "    >> Walk " + graphToUse[place][next] + " meters\n";
            sum += graphToUse[place][next];
        }
        int last = route.length - 1;
        ret += (route.length) + ". " + placesToUse[route[last]].getName() + " (" + placesToUse[route[last]].getAddress() + ")\n";
        ret += "    >> Walk " + graphToUse[route[last]][0] + " meters\n";
        sum += graphToUse[route[last]][0];
        ret += "Back in " + placesToUse[0].getName() + "\n";
        ret += "\nTotal length: " + sum + " meters";
        return ret;
    }
    
    public String getHomeAddress() {
        return this.placeController.getHomeAddress();
    }
    
    /**
     * Changes the home address.
     * @param newAddress The new home address.
     * @throws Throwable if an error occurs.
     * @return True if the change was successful, otherwise false.
     */
    public boolean changeHomeAddress(String newAddress) throws Throwable {
        if (this.placeController.changeHome(newAddress)) {
            this.places = placeController.getPlaces();
            Place home = places[0];
            for (int i = 1; i < graph.length; i++) {                 
                Place otherPlace = places[i];
                int length = this.finder.findDistance(home.getX(), home.getY(), otherPlace.getX(), otherPlace.getY());
                graph[0][i] = length;
                graph[i][0] = length;                
            }
            return true;
        }        
        return false;
    }
    
    /**
     * Finds the coordinates and distances for the given place and adds it to the current graph.
     * @param name The name of the place.
     * @param address The address of the place.
     * @throws Throwable if an error occurs.
     * @return True if the graph was updated, otherwise false.
     */
    public boolean addPlace(String name, String address) throws Throwable {
        if (this.placeController.addPlace(name, address)) {
            this.places = placeController.getPlaces();
            int[][] newGraph = new int[graph.length + 1][graph.length + 1];
            for (int i = 0; i < graph.length; i ++) {
                for (int j = 0; j < graph.length; j ++) {
                    newGraph[i][j] = graph[i][j];
                }
            }
            Place newPlace = this.places[places.length - 1];
            for (int i = 0; i < newGraph.length - 1; i ++) {
                Place otherPlace = places[i];
                int length = this.finder.findDistance(newPlace.getX(), newPlace.getY(), otherPlace.getX(), otherPlace.getY());
                newGraph[newGraph.length - 1][i] = length;
                newGraph[i][newGraph.length - 1] = length; 
            }
            this.graph = newGraph;
            return true;
        }
        return false;
    }
}
