package algorithms;

import dataStructures.Queue;

/**
 * An abstract class for algorithms that find the shortest path between two nodes in a graph.
 * @author mshroom
 */
public abstract class ShortestPath {

    int[] distance;
    int[] path;
    int[] visited;

    public ShortestPath(int[][] graph) {
        this.distance = new int[graph.length];
        this.path = new int[graph.length];
        this.visited = new int[graph.length];
    }

    protected abstract void convertGraph(int[][] graph);

    protected void initialize() {
        for (int i = 0; i < distance.length; i++) {
            distance[i] = -1;
            path[i] = -1;
            visited[i] = 0;
        }
        distance[0] = 0;
    }

    public abstract void calculateShortestPath() throws Throwable;

    /**
     * Method returns the length of the shortest path
     * @param node the index of the node at the end of the path
     * @return the length of the shortest path
     */
    public int getDistanceTo(int node) {
        return this.distance[node];
    }
    
    /**
     * Method prints the shortest path from starting node to given node.     *
     * @param goal The index of the node at the end of the path.
     * @return A String describing the path.
     */
    public String getShortestPath(int goal) throws Throwable {
        Queue stack = new Queue(10);
        int previous = path[goal];
        if (previous == -1) {
            return "There is no path";
        }
        while (previous != 0) {
            stack.push(previous);
            previous = path[previous];
        }
        String ret = "0 > ";
        while (!stack.isEmpty()) {
            ret = ret + (stack.poll() + " > ");
        }
        return "" + ret + goal;
    }
    
    public int[] getPath() {
        return this.path;
    }
    
    /**
     * Tells if the shortest path has been found.
     * @param goal The index of the goal node.
     * @return True if the path has been found, false if there is no path.
     */
    public boolean pathWasFound(int goal) {
        if (path[goal] < 0 ) {
            return false;
        }
        return true;
    }
    
}
