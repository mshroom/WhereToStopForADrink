package control;

import dataStructures.Place;
import web.AddressFinder;
import dataStructures.ObjectQueue;
import io.FileIO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PlaceController controls the places of the current GraphController and 
 * contains methods to import and export Place data.
 * @author mshroom
 */
public class PlaceController {

    private ObjectQueue queue;
    private Place[] places;
    private AddressFinder finder;
    private FileIO io;
    private String homeAddress;
    private boolean homeAddressIsSet;

    /**
     * Create a new PlaceController with an empty list of places.
     */
    public PlaceController() {
        this.queue = new ObjectQueue(10);
        this.finder= new AddressFinder();
        this.io = new FileIO();
        this.homeAddress = "Viides linja 11";
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
        this.io = new FileIO();
        this.homeAddress = "Viides linja 11";
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
        int index;
        if (this.addHomeToPlaces(this.homeAddress)) {
            index = 1;
        } else {
            index = 0;
        }
        io.setFile(file);        
        while (true) {            
            String data = io.readLine("");
            if (data.equals("")) {
                break;
            }
            String[] parts = data.split(";");
            String name = parts[0];
            String address = parts[1];
            String[] coordinates = finder.findCoordinates(address);
            if (coordinates[0] != null) {
                Place place = new Place(index, name, address, coordinates[0], coordinates[1]);
                queue.add(place);
                index++;
            }
        }
        this.convertQueueToArray();
    }

    private boolean addHomeToPlaces(String address) {         
        String[] homeCoordinates;
        try {
            homeCoordinates = finder.findCoordinates(address);
            if (homeCoordinates[0] != null) {
                Place home = new Place(0, "Home", address, homeCoordinates[0], homeCoordinates[1]);
                queue.push(home);
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }        
    }
    
    public boolean changeHome(String newAddress) {
        Place oldHome = (Place) queue.poll();
        if (this.addHomeToPlaces(newAddress)) {
            this.homeAddress = newAddress;
            this.places[0] = (Place) queue.peek();
            return true;
        } else {
            queue.push(oldHome);         
            return false;
        }   
    }
    
    /**
     * Saves the current Place list into a text file.
     * @param file The name of the file. Note that the file must already exist.
     * @throws Exception if an error occurs while saving data.
     */
    public void savePlaces(String file) throws Exception {
        io.setFile(file);
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
        io.setFile(file);
        while (true) {            
            String data = io.readLine("");
            if (data.equals("")) {
                break;
            }
            String[] places = data.split("\\|\\|");
            for (int i = 0; i < places.length; i ++) {
                String place = places[i];
                String[] attributes = place.split(";");
                Place savedPlace = new Place(Integer.parseInt(attributes[0]), attributes[1], attributes[2], attributes[3], attributes[4]);
                queue.add(savedPlace);
            }
        }
        Place home = (Place) queue.peek();
        this.homeAddress = home.getAddress();
        this.homeAddressIsSet = true;
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
    
    public String getHomeAddress() {
        return this.homeAddress;
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
        ObjectQueue copy = queue.copy();
        while (!copy.isEmpty()) {
            Place place = (Place) copy.poll();
            this.places[place.getIndex()] = place;
        }        
    }
    
    /**
     * Finds the coordinates for the given place and adds it to the place list.
     * @param name The name of the place.
     * @param address The address of the place.
     * @return True if the addition was successful, otherwise false.
     */
    public boolean addPlace(String name, String address) {
        String[] coordinates;
        try {
            coordinates = finder.findCoordinates(address);
            if (coordinates[0] != null) {
                Place newPlace = new Place(places.length, name, address, coordinates[0], coordinates[1]);
                queue.add(newPlace);
                this.convertQueueToArray();
                return true;
            }
            return false;
        } catch (Exception ex) {
            return false;
        }    
    }
}
