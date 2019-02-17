/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dataStructures.ObjectQueue;
import dataStructures.Place;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import web.AddressFinder;
import web.DistanceFinder;

/**
 *
 * @author mshroom
 */
public class GraphControllerTest {
    GraphController gc;
    AddressFinder mockAddress;
    PlaceController pc;
    ObjectQueue queue;
    DistanceFinder mockDistance;
    
    @Before
    public void setUp() {
        mockAddress = mock(AddressFinder.class);
        queue = new ObjectQueue(10);
        mockDistance = mock(DistanceFinder.class);
    }

    @Test
    public void placesCanBeImportedFromFileAndDistancesCorrectlyConvertedToGraph() throws Exception {        
        pc = new PlaceController(mockAddress, queue);
        this.setReturnValuesForMocks();        
        gc = new GraphController(mockDistance, pc, new int[1][1]);        
        gc.importPlaces("data/testData/testPlaces.txt");
        int[][] graph = gc.getGraph();
        assertEquals(1, graph[0][1]);
        assertEquals(2, graph[0][2]);
        assertEquals(3, graph[0][3]);
        assertEquals(1, graph[1][2]);
        assertEquals(2, graph[1][3]);
        assertEquals(1, graph[2][3]);
    }
    
    @Test
    public void graphIsInitializedWithHomeAddress() throws Exception {
        pc = new PlaceController(mockAddress, queue);
        this.setReturnValuesForMocks();        
        gc = new GraphController(mockDistance, pc, new int[1][1]);        
        gc.importPlaces("data/testData/testPlaces.txt");
        assertEquals("Viides linja 11", gc.getHomeAddress());
        assertEquals("Viides linja 11", gc.getPlace(0).getAddress());
    }
    
    @Test
    public void homeAddressCanBeChanged() throws Throwable {
        pc = new PlaceController(mockAddress, queue);
        this.setReturnValuesForMocks();        
        gc = new GraphController(mockDistance, pc, new int[1][1]);        
        gc.importPlaces("data/testData/testPlaces.txt");
        gc.changeHomeAddress("New Home");
        assertEquals("New Home", gc.getHomeAddress());
        assertEquals("New Home", gc.getPlace(0).getAddress());
    }
    
    @Test
    public void ifHomeAddressIsChangedDistancesAreUpdated() throws Throwable {
        pc = new PlaceController(mockAddress, queue);
        this.setReturnValuesForMocks();        
        gc = new GraphController(mockDistance, pc, new int[1][1]);        
        gc.importPlaces("data/testData/testPlaces.txt");
        gc.changeHomeAddress("New Home");
        int[][] graph = gc.getGraph();
        assertEquals(2, graph[0][1]);
        assertEquals(3, graph[0][2]);
        assertEquals(4, graph[0][3]);
        assertEquals(1, graph[1][2]);
        assertEquals(2, graph[1][3]);
        assertEquals(1, graph[2][3]);
    }
    
    @Test
    public void aNewPlaceCanBeAdded() throws Throwable {
        pc = new PlaceController(mockAddress, queue);
        this.setReturnValuesForMocks();        
        gc = new GraphController(mockDistance, pc, new int[1][1]);        
        gc.importPlaces("data/testData/testPlaces.txt");
        gc.addPlace("New Place", "New Place address");
        assertEquals("New Place", gc.getPlace(4).getName());
    }
    
    @Test
    public void ifANewPlaceIsAddedDistancesAreUpdated() throws Throwable {
        pc = new PlaceController(mockAddress, queue);
        this.setReturnValuesForMocks();        
        gc = new GraphController(mockDistance, pc, new int[1][1]);        
        gc.importPlaces("data/testData/testPlaces.txt");
        gc.addPlace("New Place", "New Place address");
        int[][] graph = gc.getGraph();
        assertEquals(5, graph[0][4]);
        assertEquals(5, graph[1][4]);
        assertEquals(5, graph[2][4]);
        assertEquals(5, graph[3][4]);
    }
    
    @Test
    public void importingNonExistentFileThrowsAnError() throws Exception {
        pc = new PlaceController(mockAddress, queue);
        this.setReturnValuesForMocks();        
        gc = new GraphController(mockDistance, pc, new int[1][1]); 
        boolean failed = false;
        try {
            gc.importPlaces("fake.txt");
        } catch (Exception ex) {
            failed = true;
        }
        assertTrue(failed);
    }
    
    @Test
    public void graphAndPlacesCanBeSavedAndReaccessedLater() throws Exception {
        this.createTestDataForGraphController();              
        gc.save("data/testData/testSaved.txt", "data/testData/testSavedGraph.txt");                
        PlaceController newPc = new PlaceController(mockAddress, new ObjectQueue(10));
        GraphController newGc = new GraphController(mockDistance, newPc, new int[1][1]);
        newGc.useSaved("data/testData/testSaved.txt", "data/testData/testSavedGraph.txt");
        int[][] graph2 = newGc.getGraph();
        assertEquals(2, graph2[0][1]);
        assertEquals(4, graph2[0][2]);
        assertEquals(3, graph2[1][2]);
    }
    
    @Test
    public void gettingSavedDataFromNonExistentFilesThrowsAnError() {
        pc = new PlaceController(mockAddress, queue);
        gc = new GraphController(mockDistance, pc, new int[1][1]); 
        boolean failed = false;
        try {
            gc.useSaved("fake.txt", "fake.txt");
        } catch (Exception ex) {
            failed = true;
        }
        assertTrue(failed);
    }
    
    @Test
    public void graphCanBeReducedByRemovingEdgesLongerThanWanted() {
        this.createTestDataForGraphController();        
        int[][] reduced = gc.getReducedGraph(3);
        assertEquals(-1, reduced[0][2]);
    }
    
    @Test
    public void graphCanBeShrunkByChoosingMaximumNumberOfNodes() {
        this.createTestDataForGraphController();
        int[][] smaller = gc.getSmallerGraph(2);
        assertEquals(2, smaller.length);
    }
    
    @Test
    public void pathIsPrintedCorrectly() throws Throwable {
        this.createTestDataForGraphController();
        int[] path = {-1, 0, 1};
        String print = gc.printPlaces(path, 2);
        assertEquals("\n1. New Place 1 (Example address 1)\n"
                + "    >> Walk 2 meters\n"
                + "2. New Place 2 (Example address 2)\n"
                + "    >> Walk 3 meters\n"
                + "3. New Place 3 (Example address 3)\n"
                + "\nTotal length: 5 meters", print);
    }
    
    @Test
    public void routeIsPrintedCorrectly() throws Throwable {
        this.createTestDataForGraphController();
        int[] route = {0, 1, 2};
        String print = gc.printPlaces(route);
        assertEquals("1. New Place 1 (Example address 1)\n"
                + "    >> Walk 2 meters\n"
                + "2. New Place 2 (Example address 2)\n"
                + "    >> Walk 3 meters\n"
                + "3. New Place 3 (Example address 3)\n"
                + "    >> Walk 4 meters\n"
                + "Back in New Place 1\n"
                + "\nTotal length: 9 meters", print);
    }

    private void createTestDataForGraphController() {
        queue.add(new Place(0, "New Place 1", "Example address 1", "x", "y"));
        queue.add(new Place(1, "New Place 2", "Example address 2", "x", "y"));
        queue.add(new Place(2, "New Place 3", "Example address 3", "x", "y"));
        pc = new PlaceController(mockAddress, queue);
        int[][] graph = {{0, 2, 4}, {2, 0, 3}, {4, 3, 0}};
        gc = new GraphController(mockDistance, pc, graph);  
    }

    private void setReturnValuesForMocks() throws Exception {
        String[] co = {"x", "y"};
        String[] newHomeCo = {"xN", "yN"};
        String[] newPlaceCo = {"xA", "yA"};
        when(mockAddress.findCoordinates("Example address 1")).thenReturn(co);
        when(mockAddress.findCoordinates("Example address 2")).thenReturn(co);
        when(mockAddress.findCoordinates("Example address 3")).thenReturn(co); 
        when(mockAddress.findCoordinates("Viides linja 11")).thenReturn(co);
        when(mockAddress.findCoordinates("New Home")).thenReturn(newHomeCo);
        when(mockAddress.findCoordinates("New Place address")).thenReturn(newPlaceCo);
        when(mockDistance.findDistance("x", "y", "x", "y"))
                .thenReturn(1).thenReturn(2).thenReturn(3)
                .thenReturn(1).thenReturn(2)
                .thenReturn(1);
        when(mockDistance.findDistance("xN", "yN", "x", "y"))
                .thenReturn(2).thenReturn(3).thenReturn(4);
        when(mockDistance.findDistance("xA", "yA", "x", "y")).thenReturn(5);
    }
}
