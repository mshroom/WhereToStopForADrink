package web;

import dataStructures.Place;

/**
 *
 * @author mshroom
 */
public class DistanceFinder {
    private String baseUrl;
    private Connection connection;
    
    public DistanceFinder() {
        this.baseUrl = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql";
        this.connection = new Connection();
    }
    
    public int findDistance(String aX, String aY, String bX, String bY) throws Exception {
        String distance = "";
        this.connection.connectTo(baseUrl);
        String contentType = "application/graphql";
        String queryParameters = "{plan(from:{lat:";
        queryParameters += aY + ",lon:" + aX + "},to:{lat:";
        queryParameters += bY + ",lon:" + bX + "},transportModes:[{mode: WALK}]){itineraries{legs{distance}}}}";
        String content = connection.postRequest(queryParameters, contentType);
        connection.close();
        String find = "distance\":";
        String[] result = content.split(find);
        if (result.length > 1) {
            distance = result[1].split("\\.")[0];
        }
        if (distance.equals("")) {
            return -1;
        }
        return Integer.parseInt(distance);
    }
    
}
