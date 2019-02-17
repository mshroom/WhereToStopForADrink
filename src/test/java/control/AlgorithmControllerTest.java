package control;

import control.AlgorithmController;
import control.GraphStore;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mshroom
 */
public class AlgorithmControllerTest {

    AlgorithmController algo;
    GraphStore graphs;

    @Before
    public void setUp() {
        this.algo = new AlgorithmController();
        this.graphs = new GraphStore();
    }

    @Test
    public void comparingPathAlgorithmsIncludesDataOfAllAlgorithms() throws Throwable {
        String result = algo.compareShortestPathAlgorithms(graphs.createSmallGraphForAStar(), 4, graphs.createDistancesForSmallGraphForAStar(4));
        assertTrue(result.contains("Dijkstra") && result.contains("AStar") && result.contains("Bfs"));
    }

    @Test
    public void comparingPathAlgorithmsIncludesDataOfAllResultCategories() throws Throwable {
        String result = algo.compareShortestPathAlgorithms(graphs.createSmallGraphForAStar(), 4, graphs.createDistancesForSmallGraphForAStar(4));
        assertTrue(result.contains("Time elapsed") && result.contains("Distance to goal") && result.contains("Shortest path"));
    }
    
    @Test
    public void comparingPathAlgorithmsIncludesValidPaths() throws Throwable {
        String result = algo.compareShortestPathAlgorithms(graphs.createSmallGraphForAStar(), 3, graphs.createDistancesForSmallGraphForAStar(3));
        assertTrue(result.contains("0 > 1 > 2 > 3") && result.contains("0 > 4 > 3"));
    }
    
    @Test
    public void comparingPathAlgorithmsIncludesValidDistances() throws Throwable {
        String result = algo.compareShortestPathAlgorithms(graphs.createSmallGraphForAStar(), 3, graphs.createDistancesForSmallGraphForAStar(3));
        assertTrue(result.contains("180 meters") && result.contains("2 edges"));
    }

    @Test
    public void comparingRouteAlgorithmsncludesDataOfAllAlgorithms() throws Throwable {
        String result = algo.compareShortestRouteAlgorithms(graphs.createSmallCompleteGraph());
        assertTrue(result.contains("TspExact") && result.contains("TspNearestNeighbour"));
    }

    @Test
    public void comparingRouteAlgorithmsIncludesDataOfAllResultCategories() throws Throwable {
        String result = algo.compareShortestRouteAlgorithms(graphs.createSmallCompleteGraph());
        assertTrue(result.contains("Time elapsed") && result.contains("Route length") && result.contains("Shortest route"));
    }
    
    @Test
    public void comparingRouteAlgorithmsIncludesValidRoute() throws Throwable {
        String result = algo.compareShortestRouteAlgorithms(graphs.createSmallCompleteGraph());
        assertTrue(result.contains("0 > 2 > 4 > 1 > 3 > 0") || result.contains("0 > 3 > 1 > 4 > 2 > 0"));
    }
    
        @Test
    public void comparingRouteAlgorithmsIncludesValidDistance() throws Throwable {
        String result = algo.compareShortestRouteAlgorithms(graphs.createSmallCompleteGraph());
        assertTrue(result.contains("5 meters"));
    }
    
}
