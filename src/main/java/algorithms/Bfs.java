package algorithms;

import dataStructures.Queue;

/**
 * Bfs algorithm finds the path with the least edges between node 0 and all
 * other nodes.
 *
 * @author mshroom
 */
public class Bfs extends ShortestPath {

    Queue[] neighbours;

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
        Queue[] n = new Queue[graph.length];
        for (int i = 0; i < graph.length; i++) {
            Queue nodes = new Queue(10);
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
    public void calculateShortestPath() throws Throwable {
        this.initialize();
        Queue queue = new Queue(this.neighbours.length);
        queue.add(0);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            while (!neighbours[node].isEmpty()) {
                int neighbour = neighbours[node].poll();
                if (visited[neighbour] == 0) {
                    visited[neighbour] = 1;
                    distance[neighbour] = distance[node] + 1;
                    path[neighbour] = node;
                    queue.add(neighbour);
                }
            }
        }
    }
}
