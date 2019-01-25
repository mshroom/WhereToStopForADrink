package algorithms;

/**
 * Class calculates the shortest route that visits all nodes in a graph and returns home.
 * The algorithm uses the nearest neighbour strategy that is fast but does not always find the shortest route.
 * @author mshroom
 */
public class TspNn extends ShortestRoute {
    
    public TspNn(int[][] distance) {
        super(distance);
        lengthOfShortestRoute = 0;
    }
    
    /**
     * Method calculates the route with a greedy strategy that always continues to the nearest unvisited node.
     * Note that although this method usually finds a short route, it is not guaranteed to find the shortest route.
     */
    @Override
    public void calculateShortestRoute() {
        int[] visited = new int[distance.length];
        visited[0] = 1;
        int current = 0;
        for (int k = 1; k < distance.length; k ++) {
            int nearest = Integer.MAX_VALUE;
            int next = -1;
            for (int i = 0; i < distance.length; i ++) {
                if (visited[i] == 0 && distance[current][i] < nearest) {
                    nearest = distance[current][i];
                    next = i;
                }
            }
            lengthOfShortestRoute += distance[current][next];
            shortestRoute[k] = next;
            visited[next] = 1;
            current = next;            
        }
        lengthOfShortestRoute += distance[0][shortestRoute[shortestRoute.length - 1]];
    }
}
