package control;

import algorithms.AStar;
import algorithms.Bfs;
import algorithms.Dijkstra;
import algorithms.ShortestPath;
import algorithms.ShortestRoute;
import algorithms.TspExact;
import algorithms.TspNearestNeighbour;

/**
 * AlgorithmController contains methods to get comparison data on the
 * performance and the results of the algorithms.
 *
 * @author mshroom
 */
public class AlgorithmController {
    private ShortestPath currentDijkstra;
    private ShortestPath currentAStar;
    private ShortestPath currentBfs;
    private ShortestRoute currentTspExact;
    private ShortestRoute currentTspNearestNeighbour;

    public AlgorithmController() {
    }

    /**
     * Method tests all shortest path algorithms to find the shortest path to
     * the given goal node.
     *
     * @param graph The graph used to test algorithms.
     * @param goal The index of the goal node.
     * @param distances An array containing distance estimates from each node to
     * the goal.
     * @return A String describing the results and the time elapsed for each
     * algorithm.
     * @throws Throwable if there is an exception while processing the data.
     */
    public String compareShortestPathAlgorithms(int[][] graph, int goal, int[] distances) throws Throwable {
        String ret = "";
        this.currentDijkstra = new Dijkstra(graph);
        this.currentBfs = new Bfs(graph);
        this.currentAStar = new AStar(graph, distances, goal);
        ret += "\n" + String.format("%-20s", "Algorithm") + String.format("%-20s", "Time elapsed") 
                + String.format("%-20s", "Distance to goal") + "Shortest path";
        ret += this.pathStatistics(currentDijkstra, goal);
        ret += this.pathStatistics(currentAStar, goal);
        ret += this.pathStatistics(currentBfs, goal);
        return ret;
    }

    /**
     * Method tests a ShortestPath algorithm and creates the statistics
     * containing the time elapsed, the length of the shortest path and the
     * description of the path.
     *
     * @param shortestPath The algorithm (a ShortestPath object) to be tested.
     * @param node The index goal node.
     * @return A String containing the results.
     * @throws Throwable if there is an exception while processing data.
     */
    private String pathStatistics(ShortestPath shortestPath, int node) throws Throwable {
        String ret = "";
        long time = this.getPerformanceTime(shortestPath, node);
        ret += "\n" + String.format("%-20s", shortestPath.getClass().getSimpleName());
        ret += String.format("%-20s", ((time) + " ns"));
        String unit = "";
        if (shortestPath.getClass().getSimpleName().equals("Bfs")) {
            unit = "edges";
        } else {
            unit = "meters";
        }
        if (shortestPath.getDistanceTo(node) < 0) {
            ret += String.format("%-20s", "infinite");
        } else {
            ret += String.format("%-20s", (shortestPath.getDistanceTo(node) + " " + unit));
        }        
        ret += shortestPath.getShortestPath(node);
        return ret;
    }

    /**
     * Runs the shortest path algorithm once and returns the time elapsed in
     * nanoseconds.
     *
     * @param shortestPath The ShortestPath algorithm to be tested.
     * @param node The index of the goal node.
     * @return The time elapsed in nanoseconds.
     * @throws Throwable if there is an exception while processing data.
     */
    private long getPerformanceTime(ShortestPath shortestPath, int node) throws Throwable {
        long startingTime = System.nanoTime();
        shortestPath.calculateShortestPath();
        long completedTime = System.nanoTime();
        return completedTime - startingTime;
    }
    
    public int[] getCurrentAStarPath() {
        return this.currentAStar.getPath();
    }
    
    public int[] getCurrentDijkstraPath() {
        return this.currentDijkstra.getPath();
    }
    
    public int[] getCurrentBfsPath() {
        return this.currentBfs.getPath();
    }
    
    public boolean pathWasFound(int goal) {
        return this.currentAStar.pathWasFound(goal);
    }

    /**
     * Method tests all shortest route algorithms to find the shortest route
     * visiting all nodes.
     *
     * @param graph The graph to be tested.
     * @return A String describing the results and the time elapsed for each
     * algorithm.
     */
    public String compareShortestRouteAlgorithms(int[][] graph, boolean all) throws Throwable {
        String ret = "";
        ret += "\n" + String.format("%-20s", "Algorithm") + String.format("%-20s", "Time elapsed") 
                + String.format("%-20s", "Route length") + "Shortest route";
        if (all) {
           this.currentTspExact = new TspExact(graph); 
           ret += this.routeStatistics(currentTspExact);
        }
        
        this.currentTspNearestNeighbour = new TspNearestNeighbour(graph);   
        ret += this.routeStatistics(currentTspNearestNeighbour);
        return ret;
    }

    /**
     * Method tests a ShortestRoute algorithm and creates the statistics
     * containing the time elapsed, the length of the shortest path and the
     * description of the path.
     *
     * @param shortestRoute The algorithm (a ShortestRoute object) to be tested.
     * @return A String containing the results.
     */
    private String routeStatistics(ShortestRoute shortestRoute) throws Throwable {
        String ret = "";
        long time = this.getPerformanceTime(shortestRoute);
        ret += "\n" + String.format("%-20s", shortestRoute.getClass().getSimpleName());
        ret += String.format("%-20s", (time + " ns"));        
        ret += String.format("%-20s", (shortestRoute.getLengthOfShortestRoute() + " meters"));
        ret += shortestRoute.printShortestRoute();
        return ret;
    }
    
     /**
     * Runs the shortest route algorithm once and returns the time elapsed in
     * nanoseconds.
     *
     * @param shortestRoute The ShortestRoute algorithm to be tested.
     * @return The time elapsed in nanoseconds.
     * @throws Throwable if there is an exception while processing data.
     */
    private long getPerformanceTime(ShortestRoute shortestRoute) throws Throwable {
        long startingTime = System.nanoTime();
        shortestRoute.calculateShortestRoute();
        long completedTime = System.nanoTime();
        return completedTime - startingTime;
    }
    
    public int[] getCurrentTspExactRoute() {
        return currentTspExact.getShortestRoute();
    }
    
    public int[] getCurrentTspNearestNeighbourRoute() {
        return currentTspNearestNeighbour.getShortestRoute();
    }

}
