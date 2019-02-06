package web;

import java.net.URLEncoder;

/**
 *
 * @author mshroom
 */
public class AddressFinder {
    private String baseUrl;
    
    public AddressFinder() {
        this.baseUrl = "http://api.digitransit.fi/geocoding/v1/";
    }
    
    public String[] findCoordinates(String address) throws Exception {
        String[] coordinates = new String[2];
        String url = this.baseUrl;
        String formattedAddress = URLEncoder.encode(address, "UTF-8");
        url += "search?text=" + formattedAddress + "&size=1";
        Connection con = new Connection(url);
        String content = con.getRequest();
        con.close();
        String find = "coordinates\":\\u005B";
        String[] result = content.split(find);
        if (result.length >1) {
            coordinates = result[1].split("]")[0].split(",");
        }
        return coordinates;
    }
}
