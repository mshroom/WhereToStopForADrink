package algorithms;

/**
 * Class calculates the shortest route that visits all nodes in a graph and returns home.
 * @author mshroom
 */
public class TspExact extends ShortestRoute {
    
    public TspExact(int[][] distance) {
        super(distance);
    }
    
    /**
     * Method calculates the shortest route by calling a recursive sub-method.
     */
    @Override    
    public void calculateShortestRoute() {
        int[] visited = new int[distance.length];
        int[] route = new int[distance.length];
        shortestRouteRecursively(0, visited, 0, 1, route);
    }
    
    /**
     * Method goes through all permutations of the route by calling itself recursively.
     * If the path is already longer than the shortest one found, it will not be examined further.
     * The shortest route and its length will be saved to the class variables.
     * @param length shows the length of the examined path in current permutation
     * @param visited an array containing value 1 for nodes that have been visited, 0 for unvisited nodes
     * @param currentNode the index of the current node, 0 is the starting point
     * @param visitNumber shows how many nodes have been visited, including current node
     * @param route an array containing the order of visited nodes in this permutation
     */
    private void shortestRouteRecursively(int length, int[] visited, int currentNode, int visitNumber, int[] route) {
        if (visitNumber == distance.length) {
            if (length + distance[currentNode][0] < lengthOfShortestRoute) {
                lengthOfShortestRoute = length + distance[currentNode][0];
                shortestRoute = route;
            }
            return;
        }
        for (int i = 1; i < distance.length; i ++) {
            if ((visited[i] == 0) && (length + distance[currentNode][i] < lengthOfShortestRoute)) {
                int[] visited2 = visited.clone();
                int[] deque2 = route.clone();
                visited2[i] = 1;
                deque2[visitNumber] = i; 
                shortestRouteRecursively(length + distance[currentNode][i], visited2, i, visitNumber + 1, deque2);
            }
        }
        return;
    }
}
