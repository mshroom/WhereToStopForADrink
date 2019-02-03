package control;

import data.DistanceFinder;
import dataStructures.PlaceQueue;
import io.FileIO;

/**
 *
 * @author mshroom
 */
public class GraphController {;
    private Place[] places;
    private DistanceFinder finder;
    private int[][] graph;
    private PlaceController placeController;
    
    public GraphController() {
        this.finder = new DistanceFinder();
        this.placeController = new PlaceController();
    }
    
    public void importPlaces(String file) throws Exception {
        placeController.importPlaces(file);
        this.places = placeController.getPlaces();
        this.initializeGraph();
    }
    
    public void useSavedPlaces() throws Exception {
        placeController.useSavedPlaces();
        this.places = placeController.getPlaces();
        this.fetchSavedGraph();
    }
    
    public void savePlaces() throws Exception {
        placeController.savePlaces();
        this.saveGraph();
    }
    
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
                    int length = this.finder.findDistance(a, b);
                    graph[i][j] = length;                    
                } else {
                    graph[i][j] = graph[j][i];
                }
            }
        }
    }
    
    private void saveGraph() throws Exception {
        FileIO io = new FileIO("savedGraph.txt");
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
    
    private void fetchSavedGraph() throws Exception {        
        FileIO io = new FileIO("savedGraph.txt");
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
    
    public int[] getDistances(int index) {
        return this.graph[index];
    }
    
    public Place getPlace(int index) {
        return this.places[index];
    }
    
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
    
    public int getSizeOfCurrentGraph() {
        return this.graph.length;
    }
}
