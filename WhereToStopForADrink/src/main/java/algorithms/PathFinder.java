/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.util.ArrayDeque;

/**
 *
 * @author mshroom
 */
public abstract class PathFinder {

    int[] distance;
    int[] path;
    int[] visited;

    public PathFinder(int[][] graph) {
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

    public abstract void calculateShortestPath();

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
    public String getShortestPath(int goal) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int previous = path[goal];
        if (previous == -1) {
            return "There is no path";
        }
        while (previous != 0) {
            stack.addFirst(previous);
            previous = path[previous];
        }
        String ret = "0 > ";
        while (!stack.isEmpty()) {
            ret = ret + (stack.removeFirst() + " > ");
        }
        return "" + ret + goal;
    }

}
