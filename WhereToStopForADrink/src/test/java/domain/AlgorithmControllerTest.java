package domain;

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
    public void comparingPathAlgorithmsWithSmallTestGraphIncludesDataOfAllAlgorithms() throws Throwable {
        String result = algo.compareShortestPathAlgorithmsWithSmallTestGraph(4);
        assertTrue(result.contains("Dijkstra") && result.contains("AStar") && result.contains("Bfs"));
    }

    @Test
    public void comparingPathAlgorithmsWithSmallTestGraphIncludesDataOfAllResultCategories() throws Throwable {
        String result = algo.compareShortestPathAlgorithmsWithSmallTestGraph(4);
        assertTrue(result.contains("Time elapsed") && result.contains("Distance to goal") && result.contains("Shortest path"));
    }

    @Test
    public void comparingPathAlgorithmsWithBigTestGraphIncludesDataOfAllAlgorithms() throws Throwable {
        String result = algo.compareShortestPathAlgorithmsWithBigTestGraph(4);
        assertTrue(result.contains("Dijkstra") && result.contains("AStar") && result.contains("Bfs"));
    }

    @Test
    public void comparingPathAlgorithmsWithBigTestGraphIncludesDataOfAllResultCategories() throws Throwable {
        String result = algo.compareShortestPathAlgorithmsWithBigTestGraph(4);
        assertTrue(result.contains("Time elapsed") && result.contains("Distance to goal") && result.contains("Shortest path"));
    }

    @Test
    public void comparingRouteAlgorithmsWithSmallTestGraphIncludesDataOfAllAlgorithms() {
        String result = algo.compareShortestRouteAlgorithms(graphs.createSmallCompleteGraph());
        assertTrue(result.contains("Tsp") && result.contains("TspNn"));
    }

    @Test
    public void comparingRouteAlgorithmsWithSmallTestGraphIncludesDataOfAllResultCategories() {
        String result = algo.compareShortestRouteAlgorithms(graphs.createSmallCompleteGraph());
        assertTrue(result.contains("Time elapsed") && result.contains("Route length") && result.contains("Shortest route"));
    }

    @Test
    public void comparingRouteAlgorithmsWithBigTestGraphIncludesDataOfAllAlgorithms() {
        String result = algo.compareShortestRouteAlgorithms(graphs.createBigCompleteGraph());
        assertTrue(result.contains("Tsp") && result.contains("TspNn"));
    }

    @Test
    public void comparingRouteAlgorithmsWithBigTestGraphIncludesDataOfAllResultCategories() {
        String result = algo.compareShortestRouteAlgorithms(graphs.createBigCompleteGraph());
        assertTrue(result.contains("Time elapsed") && result.contains("Route length") && result.contains("Shortest route"));
    }
    

}
