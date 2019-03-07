package dataStructures;

/**
 * Place object represents a geographical place.
 * @author mshroom
 */
public class Place implements Queueable {
    private int index;
    private String name;
    private String address;
    private String x;
    private String y;
    private boolean home;
    
    /**
     * Creates a new Place object.
     * @param index The index of the place.
     * @param name The name of the place.
     * @param address The address.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Place(int index, String name, String address, String x, String y) {
        this.index = index;
        this.name = name;
        this.address = address;
        this.x = x;
        this.y = y;
        this.home = false;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }
    
    public String getAddress() {
        return address;
    }
    
    @Override
    public String toString() {
        return this.index + ";" + this.name + ";" + this.address + ";" + this.x + ";" + this.y;
    }    
}
