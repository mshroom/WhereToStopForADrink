package web;

import dataStructures.Place;

/**
 * Creates and sends HTTP requests to the Routiing API of Digitransit.
 * @author mshroom
 */
public class DistanceFinder {
    private String baseUrl;
    private Connection connection;
    
    public DistanceFinder() {
        this.baseUrl = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql";
        this.connection = new Connection();
    }
    
    /**
     * Creates and sends a HTTP request that finds the distance between two places.
     * @param firstX The X coordinate of the first place.
     * @param firstY The Y coordinate of the first place.
     * @param otherX The X coordinate of the second place.
     * @param otherY The Y coordinate of the second place.
     * @return The distance between the places in meters or -1 if the distance was not found.
     * @throws Exception if the connection fails.
     */
    public int findDistance(String firstX, String firstY, String otherX, String otherY) throws Exception {
        String distance = "";
        this.connection.connectTo(baseUrl);
        String contentType = "application/graphql";
        String queryParameters = "{plan(from:{lat:";
        queryParameters += firstY + ",lon:" + firstX + "},to:{lat:";
        queryParameters += otherY + ",lon:" + otherX + "},transportModes:[{mode: WALK}]){itineraries{legs{distance}}}}";
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
