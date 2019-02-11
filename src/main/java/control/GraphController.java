package control;

import dataStructures.Place;
import web.DistanceFinder;
import io.FileIO;

/**
 * GraphController controls the graph and the places belonging to the graph, 
 * with the help of the PlaceController. Class contains methods to import and export
 * graph and place data.
 * @author mshroom
 */
public class GraphController {;
    private Place[] places;
    private DistanceFinder finder;
    private int[][] graph;
    private PlaceController placeController;
    private FileIO io;
    
    /**
     * Create a GraphController with an empty graph.
     */
    public GraphController() {
        this.finder = new DistanceFinder();
        this.placeController = new PlaceController();
        this.io = new FileIO();
    }
    
    /**
     * Create a GraphController with a custom DistanceFinder, placeController and graph.
     * @param finder DistanceFinder object to be used with this GraphController
     * @param placeController PlaceController object to be used with this GraphControlelr
     * @param graph The graph in the form of a two-dimensional array. If the graph is not
     * empty it should correspond to the place list of the placeController.
     */
    public GraphController(DistanceFinder finder, PlaceController placeController, int[][] graph) {
        this.finder = finder;
        this.placeController = placeController;
        this.graph = graph;
        this.io = new FileIO();
    }
    
    /**
     * Imports place data from a text file, finds the coordinates for the places and 
     * the distances between the places and uses the data to initialize the graph.
     * @param file The name of the file containing place data. 
     * Each row in the file must describe the place in the form "Name;Address" (without
     * the quotation marks).
     * @throws Exception If an error occurs while importing data.
     */
    public void importPlaces(String file) throws Exception {
        placeController.importPlaces(file);
        this.places = placeController.getPlaces();
        this.initializeGraph();
    }
    
    /**
     * Imports a saved graph and saved Place objects from text files.
     * @param placeFile The name of the file containing saved Places. Each row in the file must
     * contain a toString representation of a Place object.
     * @param graphFile The name of the file containing the saved graph. The file must be
     * consistent with the placeFile.
     * @throws Exception If an error occurs while importing data.
     */
    public void useSaved(String placeFile, String graphFile) throws Exception {
        placeController.useSavedPlaces(placeFile);
        this.places = placeController.getPlaces();
        this.fetchSavedGraph(graphFile);
    }
    
    /**
     * Saves the current graph into text files. These two text files must be used together next time 
     * when importing the saved data.
     * @param placeFile The name of the file for places. Note that the file must already exist.
     * @param graphFile The name of the file for the graph. Note that the file must already exist.
     * @throws Exception If an error occurs while saving data.
     */
    public void save(String placeFile, String graphFile) throws Exception {
        placeController.savePlaces(placeFile);
        this.saveGraph(graphFile);
    }
    
    /**
     * Initializes the graph by finding the distances between places and creating
     * a two-dimensional array containing the distances.
     * @throws Exception If an error occurs while getting the distances.
     */
    private void initializeGraph() throws Exception {
        System.out.println("Getting distances...");
        int total = places.length;
        this.graph = new int[places.length][places.length];
        for (int i = 0; i < graph.length; i ++) {
            System.out.println("Progress: " + i + "/" + total);
            for (int j = 0; j < graph.length; j ++) {
                Place a = places[i];
                Place b = places[j];
                if (graph[j][i] == 0 && i != j) {
                    int length = this.finder.findDistance(a.getX(), a.getY(), b.getX(), b.getY());
                    graph[i][j] = length;                    
                } else {
                    graph[i][j] = graph[j][i];
                }
            }
        }
    }
    
    /**
     * Saves the current graph to a text file.
     * @param file The name of the file.
     * @throws Exception If an error occurs while saving data.
     */
    private void saveGraph(String file) throws Exception {
        this.io.setFile(file);
        io.clear();
        for (int i = 0; i < this.graph.length; i ++) {
            String write = "";
            for (int j = 0; j < this.graph[i].length; j ++) {
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
     * @param file The name of the file.
     * @throws Exception If an error occurs while importing data.
     */
    private void fetchSavedGraph(String file) throws Exception {        
        this.io.setFile(file);
        while (true) {            
            String p = io.readLine("");
            if (p.equals("")) {
                break;
            }
            String[] rows = p.split("\\|\\|");  
            int[][] savedGraph = new int[rows.length][rows.length];
            for (int i = 0; i < rows.length; i ++) {
                String row = rows[i];
                String[] distances = row.split(";");
                for (int j = 0; j < distances.length; j ++) {
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
     * Returns an array containing the distances from the given node to all other nodes.
     * @param index The index of the wanted node.
     * @return An array where the indexes of the array correspond to the indexes of the nodes 
     * and in each index is the distance to the given node.
     */
    public int[] getDistances(int index) {
        return this.graph[index];
    }
    
    /**
     * Returns the place with the given index.
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
     * Creates a reduced graph from the current graph, where each edge that is longer
     * than the given maximum distance, will be removed.
     * @param maximumDistance The maximum distance; longer edges will be removed.
     * @return The reduced graph where longer edges have been removed.
     */
    public int[][] getReducedGraph(int maximumDistance) {
        int[][] ret = new int[this.graph.length][this.graph.length];
        for (int i = 0; i < ret.length; i ++) {
            for (int j = 0; j < ret.length; j ++) {
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
     * Creates a smaller graph consisting of given amount of nodes.
     * @param maximumNumber The maximum amount of nodes.
     * @return The reduced graph in which the rest of the nodes have been removed.
     */
    public int[][] getSmallerGraph(int maximumNumber) {
        if (maximumNumber > this.graph.length) {
            return this.graph;
        }
        int[][] smaller = new int[maximumNumber][maximumNumber];
        for (int i = 0; i < smaller.length; i ++) {
            for (int j = 0; j < smaller.length; j ++) {
                smaller[i][j] = graph[i][j];
            }
        }
        return smaller;
    }
    
    /**
     * Returns the amount of nodes in the current graph.
     * @return The amount of nodes.
     */
    public int getSizeOfCurrentGraph() {
        return this.graph.length;
    }
}
