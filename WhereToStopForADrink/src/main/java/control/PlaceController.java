package control;

import data.AddressFinder;
import dataStructures.PlaceQueue;
import io.FileIO;

/**
 *
 * @author mshroom
 */
public class PlaceController {

    private PlaceQueue queue;
    private Place[] places;
    private AddressFinder finder;


    public PlaceController() {
        this.queue = new PlaceQueue(10);
        this.finder = new AddressFinder();
    }

    public void importPlaces(String file) throws Exception {
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
    
    public void savePlaces() throws Exception {
        FileIO io = new FileIO("saved.txt");
        io.clear();
        for (Place p : places) {
            io.printLine(p.toString());
        }
    }
    
    public void useSavedPlaces() throws Exception {
        FileIO io = new FileIO("saved.txt");
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

    public Place[] getPlaces() {
        return this.places;
    }

    private void convertQueueToArray() {
        this.places = new Place[queue.getSize()];
        while (!queue.isEmpty()) {
            Place p = queue.poll();
            this.places[p.getIndex()] = p;
        }        
    }
}
