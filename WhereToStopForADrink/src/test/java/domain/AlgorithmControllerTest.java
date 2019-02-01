package domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mshroom
 */
public class AlgorithmControllerTest {

    AlgorithmController algo;

    @Before
    public void setUp() {
        this.algo = new AlgorithmController();
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
        String result = algo.compareShortestRouteAlgorithmsWithSmallTestGraph();
        assertTrue(result.contains("Tsp") && result.contains("TspNn"));
    }

    @Test
    public void comparingRouteAlgorithmsWithSmallTestGraphIncludesDataOfAllResultCategories() {
        String result = algo.compareShortestRouteAlgorithmsWithSmallTestGraph();
        assertTrue(result.contains("Time elapsed") && result.contains("Route length") && result.contains("Shortest route"));
    }

    @Test
    public void comparingRouteAlgorithmsWithBigTestGraphIncludesDataOfAllAlgorithms() {
        String result = algo.compareShortestRouteAlgorithmsWithBigTestGraph();
        assertTrue(result.contains("Tsp") && result.contains("TspNn"));
    }

    @Test
    public void comparingRouteAlgorithmsWithBigTestGraphIncludesDataOfAllResultCategories() {
        String result = algo.compareShortestRouteAlgorithmsWithBigTestGraph();
        assertTrue(result.contains("Time elapsed") && result.contains("Route length") && result.contains("Shortest route"));
    }
    

}
