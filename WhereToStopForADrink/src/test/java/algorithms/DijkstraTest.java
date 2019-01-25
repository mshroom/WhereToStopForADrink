package algorithms;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mshroom
 */
public class DijkstraTest {
    Dijkstra dijkstraSmall;
    Dijkstra dijkstraBig;
    Dijkstra dijkstraNoPath;
    
    @Before
    public void setUp() {
        GraphCreator graphs = new GraphCreator();        
        this.dijkstraSmall = new Dijkstra(graphs.createSmallGraphForPathfinding());
        this.dijkstraBig = new Dijkstra(graphs.createBigGraphForPathfinding());
        this.dijkstraNoPath = new Dijkstra(graphs.createGraphWithNoPath());
    }

    @Test
    public void dijkstraCalculatesShortestDistanceCorrectlyWithSmallInput() {
        this.dijkstraSmall.calculateShortestPath();
        assertEquals(4, this.dijkstraSmall.getDistanceTo(6));
    }
    
    @Test
    public void dijkstraPrintsShortestPathCorrectlyWithSmallInput() {
        this.dijkstraSmall.calculateShortestPath();
        assertEquals("0 > 1 > 4 > 5 > 6", this.dijkstraSmall.getShortestPath(6));
    }
    
    @Test
    public void dijkstraCalculatesShortestDistanceCorrectlyWithBigInput() {
        this.dijkstraBig.calculateShortestPath();
        assertEquals(90, this.dijkstraBig.getDistanceTo(99));
    }
    
    @Test
    public void dijkstraPrintsShortestPathCorrectlyWithBigInput() {
        this.dijkstraBig.calculateShortestPath();
        assertEquals("0 > 99", this.dijkstraBig.getShortestPath(99));
    }
    
    @Test
    public void shortestDistanceIsMinusOneIfThereIsNoPath() {
        this.dijkstraNoPath.calculateShortestPath();
        assertEquals(-1, this.dijkstraNoPath.getDistanceTo(6));
    }
    
    @Test
    public void noPathMessageIsPrintedWhenThereIsNoPath() {
        this.dijkstraNoPath.calculateShortestPath();
        assertEquals("There is no path", this.dijkstraNoPath.getShortestPath(6));
    }
}
