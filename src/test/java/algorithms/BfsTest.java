package algorithms;

import control.GraphStore;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mshroom
 */
public class BfsTest {

    GraphStore graphs;
    Bfs bfsSmall;
    Bfs bfsBig;
    Bfs bfsNoPath;
    Bfs bfsBigRandom;

    @Before
    public void setUp() {
        graphs = new GraphStore();
        this.bfsSmall = new Bfs(graphs.createSmallGraphForPathfinding());
        this.bfsBig = new Bfs(graphs.createBigSimpleGraphForPathfinding());
        this.bfsNoPath = new Bfs(graphs.createGraphWithNoPath());
    }

    @Test
    public void bfsCalculatesShortestDistanceCorrectlyWithSmallInput() throws Throwable {
        this.bfsSmall.calculateShortestPath();
        assertEquals(2, this.bfsSmall.getDistanceTo(6));
    }

    @Test
    public void bfsPrintsShortestPathCorrectlyWithSmallInput() throws Throwable {
        this.bfsSmall.calculateShortestPath();
        assertEquals("0 > 2 > 6", this.bfsSmall.getShortestPath(6));
    }

    @Test
    public void bfsCalculatesShortestDistanceCorrectlyWithBigInput() throws Throwable {
        this.bfsBig.calculateShortestPath();
        assertEquals(1, this.bfsBig.getDistanceTo(99));
    }

    @Test
    public void bfsPrintsShortestPathCorrectlyWithBigInput() throws Throwable {
        this.bfsBig.calculateShortestPath();
        assertEquals("0 > 99", this.bfsBig.getShortestPath(99));
    }

    @Test
    public void shortestDistanceIsMinusOneIfThereIsNoPath() throws Throwable {
        this.bfsNoPath.calculateShortestPath();
        assertEquals(-1, this.bfsNoPath.getDistanceTo(6));
    }

    @Test
    public void noPathMessageIsPrintedWhenThereIsNoPath() throws Throwable {
        this.bfsNoPath.calculateShortestPath();
        assertEquals("There is no path", this.bfsNoPath.getShortestPath(6));
    }
    
    @Test
    public void bfsFindsPathEvenWithBigRandomInput() throws Throwable {
        this.bfsBigRandom = new Bfs(graphs.createRandomGraphForPathfinding(2000));
        this.bfsBigRandom.calculateShortestPath();
        assertTrue(bfsBigRandom.pathWasFound(1999));
    }
}
