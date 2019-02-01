package algorithms;

import dataStructures.MinHeap;
import dataStructures.NodeQueue;

/**
 * Class calculates the shortest path from starting node to all other nodes in a
 * graph.
 *
 * @author mshroom
 */
public class Dijkstra extends ShortestPath {

    private NodeQueue[] neighbours;

    public Dijkstra(int[][] graph) {
        super(graph);
        this.convertGraph(graph);
    }

    /**
     * Method converts the graph to an adjacency list.
     * @param graph the original graph in the form of a two-dimensional array
     */
    @Override
    protected void convertGraph(int[][] graph) {
        NodeQueue[] n = new NodeQueue[graph.length];
        for (int i = 0; i < graph.length; i++) {
            NodeQueue nodes = new NodeQueue(10);
            for (int j = 0; j < graph.length; j++) {
                if (i != j && graph[i][j] >= 0) {
                    nodes.add(new Node(j, graph[i][j]));
                }
            }
            n[i] = nodes;
        }
        this.neighbours = n;
    }

    /**
     * Method calculates the shortest path from node 0 to all other nodes.
     */
    @Override
    public void calculateShortestPath() {
        this.initialize();
        NodeQueue[] neighboursCopy = this.neighbours.clone();
        MinHeap heap = new MinHeap(neighbours.length * neighbours.length);
        heap.add(new Node(0, 0));
        while (!heap.isEmpty()) {
            Node smallest = heap.poll();
            if (visited[smallest.getIndex()] == 0) {
                visited[smallest.getIndex()] = 1;
                while (!neighboursCopy[smallest.getIndex()].isEmpty()) {
                    Node node = neighboursCopy[smallest.getIndex()].poll();                
                    if (distance[node.getIndex()] == -1 || distance[node.getIndex()] > distance[smallest.getIndex()] + node.getDistance()) {
                        distance[node.getIndex()] = distance[smallest.getIndex()] + node.getDistance();
                        path[node.getIndex()] = smallest.getIndex();
                        heap.add(new Node(node.getIndex(), distance[node.getIndex()]));                        
                    }
                }
            }
        }
    }
}
