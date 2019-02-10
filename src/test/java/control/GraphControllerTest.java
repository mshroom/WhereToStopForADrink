/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dataStructures.ObjectQueue;
import dataStructures.Place;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        gc.importPlaces("data/testPlaces.txt");
        int[][] graph = gc.getGraph();
        assertEquals(1, graph[0][1]);
        assertEquals(2, graph[0][2]);
        assertEquals(1, graph[1][2]);
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
        gc.save("data/testSaved.txt", "data/testSavedGraph.txt");                
        PlaceController newPc = new PlaceController(mockAddress, new ObjectQueue(10));
        GraphController newGc = new GraphController(mockDistance, newPc, new int[1][1]);
        newGc.useSaved("data/testSaved.txt", "data/testSavedGraph.txt");
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

    private void createTestDataForGraphController() {
        queue.add(new Place(0, "New Place 1", "Example address 1", "x", "y"));
        queue.add(new Place(1, "New Place 2", "Example address 2", "x", "y"));
        queue.add(new Place(2, "New Place 2", "Example address 2", "x", "y"));
        pc = new PlaceController(mockAddress, queue);
        int[][] graph = {{0, 2, 4}, {2, 0, 3}, {4, 3, 0}};
        gc = new GraphController(mockDistance, pc, graph);  
    }

    private void setReturnValuesForMocks() throws Exception {
        String[] co = new String[2];
        co[0] = "x";
        co[1] = "y";
        when(mockAddress.findCoordinates("Example address 1")).thenReturn(co);
        when(mockAddress.findCoordinates("Example address 2")).thenReturn(co);
        when(mockAddress.findCoordinates("Example address 3")).thenReturn(co);       
        when(mockDistance.findDistance("x", "y", "x", "y")).thenReturn(1).thenReturn(2).thenReturn(1);
    }
}
