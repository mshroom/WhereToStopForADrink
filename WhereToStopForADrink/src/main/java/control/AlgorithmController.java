package control;

import algorithms.AStar;
import algorithms.Bfs;
import algorithms.Dijkstra;
import algorithms.ShortestPath;
import algorithms.ShortestRoute;
import algorithms.Tsp;
import algorithms.TspNn;

/**
 * AlgorithmController includes methods to get comparison data on the performance and the results
 * of the algorithms.
 * @author mshroom
 */
public class AlgorithmController {

    private GraphStore graphs;

    public AlgorithmController() {
        this.graphs = new GraphStore();
    }

    /**
     * Method tests all shortest path algorithms to find the shortest path to the given goal node.
     * @param graph The graph used to test algorithms.
     * @param goal The index of the goal node.
     * @param distances An array containing distance estimates from each node to the goal.
     * @return A String describing the results and the time elapsed for each algorithm.
     * @throws Throwable if there is an exception while processing the data.
     */
    public String compareShortestPathAlgorithms(int[][] graph, int goal, int[] distances) throws Throwable {
        String ret = "";
        ShortestPath d = new Dijkstra(graph);
        ShortestPath b = new Bfs(graph);
        ShortestPath a = new AStar(graph, distances, goal);
        ret += "\n" + String.format("%-20s", "Algorithm") + String.format("%-20s", "Time elapsed") + String.format("%-20s", "Distance to goal") + "Shortest path";
        ret += this.pathStatistics(d, goal);
        ret += this.pathStatistics(a, goal);
        ret += this.pathStatistics(b, goal);
        return ret;
    }

    /**
     * Method tests a ShortestPath algorithm and creates the statistics containing the time elapsed, 
     * the length of the shortest path and the description of the path.
     * @param s The algorithm (a ShortestPath object) to be tested.
     * @param node The index goal node.
     * @return A String containing the results.
     * @throws Throwable if there is an exception while processing data.
     */
    private String pathStatistics(ShortestPath s, int node) throws Throwable {
        String ret = "";
        long startingTime = System.nanoTime();
        s.calculateShortestPath();
        long completedTime = System.nanoTime();
        ret += "\n" + String.format("%-20s", s.getClass().getSimpleName());
        ret += String.format("%-20s", ((completedTime - startingTime) + " ns"));
        ret += String.format("%-20s", s.getDistanceTo(node));
        ret += s.getShortestPath(node);
        return ret;
    }   

    /**
     * Method tests all shortest route algorithms to find the shortest route visiting
     * all nodes.
     * @param graph The graph to be tested.
     * @return A String describing the results and the time elapsed for each algorithm. 
     */
    public String compareShortestRouteAlgorithms(int[][] graph) {
        String ret = "";
        ShortestRoute tsp = new Tsp(graph);
        ShortestRoute nn = new TspNn(graph);
        ret += "\n" + String.format("%-20s", "Algorithm") + String.format("%-20s", "Time elapsed") + String.format("%-20s", "Route length") + "Shortest route";
        ret += this.routeStatistics(tsp);
        ret += this.routeStatistics(nn);
        return ret;        
    }
    
    /**
     * Method tests a ShortestRoute algorithm and creates the statistics containing the time elapsed, 
     * the length of the shortest path and the description of the path.
     * @param s The algorithm (a ShortestRoute object) to be tested.
     * @return A String containing the results.
     */
    private String routeStatistics(ShortestRoute s) {
        String ret = "";
        long startingTime = System.nanoTime();
        s.calculateShortestRoute();
        long completedTime = System.nanoTime();
        ret += "\n" + String.format("%-20s", s.getClass().getSimpleName());
        ret += String.format("%-20s", ((completedTime - startingTime) + " ns"));
        ret += String.format("%-20s", s.getLengthOfShortestRoute());
        ret += s.printShortestRoute();
        return ret;        
    }

}
