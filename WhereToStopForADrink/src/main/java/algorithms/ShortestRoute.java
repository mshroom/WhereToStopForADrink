package algorithms;

/**
 *
 * @author mshroom
 */
public abstract class ShortestRoute {
    int[][] distance;
    int lengthOfShortestRoute;
    int[] shortestRoute;
    
    public ShortestRoute(int[][] distance) {
        this.distance = distance;
        lengthOfShortestRoute = Integer.MAX_VALUE;
        this.shortestRoute = new int[distance.length];
    }
    
    public abstract void calculateShortestRoute();
    
    public int[] getShortestRoute() {
        return this.shortestRoute;
    }
    int getLengthOfShortestRoute() {
        return this.lengthOfShortestRoute;
    }
}
