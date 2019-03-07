package web;

import java.net.URLEncoder;

/**
 * Creates and sends HTTP requests to the Geocoding API of Digitransit.
 * @author mshroom
 */
public class AddressFinder {
    private String baseUrl;
    private Connection connection;
    
    public AddressFinder() {
        this.baseUrl = "http://api.digitransit.fi/geocoding/v1/";
        this.connection = new Connection();
    }
    
    /**
     * Creates and sends a HTTP request that finds the coordinates for the given address.
     * @param address The address to be searched.
     * @return An array containing the coordinates.
     * @throws Exception if the connection fails.
     */
    public String[] findCoordinates(String address) throws Exception {
        String[] coordinates = new String[2];
        String url = this.baseUrl;
        String formattedAddress = URLEncoder.encode(address, "UTF-8");
        url += "search?text=" + formattedAddress + "&size=1";
        connection.connectTo(url);
        String content = connection.getRequest();
        connection.close();
        String find = "coordinates\":\\u005B";
        String[] result = content.split(find);
        if (result.length >1) {
            coordinates = result[1].split("]")[0].split(",");
        }
        return coordinates;
    }
}
