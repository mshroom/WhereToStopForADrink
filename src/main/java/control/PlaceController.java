package control;

import dataStructures.Place;
import web.AddressFinder;
import dataStructures.ObjectQueue;
import dataStructures.Queueable;
import io.FileIO;
import io.IO;

/**
 * PlaceController controls the places of the current GraphController and 
 * contains methods to import and export Place data.
 * @author mshroom
 */
public class PlaceController {

    private ObjectQueue queue;
    private Place[] places;
    private AddressFinder finder;

    /**
     * Create a new PlaceController with an empty list of places.
     */
    public PlaceController() {
        this.queue = new ObjectQueue(10);
        this.finder= new AddressFinder();
    }
    
    /**
     * Create a new PlaceController with a custom AddressFinder and a queue.
     * @param finder AddressFinder object to be used with this PlaceController.
     * @param queue ObjectQueue can be empty or contain Place objects that will be
     * added to the place list.
     */
    public PlaceController(AddressFinder finder, ObjectQueue queue) {
        this.queue = queue;
        this.convertQueueToArray();
        this.finder = finder;
    }
    
    /**
     * Imports places from a text file, finds the coordinates for the places and
     * adds the places to the place list.
     * @param file The name of the file containing place data. 
     * Each row in the file must describe the place in the form "Name;Address" (without
     * the quotation marks).
     * @throws Exception if an error occurs while importing the data.
     */
    public void importPlaces(String file) throws Exception {
        System.out.println("Getting coordinates...");
        this.queue = new ObjectQueue(10);
        FileIO io = new FileIO(file);
        int i = 0;
        while (true) {            
            String p = io.readLine("");
            if (p.equals("")) {
                break;
            }
            String[] parts = p.split(";");
            String name = parts[0];
            String address = parts[1];
            String[] coordinates = finder.findCoordinates(address);
            if (coordinates[0] != null) {
                Place place = new Place(i, name, address, coordinates[0], coordinates[1]);
                queue.add(place);
                i++;
            }
        }
        this.convertQueueToArray();
    }

    /**
     * Saves the current Place list into a text file.
     * @param file The name of the file. Note that the file must already exist.
     * @throws Exception if an error occurs while saving data.
     */
    public void savePlaces(String file) throws Exception {
        FileIO io = new FileIO(file);
        io.clear();
        for (Place p : places) {
            io.printLine(p.toString());
        }
    }
    
    /**
     * Imports saved Place objects from a text file
     * @param file The name of the file. Each row in the file must
     * contain a toString representation of a Place object.
     * @throws Exception if an error occurs while importing saved data.
     */
    public void useSavedPlaces(String file) throws Exception {
        this.queue = new ObjectQueue(10);
        FileIO io = new FileIO(file);
        while (true) {            
            String p = io.readLine("");
            if (p.equals("")) {
                break;
            }
            String[] places = p.split("\\|\\|");
            for (int i = 0; i < places.length; i ++) {
                String place = places[i];
                String[] attributes = place.split(";");
                Place savedPlace = new Place(Integer.parseInt(attributes[0]), attributes[1], attributes[2], attributes[3], attributes[4]);
                queue.add(savedPlace);
            }
        }
        this.convertQueueToArray();
    }

    /**
     * Returns the places in an array where the index of the array corresponds to the
     * index of the Place object.
     * @return An array containing the current Place objects.
     */
    public Place[] getPlaces() {
        return this.places;
    }

    /**
     * Converts the Place queue to a simple array where the index of the array
     * corresponds to the index of the Place object.
     */
    private void convertQueueToArray() {
        if (queue.getSize() < 1) {
            return;
        }
        this.places = new Place[queue.getSize()];
        while (!queue.isEmpty()) {
            Place p = (Place) queue.poll();
            this.places[p.getIndex()] = p;
        }        
    }
}
