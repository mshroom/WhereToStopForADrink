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
    
    @Test
    public void commandNewInSettingsMenuAsksForFileToImport() throws Exception {
        inputs.add("settings");
        inputs.add("new");
        inputs.add("file.txt");
        inputs.add("quit");
        createUi();
        verify(graphs, times(1)).importPlaces(eq("file.txt"));
    }
    
    @Test
    public void commandMemoryInSettingsMenuAccessesSavedFiles() throws Exception {
        inputs.add("settings");
        inputs.add("memory");
        inputs.add("quit");
        createUi();
        verify(graphs, times(1)).useSaved(eq("data/userData/savedPlaces.txt"), eq("data/userData/savedGraph.txt"));
    }

    @Test
    public void commandCustomInRouteMenuGivesErrorMessageIfCustomGraphIsNotSet() {
        inputs.add("route");
        inputs.add("custom");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("You have not imported any graph."));
    }    
    
    @Test
    public void commandCustomInRouteMenuRequiresValidNumber() {
        this.routeCustom();
        inputs.add("30");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("Not a valid number."));
    }
    
    @Test
    public void commandCustomInRouteMenuComparesOnlyApproximationAlgorithmsIfNumberIsTooBig() throws Throwable {
        this.routeCustom();
        inputs.add("16");
        inputs.add("");
        inputs.add("quit");
        createUi();
        verify(graphs, times(1)).getSmallerGraph(eq(16));
        verify(algo, times(1)).compareShortestRouteAlgorithms(any(int[][].class), eq(false));
    }
    
   
    @Test
    public void commandCustomInRouteMenuComparesAllAlgorithmsIfNumberIsSmall() throws Throwable {
        this.routeCustom();
        inputs.add("15");
        inputs.add("");
        inputs.add("quit");
        createUi();
        verify(graphs, times(1)).getSmallerGraph(eq(15));
        verify(algo, times(1)).compareShortestRouteAlgorithms(any(int[][].class), eq(true));
    }
    
    @Test
    public void commandCustomInPathMenuGivesErrorMessageIfCustomGraphIsNotSet() {
        inputs.add("path");
        inputs.add("custom");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("You have not imported any graph."));
    }
    
    @Test
    public void commandCustomInPathMenuRequiresValidNumber() {
        this.pathCustom();
        inputs.add("-1");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("Not a valid number."));
    }
    
    @Test
    public void commandCustomInPathMenuComparesAlgorithmsWithCustomParameters() throws Throwable {
        this.pathCustom();
        inputs.add("15");
        inputs.add("200");
        inputs.add("a");
        inputs.add("quit");
        createUi();
        verify(graphs, times(1)).getReducedGraph(eq(200));
        verify(graphs, times(1)).getDistances(eq(15));
        verify(algo, times(1)).compareShortestPathAlgorithms(any(int[][].class), eq(15), any(int[].class));
    }
    
    @Test
    public void commandSaveInSettingsMenuGivesErrorMessageIfCustomGraphIsNotSet() {
        inputs.add("settings");
        inputs.add("save");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("You have not imported any graph."));
    }
    
    @Test
    public void commandSaveInSettingsMenuSavesCurrentGraph() throws Exception {
        inputs.add("settings");
        inputs.add("memory");
        inputs.add("save");
        inputs.add("quit");
        createUi();
        verify(graphs, times(1)).save(eq("data/userData/savedPlaces.txt"), eq("data/userData/savedGraph.txt"));   
    }
    
    @Test
    public void commandHomeInSettingsMenuGivesErrorMessageIfCustomGraphIsNotSet() {
        inputs.add("settings");
        inputs.add("home");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("You have not imported any graph."));
    }    
    
    @Test
    public void commandHomeInSettingsMenuAsksForNewAddressAndChangesIt() throws Throwable {
        inputs.add("settings");
        inputs.add("memory");
        inputs.add("home");
        inputs.add("yes");
        inputs.add("My New Address");
        inputs.add("quit");
        createUi();
        verify(graphs, times(1)).changeHomeAddress(eq("My New Address"));
    }
    
    @Test
    public void commandHomeInSettingsMenuDoesNotChangeAddressIfGivenEmptyInput() throws Throwable {
        inputs.add("settings");
        inputs.add("memory");
        inputs.add("home");
        inputs.add("yes");
        inputs.add("");
        inputs.add("quit");
        createUi();
        verify(graphs, times(0)).changeHomeAddress(eq(""));
    }
    
    @Test
    public void commandAddInSettingsMenuGivesErrorMessageIfCustomGraphIsNotSet() {
        inputs.add("settings");
        inputs.add("add");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("You have not imported any graph."));
    }
    
    @Test
    public void commandAddInSettingsMenuAsksForNewPlaceNameAndAddressAndAddsTheNewPlace() {
        inputs.add("settings");
        inputs.add("memory");
        inputs.add("add");
        inputs.add("Name of the place");
        inputs.add("Address");
        inputs.add("quit");
        createUi();
        verify(graphs, times(1)).addPlace(eq("Name of the place"), eq("Address"));
    }
    
    @Test
    public void commandAddInSettingsMenuDoesNotAddAPlaceWithEmptyName() {
        inputs.add("settings");
        inputs.add("memory");
        inputs.add("add");
        inputs.add("");
        inputs.add("Address");
        inputs.add("quit");
        createUi();
        verify(graphs, times(0)).addPlace(eq(""), eq("Address"));
    }
    
    @Test
    public void commandAddInSettingsMenuDoesNotAddPlaceWithEmptyAddress() {
        inputs.add("settings");
        inputs.add("memory");
        inputs.add("add");
        inputs.add("Name of the place");
        inputs.add("");
        inputs.add("quit");
        createUi();
        verify(graphs, times(0)).addPlace(eq("Name of the place"), eq(""));    
    }
    
    @Test
    public void commandFindInPathMenuGivesErrorMessageIfCustomGraphIsNotSet() {
        inputs.add("path");
        inputs.add("find");
        inputs.add("quit");
        createUi();
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("You have not imported any graph."));
    }
    
    @Test
    public void commandFindInPathMenuMakesASearchWithGivenWordAndPrintsFoundPlaces() {
        inputs.add("settings");
        inputs.add("memory");
        inputs.add("back");
        inputs.add("path");
        inputs.add("find");
        inputs.add("place");
        inputs.add("1");
        inputs.add("200");
        inputs.add("");
        inputs.add("quit");
        createUi();
        verify(graphs, times(1)).findPlaces("place");
        Queue<String> prints = io.getPrints();
        assertTrue(prints.contains("[1] A Place"));
    }
    
    @Test
    public void commandFindInPathMenuCancelsSearchWithEmptyInput() {
        inputs.add("settings");
        inputs.add("memory");
        inputs.add("back");
        inputs.add("path");
        inputs.add("find");
        inputs.add("");
        inputs.add("quit");
        createUi();
        verify(graphs, times(0)).findPlaces("");
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
        when(graphs.getSizeOfCurrentGraph()).thenReturn(20);
        when(graphs.getSmallerGraph(anyInt())).thenReturn(new int[1][1]);   
        when(graphs.getReducedGraph(anyInt())).thenReturn(new int[1][1]);
        when(graphs.getDistances(anyInt())).thenReturn(new int[1]);
        when(graphs.findPlaces(eq("place"))).thenReturn("[1] A Place");
    }
    
    private void routeCustom() {
        inputs.add("settings");
        inputs.add("memory");
        inputs.add("back");
        inputs.add("route");
        inputs.add("custom");
    }
    
    private void pathCustom() {
        inputs.add("settings");
        inputs.add("memory");
        inputs.add("back");
        inputs.add("path");
        inputs.add("custom");
    }
}
