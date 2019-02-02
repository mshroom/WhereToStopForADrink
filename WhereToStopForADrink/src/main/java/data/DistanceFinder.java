package data;

import control.Place;

/**
 *
 * @author mshroom
 */
public class DistanceFinder {
    private String baseUrl;
    
    public DistanceFinder() {
        this.baseUrl = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql";
    }
    
    public int findDistance(Place a, Place b) throws Exception {
        String distance = "";
        Connection con = new Connection(baseUrl);
        String contentType = "application/graphql";
        String queryParameters = "{plan(from:{lat:";
        queryParameters += a.getY() + ",lon:" + a.getX() + "},to:{lat:";
        queryParameters += b.getY() + ",lon:" + b.getX() + "},transportModes:[{mode: WALK}]){itineraries{legs{distance}}}}";
        String content = con.postRequest(queryParameters, contentType);
        String find = "distance\":";
        String[] result = content.split(find);
        if (result.length > 1) {
            distance = result[1].split("\\.")[0];
        }
        return Integer.parseInt(distance);
    }
    
}
