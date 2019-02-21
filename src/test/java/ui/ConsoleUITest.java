package ui;

import control.AlgorithmController;
import control.GraphController;
import control.GraphStore;
import dataStructures.Queue;
import io.StubIO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author mshroom
 */
public class ConsoleUITest {

    private ConsoleUI ui;
    private StubIO io;
    private Queue<String> inputs;
    private AlgorithmController algo;
    private GraphController graphs;
    private GraphStore store;

    @Before
    public void setUp() {
        this.inputs = new Queue(new String[10]);
    }

    @Test
    public void applicationStartsAndTerminatesWithCorrectOutput() {
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("\nWelcome!"));
        assertTrue(prints.contains("\nThis is the main menu. What would you like to do?\n"));
        assertTrue(prints.contains("Thank you and see you soon!"));
    }

    @Test
    public void commandPathOpensPathMenu() {
        inputs.add("path");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("\nCompare shortest path algorithms\n"));
    }

    @Test
    public void commandRouteOpensPathMenu() {
        inputs.add("route");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("\nCompare shortest route algorithms\n"));
    }

    @Test
    public void commandSettingsOpensSettingsMenu() {
        inputs.add("settings");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("\nSettings for a custom graph\n"));
    }

    @Test
    public void commandSmallInPathMenuComparesAlgorithmsWithSmallTestGraph() throws Throwable {
        inputs.add("path");
        inputs.add("small");
        inputs.add("3");
        inputs.add("quit");
        createUi();
        verify(algo, times(1)).compareShortestPathAlgorithms(any(int[][].class), eq(3), any(int[].class));
        verify(store, times(1)).createSmallGraphForPathfinding2();
    }

    @Test
    public void commandBigInPathMenuComparesAlgorithmsWithBigTestGraph() throws Throwable {
        inputs.add("path");
        inputs.add("big");
        inputs.add("1999");
        inputs.add("quit");
        createUi();
        verify(algo, times(1)).compareShortestPathAlgorithms(any(int[][].class), eq(1999), any(int[].class));
        verify(store, times(1)).createBigRandomGraphForPathfinding();
    }
    
    @Test
    public void commandSmallInPathMenuRequiresValidIndex() throws Throwable {
        inputs.add("path");
        inputs.add("small");
        inputs.add("100");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("Not a valid index."));
        
    }
    
    @Test
    public void commandBigInPathMenuRequiresValidIndex() throws Throwable {
        inputs.add("path");
        inputs.add("big");
        inputs.add("-1");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("Not a valid index."));
        
    }
    
    @Test
    public void commandSmallInRouteMenuComparesAlgorithmsWithSmallTestGraph() throws Throwable {
        inputs.add("route");
        inputs.add("small");
        inputs.add("3");
        inputs.add("quit");
        createUi();
        verify(algo, times(1)).compareShortestRouteAlgorithms(any(int[][].class), eq(true));
        verify(store, times(1)).createSmallCompleteGraph();
    }
    
    @Test
    public void commandBigSimpleInRouteMenuComparesAlgorithmsWithBigSimpleTestGraph() throws Throwable {
        inputs.add("route");
        inputs.add("big");
        inputs.add("simple");
        inputs.add("10");
        inputs.add("quit");
        createUi();
        verify(algo, times(1)).compareShortestRouteAlgorithms(any(int[][].class), eq(true));
        verify(store, times(1)).createBigCompleteGraph();
    }
    
    @Test
    public void commandBigRandomInRouteMenuComparesOnlyApproximationAlgorithmsWithBigRandomTestGraph() throws Throwable {
        inputs.add("route");
        inputs.add("big");
        inputs.add("random");
        inputs.add("10");
        inputs.add("quit");
        createUi();
        verify(algo, times(1)).compareShortestRouteAlgorithms(any(int[][].class), eq(false));
        verify(store, times(1)).createBigRandomCompleteGraph();
    }

    private void createUi() {
        io = new StubIO(inputs);
        algo = mock(AlgorithmController.class);
        graphs = mock(GraphController.class);
        store = mock(GraphStore.class);
        setValuesForMocks();
        this.ui = new ConsoleUI(io, algo, graphs, store);        
        ui.start();
    }

    private void setValuesForMocks() {
        when(store.createSmallGraphForPathfinding2()).thenReturn(new int[1][1]);
        when(store.createBigRandomGraphForPathfinding()).thenReturn(new int[1][1]);
        when(store.createFakeDistancesForAStarGraph(any(int[][].class), anyInt())).thenReturn(new int[1]);
        when(store.createSmallCompleteGraph()).thenReturn(new int[1][1]);
        when(store.createBigCompleteGraph()).thenReturn(new int[1][1]);
        when(store.createBigRandomCompleteGraph()).thenReturn(new int[1][1]);
    }
}
