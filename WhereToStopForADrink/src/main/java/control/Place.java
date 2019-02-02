package control;

/**
 *
 * @author mshroom
 */
public class Place {
    private int index;
    private String name;
    private String address;
    private String x;
    private String y;
    
    public Place(int index, String name, String address, String x, String y) {
        this.index = index;
        this.name = name;
        this.address = address;
        this.x = x;
        this.y = y;
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
    
    @Override
    public String toString() {
        return this.index + ";" + this.name + ";" + this.address + ";" + this.x + ";" + this.y;
    }    
}
