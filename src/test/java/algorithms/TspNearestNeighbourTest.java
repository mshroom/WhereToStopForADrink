package algorithms;

import control.GraphStore;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mshroom
 */
public class TspNearestNeighbourTest {
    
    TspNearestNeighbour tspSmall;
    TspNearestNeighbour tspBig;
    TspNearestNeighbour tspNotOptimal;
    
    @Before
    public void setUp() {
        GraphStore graphs = new GraphStore();
        this.tspSmall = new TspNearestNeighbour(graphs.createSmallCompleteGraph());        
        this.tspNotOptimal = new TspNearestNeighbour(graphs.createNotOptimalCompleteGraph());        
        this.tspBig = new TspNearestNeighbour(graphs.createBigCompleteGraph());
    }
    
           
    @Test
    public void tspNnCalculatesLengthOfShortestRouteCorrectlyWithSmallInput() {
        tspSmall.calculateShortestRoute();
        assertEquals(5, tspSmall.getLengthOfShortestRoute());
    }
    
    @Test
    public void tspNnCalculatesLengthOfShortestRouteCorrectlyWithBigInput() {
        tspBig.calculateShortestRoute();
        assertEquals(20, tspBig.getLengthOfShortestRoute());
    }
    
    @Test
    public void tspNnFindsShortestRouteWithSmallInput() {
        tspSmall.calculateShortestRoute();
        int[] correct = {0, 2, 4, 1, 3};
        int[] route = tspSmall.getShortestRoute();
        assertTrue(Arrays.equals(correct, route));
    }
    
    @Test
    public void tspNnFindsShortestRouteWithBigInput() {
        tspBig.calculateShortestRoute();
        int[] correct = {0, 2, 4, 1, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        int[] route = tspBig.getShortestRoute();
        assertTrue(Arrays.equals(correct, route));
    }
    
    @Test
    public void tspNnDoesNotFindShortestRouteWhenNearestNeighbourStrategyReturnsLongerOne() {
        tspNotOptimal.calculateShortestRoute();
        assertEquals(15, tspNotOptimal.getLengthOfShortestRoute());
    }
}
