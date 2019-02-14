package algorithms;

/**
 * An abstract class for algorithms that find the shortest route that visits all nodes in a complete graph.
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
    
    /**
     * Method prints the shortest route. 
     * @return A String describing the path.
     */
    public String printShortestRoute() {
        String ret = "";
        for (int i : shortestRoute) {
            ret += i + " > ";
        }
        ret += 0;
        return ret;
    }
    
    public int getLengthOfShortestRoute() {
        return this.lengthOfShortestRoute;
    }
}
