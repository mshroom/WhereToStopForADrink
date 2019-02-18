package control;

import dataStructures.Queue;
import dataStructures.Place;
import dataStructures.Queueable;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import web.AddressFinder;

/**
 *
 * @author mshroom
 */
public class PlaceControllerTest {

    PlaceController pc;
    AddressFinder mockFinder;
    Queue queue;

    @Before
    public void setUp() {
        mockFinder = mock(AddressFinder.class);
        queue = new Queue(new Queueable[10]);
    }

    @Test
    public void placesCanBeImportedFromFileAndCorrectlyConvertedToPlaceObjects() throws Exception {
        pc = new PlaceController(mockFinder, queue);
        this.setReturnValuesForMockFinder();        
        pc.importPlaces("data/testData/testPlaces.txt");
        Place[] places = pc.getPlaces();
        assertEquals("1;Place 1;Example address 1;x;y||2;Place 2;Example address 2;x;y", places[1].toString() + "||" + places[2].toString());
    }
    
    @Test
    public void defaultHomeAddressIsSetCorrectly() throws Exception {
        pc = new PlaceController(mockFinder, queue);
        this.setReturnValuesForMockFinder();        
        pc.importPlaces("data/testData/testPlaces.txt");
        Place[] places = pc.getPlaces();
        assertEquals("0;Home;Viides linja 11;x;y", places[0].toString());
    }
    
    @Test
    public void homeAddressCanBeChanged() throws Exception {
        pc = new PlaceController(mockFinder, queue);
        this.setReturnValuesForMockFinder();        
        pc.importPlaces("data/testData/testPlaces.txt");
        pc.changeHome("New Home");
        Place[] places = pc.getPlaces();
        assertEquals("0;Home;New Home;x;y", places[0].toString());
    }
    
    @Test
    public void homeAddressIsNotChangedIfCoordinatesAreNotFound() throws Exception {
        pc = new PlaceController(mockFinder, queue);
        this.setReturnValuesForMockFinder();        
        pc.importPlaces("data/testData/testPlaces.txt");
        assertFalse(pc.changeHome("Not-existing"));        
    }
    
    @Test
    public void aPlaceCanBeAdded() throws Exception {
        pc = new PlaceController(mockFinder, queue);
        this.setReturnValuesForMockFinder();        
        pc.importPlaces("data/testData/testPlaces.txt");
        pc.addPlace("New Place", "New Place address");
        Place[] places = pc.getPlaces();
        assertEquals("4;New Place;New Place address;x;y", places[places.length - 1].toString());
    }
    
    @Test
    public void placeIsNotAddedIfCoordinatesAreNotFound() throws Exception {
        pc = new PlaceController(mockFinder, queue);
        this.setReturnValuesForMockFinder();        
        pc.importPlaces("data/testData/testPlaces.txt");
        assertFalse(pc.addPlace("New Place", "Not-existing")); 
    }

    @Test
    public void placesCanBeSavedAndReaccessedLater() throws Exception {
        queue.add(new Place(0, "New Place 1", "Example address 1", "x", "y"));
        queue.add(new Place(1, "New Place 2", "Example address 2", "x", "y"));
        pc = new PlaceController(mockFinder, queue);
        pc.savePlaces("data/testData/testSaved.txt");
        PlaceController newPc = new PlaceController(mockFinder, new Queue<Queueable>(new Queueable[10]));
        newPc.useSavedPlaces("data/testData/testSaved.txt");
        Place[] places = newPc.getPlaces();
        assertEquals("0;New Place 1;Example address 1;x;y||1;New Place 2;Example address 2;x;y", places[0].toString() + "||" + places[1].toString());
    }

    @Test
    public void importingNonExistentFileThrowsAnError() throws Exception {
        pc = new PlaceController(mockFinder, queue);
        this.setReturnValuesForMockFinder();
        boolean failed = false;
        try {
            pc.importPlaces("fake.txt");
        } catch (Exception ex) {
            failed = true;
        }
        assertTrue(failed);
    }

    @Test
    public void gettingSavedPlacesFromNonExistentFileThrowsAnError() {
        pc = new PlaceController(mockFinder, queue);
        boolean failed = false;
        try {
            pc.useSavedPlaces("fake.txt");
        } catch (Exception ex) {
            failed = true;
        }
        assertTrue(failed);
    }

    private void setReturnValuesForMockFinder() throws Exception {
        String[] co = new String[2];
        co[0] = "x";
        co[1] = "y";
        when(mockFinder.findCoordinates("Example address 1")).thenReturn(co);
        when(mockFinder.findCoordinates("Example address 2")).thenReturn(co);
        when(mockFinder.findCoordinates("Example address 3")).thenReturn(co);
        when(mockFinder.findCoordinates("Viides linja 11")).thenReturn(co);        
        when(mockFinder.findCoordinates("New Home")).thenReturn(co);
        when(mockFinder.findCoordinates("New Place address")).thenReturn(co);
        when(mockFinder.findCoordinates("Not-existing")).thenReturn(null);
    }

}
