package algorithms;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mshroom
 */
public class AStarTest {
    GraphCreator graphs;
    AStar aStarSmall;
    AStar aStarBig;
    AStar aStarNoPath;
    
    @Before
    public void setUp() {
        this.graphs = new GraphCreator();
        createSmallGraph();        
        createBigGraph();        
        createGraphWithNoPath();        
    }

    @Test
    public void aStarCalculatesShortestDistanceCorrectlyWithSmallInput() {
        this.aStarSmall.calculateShortestPath();
        assertEquals(180, this.aStarSmall.getDistanceTo(3));
    }
    
    @Test
    public void aStarPrintsShortestPathCorrectlyWithSmallInput() {
        this.aStarSmall.calculateShortestPath();
        assertEquals("0 > 1 > 2 > 3", this.aStarSmall.getShortestPath(3));
    }
    
    
    @Test
    public void aStarCalculatesShortestDistanceCorrectlyWithBigInput() {
        this.aStarBig.calculateShortestPath();
        assertEquals(90, this.aStarBig.getDistanceTo(99));
    }
    
    @Test
    public void aStarPrintsShortestPathCorrectlyWithBigInput() {
        this.aStarBig.calculateShortestPath();
        assertEquals("0 > 99", this.aStarBig.getShortestPath(99));
    }
    
    @Test
    public void shortestDistanceIsMinusTwoIfThereIsNoPath() { 
        this.aStarNoPath.calculateShortestPath();
        assertEquals(-2, this.aStarNoPath.getDistanceTo(6));
    }
    
    @Test
    public void noPathMessageIsPrintedWhenThereIsNoPath() {
        this.aStarNoPath.calculateShortestPath();
        assertEquals("There is no path", this.aStarNoPath.getShortestPath(6));
    }
    

    private void createSmallGraph() {
        int[] distances = {101, 110, 60, 0, 95};
        int[][] smallGraph = graphs.createSmallGraphForAStar();
        this.aStarSmall = new AStar(smallGraph, distances, 3);
    }

    private void createBigGraph() {
        int[][] graph = graphs.createBigGraphForPathfinding();
        int[] distances = new int[100];
        distances[0] = 90;
        for (int i = 1; i < distances.length; i ++) {            
            distances[i] = 100 - i;
        }             
        this.aStarBig = new AStar(graph, distances, 99);
    }
    
    private void createGraphWithNoPath() {
        int[][] graph = graphs.createGraphWithNoPath();
        int[] distances = {6, 5, 4, 3, 2, 1, 0};
        this.aStarNoPath = new AStar(graph, distances, 6);
    }

}
