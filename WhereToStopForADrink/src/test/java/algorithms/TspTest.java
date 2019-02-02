package algorithms;

import control.GraphStore;
import java.util.Arrays;
import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author mshroom
 */
public class TspTest {
    Tsp tspSmall;
    Tsp tspBig;
    
    @Before
    public void setUp() {
        GraphStore graphs = new GraphStore();
        this.tspSmall = new Tsp(graphs.createSmallCompleteGraph());        
        this.tspBig = new Tsp(graphs.createBigCompleteGraph());
    }
       
    @Test
    public void tspCalculatesLengthOfShortestRouteCorrectlyWithSmallInput() {
        tspSmall.calculateShortestRoute();
        assertEquals(5, tspSmall.getLengthOfShortestRoute());
    }
    
    @Test
    public void tspCalculatesLengthOfShortestRouteCorrectlyWithBigInput() {
        tspBig.calculateShortestRoute();
        assertEquals(20, tspBig.getLengthOfShortestRoute());
    }
    
    @Test
    public void tspFindsShortestRouteWithSmallInput() {
        tspSmall.calculateShortestRoute();
        int[] correct1 = {0, 2, 4, 1, 3};
        int[] correct2 = {0, 3, 1, 4, 2};
        int[] route = tspSmall.getShortestRoute();
        assertTrue(Arrays.equals(correct1, route) || Arrays.equals(correct2, route));
    }
    
    @Test
    public void tspFindsShortestRouteWithBigInput() {
        tspBig.calculateShortestRoute();
        int[] correct1 = {0, 2, 4, 1, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19};
        int[] correct2 = {0, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 3, 1, 4, 2};
        int[] route = tspBig.getShortestRoute();
        assertTrue(Arrays.equals(correct1, route) || Arrays.equals(correct2, route));
    }
}
