package algorithms;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Bfs algorithm finds the path with the least edges between node 0 and all
 * other nodes.
 *
 * @author mshroom
 */
public class Bfs extends PathFinder {

    List<Integer>[] neighbours;

    public Bfs(int[][] graph) {
        super(graph);
        this.convertGraph(graph);
    }

    /**
     * Method converts the graph to an adjacency list.
     *
     *
     * @param graph the original graph in the form of a two-dimensional array
     */
    @Override
    protected void convertGraph(int[][] graph) {
        List<Integer>[] n = new List[graph.length];
        for (int i = 0; i < graph.length; i++) {
            ArrayList<Integer> nodes = new ArrayList<>();
            for (int j = 0; j < graph.length; j++) {
                if (i != j && graph[i][j] >= 0) {
                    nodes.add(j);
                }
            }
            n[i] = nodes;
        }
        this.neighbours = n;
    }

    @Override
    protected void initialize() {
        super.initialize();
        visited[0] = 1;
    }

    /**
     * Method calculates the path with the least edges from node 0 to all other
     * nodes.
     */
    @Override
    public void calculateShortestPath() {
        this.initialize();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int node = queue.pollFirst();
            for (Integer i : neighbours[node]) {
                if (visited[i] == 0) {
                    visited[i] = 1;
                    distance[i] = distance[node] + 1;
                    path[i] = node;
                    queue.addLast(i);
                }
            }
        }
    }
}
