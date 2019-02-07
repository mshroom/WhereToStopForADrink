package control;

import dataStructures.ObjectQueue;
import dataStructures.Place;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    ObjectQueue queue;

    @Before
    public void setUp() {
        mockFinder = mock(AddressFinder.class);
        queue = new ObjectQueue(10);
    }

    @Test
    public void placesCanBeImportedFromFileAndCorrectlyConvertedToPlaceObjects() throws Exception {
        pc = new PlaceController(mockFinder, queue);
        this.setReturnValuesForMockFinder();        
        pc.importPlaces("testPlaces.txt");
        Place[] places = pc.getPlaces();
        assertEquals("0;Place 1;Example address 1;x;y||1;Place 2;Example address 2;x;y", places[0].toString() + "||" + places[1].toString());
    }

    @Test
    public void placesCanBeSavedAndReaccessedLater() throws Exception {
        queue.add(new Place(0, "New Place 1", "Example address 1", "x", "y"));
        queue.add(new Place(1, "New Place 2", "Example address 2", "x", "y"));
        pc = new PlaceController(mockFinder, queue);
        pc.savePlaces("testSaved.txt");
        PlaceController newPc = new PlaceController(mockFinder, new ObjectQueue(10));
        newPc.useSavedPlaces("testSaved.txt");
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
    }

}
