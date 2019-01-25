package algorithms;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mshroom
 */
public class BfsTest {

    Bfs bfsSmall;
    Bfs bfsBig;
    Bfs bfsNoPath;

    @Before
    public void setUp() {
        GraphCreator graphs = new GraphCreator();
        this.bfsSmall = new Bfs(graphs.createSmallGraphForPathfinding());
        this.bfsBig = new Bfs(graphs.createBigGraphForPathfinding());
        this.bfsNoPath = new Bfs(graphs.createGraphWithNoPath());
    }

    @Test
    public void bfsCalculatesShortestDistanceCorrectlyWithSmallInput() {
        this.bfsSmall.calculateShortestPath();
        assertEquals(2, this.bfsSmall.getDistanceTo(6));
    }

    @Test
    public void bfsPrintsShortestPathCorrectlyWithSmallInput() {
        this.bfsSmall.calculateShortestPath();
        assertEquals("0 > 2 > 6", this.bfsSmall.getShortestPath(6));
    }

    @Test
    public void bfsCalculatesShortestDistanceCorrectlyWithBigInput() {
        this.bfsBig.calculateShortestPath();
        assertEquals(1, this.bfsBig.getDistanceTo(99));
    }

    @Test
    public void bfsPrintsShortestPathCorrectlyWithBigInput() {
        this.bfsBig.calculateShortestPath();
        assertEquals("0 > 99", this.bfsBig.getShortestPath(99));
    }

    @Test
    public void shortestDistanceIsMinusOneIfThereIsNoPath() {
        this.bfsNoPath.calculateShortestPath();
        assertEquals(-1, this.bfsNoPath.getDistanceTo(6));
    }

    @Test
    public void noPathMessageIsPrintedWhenThereIsNoPath() {
        this.bfsNoPath.calculateShortestPath();
        assertEquals("There is no path", this.bfsNoPath.getShortestPath(6));
    }
}
